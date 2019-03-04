USE MM2;
GO

SELECT *
FROM dbo.Utilizador
    INNER JOIN dbo.Cliente
        ON Cliente.Utilizador = Utilizador.email;

SELECT *
FROM dbo.Utilizador
    INNER JOIN dbo.Estabelecimento
        ON Estabelecimento.Utilizador = Utilizador.email;

SELECT *
FROM dbo.Cliente;

SELECT *
FROM dbo.Estabelecimento;

SELECT *
FROM dbo.HorarioEstabelecimento;

SELECT *
FROM dbo.Categoria;

SELECT *
FROM dbo.Iguaria;



SELECT *
FROM dbo.Cliente_avalia_Estabelecimento;

SELECT *
FROM dbo.Cliente_critica_Iguaria;


SELECT id_selecao,
       Cliente_seleciona_Estabelecimento.Cliente,
       Cliente_seleciona_Estabelecimento.Estabelecimento,
       data_hora_selecao
FROM dbo.Cliente_seleciona_Estabelecimento
    INNER JOIN dbo.SelecaoEstabelecimento
        ON SelecaoEstabelecimento.Estabelecimento = Cliente_seleciona_Estabelecimento.Estabelecimento
           AND SelecaoEstabelecimento.Cliente = Cliente_seleciona_Estabelecimento.Cliente
ORDER BY id_selecao;

SELECT Cliente_seleciona_iguaria.Cliente,
       Cliente_seleciona_iguaria.Iguaria,
       Cliente_seleciona_iguaria.Estabelecimento,
       data_hora_visualizacao
FROM dbo.Cliente_seleciona_iguaria
    INNER JOIN dbo.SelecaoIguaria
        ON SelecaoIguaria.Cliente = Cliente_seleciona_iguaria.Cliente
           AND SelecaoIguaria.Iguaria = Cliente_seleciona_iguaria.Iguaria
           AND SelecaoIguaria.Estabelecimento = Cliente_seleciona_iguaria.Estabelecimento
ORDER BY id_visualizacao;



UPDATE dbo.Utilizador
SET tipo = 0
WHERE email = 'ola@mail.pt';

SELECT COUNT(*)
FROM dbo.SelecaoIguaria
WHERE Cliente = 1
      AND Estabelecimento = 2
      AND Iguaria = 1;

SELECT *
FROM dbo.Cliente_seleciona_iguaria
WHERE Cliente = 1
      AND Iguaria = 2
      AND Estabelecimento = 1;


SELECT *
FROM dbo.Iguaria
WHERE nome_iguaria LIKE '%Bife%';


SELECT AVG(rating_igu)
FROM dbo.Cliente_critica_Iguaria
WHERE Iguaria = 2
      AND Estabelecimento = 1;

UPDATE dbo.Estabelecimento
SET latitude = 41.5632291,
    longitude = -8.422394
WHERE id_estabelecimento = 1;