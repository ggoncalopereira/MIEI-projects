USE ferroviaria;
-- -----------------------------------------------------
-- Restrição que garante que um comboio numa reserva é o mesmo
-- que o comboio na viagem dessa reserva
DELIMITER |
CREATE TRIGGER verifica_comboio
BEFORE INSERT ON reserva
FOR EACH ROW
BEGIN
	IF (NEW.comboio != (SELECT comboio 
							FROM viagem 
							WHERE NEW.viagem = viagem.id_viagem))
	THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Comboio inválido';
	END IF;
END
|
DELIMITER ;   
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Restrição que garante que um lugar é apenas reservado
-- uma vez para uma viagem
DELIMITER |
CREATE TRIGGER verifica_lugar
BEFORE INSERT ON reserva
FOR EACH ROW
BEGIN
    IF (1 = (SELECT COUNT(lugar) FROM reserva WHERE NEW.viagem = reserva.viagem AND
													NEW.comboio =  reserva.comboio AND
													NEW.carruagem = reserva.carruagem AND
													NEW.lugar = reserva.lugar))
    THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lugar Ocupado';
	END IF;
END
|
DELIMITER ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tamanho da Base de Dados em bytes
DELIMITER |
SELECT table_name AS 'Tabela', (data_length + index_length) AS 'Tamanho (bytes)'
	FROM information_schema.TABLES
    WHERE table_schema = 'ferroviaria';
|
DELIMITER ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Regras de Acesso
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'passwordadmin';
GRANT ALL PRIVILEGES ON ferroviaria.* TO 'admin'@'localhost';

CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'passwordgerente';
GRANT SELECT ON ferroviaria.* TO 'gerente'@'localhost';
GRANT INSERT, UPDATE ON ferroviaria.comboio TO 'gerente'@'localhost';
GRANT INSERT, UPDATE ON ferroviaria.viagem TO 'gerente'@'localhost';

CREATE USER 'user'@'localhost' IDENTIFIED BY 'passworduser';
GRANT SELECT ON ferroviaria.* TO 'user'@'localhost';
GRANT INSERT, UPDATE ON ferroviaria.cliente TO 'user'@'localhost';
GRANT INSERT, UPDATE ON ferroviaria.reserva TO 'user'@'localhost';
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Transação para fazer reserva
DELIMITER |
CREATE PROCEDURE faz_reserva (IN Id_reserva INT, IN Data_reserva DATETIME, IN Cliente VARCHAR (40), IN Viagem INT,
							  IN Comboio INT, IN Carruagem INT, IN Lugar INT)
BEGIN
	declare Preco_reserva Decimal(3,2);
    DECLARE Erro BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET Erro = 1;
    START TRANSACTION;
		SELECT (preco*taxa) into Preco_reserva FROM viagem, tipo_comboio, comboio 
									  WHERE viagem.id_viagem = Viagem 
											AND comboio.id_comboio = Comboio
											AND comboio.tipo = tipo_comboio.designacao LIMIT 1;
		INSERT INTO reserva (id_reserva, preco, data_reserva, cliente, viagem, comboio, carruagem, lugar)
			VALUES (Id_reserva, Preco_reserva, Data_reserva, Cliente, Viagem, Comboio, Carruagem, Lugar);
		IF Erro THEN
			ROLLBACK;
		ELSE
			COMMIT;
		END IF;
END
|
DELIMITER ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Transação para consultar lugares num comboio
DELIMITER |
CREATE PROCEDURE consultar_lugares (IN Viagem INT)
BEGIN
    START TRANSACTION;
		SELECT comboio, carruagem, lugar FROM lugares WHERE (carruagem, lugar) 
				NOT IN (SELECT carruagem, lugar FROM reserva WHERE reserva.viagem = Viagem AND 
																   reserva.comboio = (SELECT comboio FROM viagem WHERE id_viagem = Viagem)) AND
                                                                   lugares.comboio = (SELECT comboio FROM viagem WHERE id_viagem = Viagem);
END
|
DELIMITER ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Transação para inserir uma viagem
DELIMITER |
CREATE PROCEDURE insere_viagem (IN Id_viagem INT, IN Data_partida DATETIME, IN Data_chegada DATETIME, IN PRECO DECIMAL(3, 2),
								IN Estacao_partida VARCHAR(10), IN Estacao_chegada VARCHAR(10), IN Comboio INT)
BEGIN
    DECLARE Erro BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET Erro = 1;
    START TRANSACTION;
        INSERT INTO viagem (id_viagem, data_partida, data_chegada, preco, estacao_partida, estacao_chegada, comboio)
			VALUES (Id_viagem, Data_partida, Data_chegada, Preco, Estacao_partida, Estacao_chegada, Comboio);
            
        IF Erro THEN
			ROLLBACK;
		ELSE
			COMMIT;
		END IF;
END
|
DELIMITER ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Povoar BD
INSERT INTO cliente (username, password, nome)
	VALUES ('username1', '123', 'Helder'), ('username2', '1234', 'Andre'), 
		   ('username3', '12345', 'Antonio'), ('username4', '123456', 'Goncalo');

INSERT INTO tipo_comboio (taxa, designacao)
	VALUES ('0.9', 'Alfa'),
		   ('0.6', 'Int');
           
INSERT INTO comboio (id_comboio, tipo)
	VALUES (1, 'Alfa'),
		   (2, 'Alfa'),
           (3, 'Int'),
		   (4, 'Int');

INSERT INTO lugares (comboio, carruagem, lugar)
	VALUES (1, 1, 2), (1, 2, 2),
           (2, 1, 1), (2, 2, 2),
           (3, 1, 1), (3, 2, 2),
           (4, 1, 1), (4, 2, 2);

INSERT INTO estacao (nome)
	VALUES ('Braga'), ('Porto'), ('Aveiro'),
		   ('Guarda'), ('Lisboa'), ('Madrid');

CALL insere_viagem (1, '2017-1-1 09:00:00', '2017-1-1 10:00:00', '5.5', 'Braga', 'Porto', '1');
CALL insere_viagem (2, '2017-1-2 16:00:00', '2017-1-2 20:00:00', '7.5', 'Lisboa', 'Madrid', '2');
CALL insere_viagem (3, '2017-1-3 10:00:00', '2017-1-3 14:00:00', '3.5', 'Porto', 'Lisboa', '3');

CALL faz_reserva (1, '2017-1-1 09:00:00', 'username1', 1, 1, 1, 2);
CALL faz_reserva (2, '2017-1-1 09:00:00', 'username1', '1', '1', '2', '2');
CALL faz_reserva (3, '2017-1-2 16:00:00', 'username2', '2', '2', '1', '1');
CALL faz_reserva (4, '2017-1-3 10:00:00', 'username3', '3', '3', '2', '2');

CALL consultar_lugares (1);
-- -----------------------------------------------------
