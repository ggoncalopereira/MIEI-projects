-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ferroviaria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ferroviaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ferroviaria` DEFAULT CHARACTER SET utf8 ;
USE `ferroviaria` ;

-- -----------------------------------------------------
-- Table `ferroviaria`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`cliente` (
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`tipo_comboio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`tipo_comboio` (
  `taxa` DECIMAL(2,2) NOT NULL,
  `designacao` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`designacao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`comboio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`comboio` (
  `id_comboio` INT NOT NULL,
  `tipo` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id_comboio`),
  INDEX `fk_comboio_tipo_idx` (`tipo` ASC),
  CONSTRAINT `fk_comboio_tipo`
    FOREIGN KEY (`tipo`)
    REFERENCES `ferroviaria`.`tipo_comboio` (`designacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`estacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`estacao` (
  `nome` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`viagem` (
  `id_viagem` INT NOT NULL,
  `data_partida` DATETIME NOT NULL,
  `data_chegada` DATETIME NOT NULL,
  `preco` DECIMAL(3,2) NOT NULL,
  `estacao_partida` VARCHAR(10) NOT NULL,
  `estacao_chegada` VARCHAR(10) NOT NULL,
  `comboio` INT NOT NULL,
  PRIMARY KEY (`id_viagem`),
  INDEX `fk_comboio_idx` (`comboio` ASC),
  INDEX `fk_estacao_partida_idx` (`estacao_partida` ASC),
  INDEX `fk_estacao_chegada_idx` (`estacao_chegada` ASC),
  CONSTRAINT `fk_viagem_comboio`
    FOREIGN KEY (`comboio`)
    REFERENCES `ferroviaria`.`comboio` (`id_comboio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_estacao_partida`
    FOREIGN KEY (`estacao_partida`)
    REFERENCES `ferroviaria`.`estacao` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_estacao_chegada`
    FOREIGN KEY (`estacao_chegada`)
    REFERENCES `ferroviaria`.`estacao` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`lugares`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`lugares` (
  `comboio` INT NOT NULL,
  `carruagem` INT NOT NULL,
  `lugar` INT NOT NULL,
  PRIMARY KEY (`comboio`, `carruagem`, `lugar`),
  CONSTRAINT `fk_lugares_comboio`
    FOREIGN KEY (`comboio`)
    REFERENCES `ferroviaria`.`comboio` (`id_comboio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ferroviaria`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ferroviaria`.`reserva` (
  `id_reserva` INT NOT NULL,
  `preco` DECIMAL(3,2) NOT NULL,
  `data_reserva` DATETIME NOT NULL,
  `cliente` VARCHAR(40) NOT NULL,
  `viagem` INT NOT NULL,
  `comboio` INT NOT NULL,
  `carruagem` INT NOT NULL,
  `lugar` INT NOT NULL,
  PRIMARY KEY (`id_reserva`),
  INDEX `fk_reserva_cliente_idx` (`cliente` ASC),
  INDEX `fk_reserva_viagem_idx` (`viagem` ASC),
  INDEX `fk_reserva_lugares_idx` (`comboio` ASC, `carruagem` ASC, `lugar` ASC),
  CONSTRAINT `fk_reserva_cliente`
    FOREIGN KEY (`cliente`)
    REFERENCES `ferroviaria`.`cliente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reserva_viagem`
    FOREIGN KEY (`viagem`)
    REFERENCES `ferroviaria`.`viagem` (`id_viagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_lugares`
    FOREIGN KEY (`comboio` , `carruagem` , `lugar`)
    REFERENCES `ferroviaria`.`lugares` (`comboio` , `carruagem` , `lugar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
