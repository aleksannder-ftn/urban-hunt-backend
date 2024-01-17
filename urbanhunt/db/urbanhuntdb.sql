-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema urbanhuntdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema urbanhuntdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `urbanhuntdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `urbanhuntdb` ;

-- -----------------------------------------------------
-- Table `urbanhuntdb`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`administrator` (
                                                             `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                             `address` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone_number` INT NOT NULL,
    `role` ENUM('GUEST', 'ADMINISTRATOR', 'AGENT', 'OWNER') NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `active` BIT(1) NOT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`agent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`agent` (
                                                     `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `address` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone_number` INT NOT NULL,
    `role` ENUM('GUEST', 'ADMINISTRATOR', 'AGENT', 'OWNER') NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `average_rating` FLOAT NOT NULL,
    `agency_id` BIGINT NOT NULL,
    `active` BIT(1) NOT NULL,
    PRIMARY KEY (`user_id`),
    INDEX `FK2karykbqlyw19a0e80gcf9ch9` (`agency_id` ASC) VISIBLE,
    CONSTRAINT `FK2karykbqlyw19a0e80gcf9ch9`
    FOREIGN KEY (`agency_id`)
    REFERENCES `urbanhuntdb`.`agency` (`agency_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`owner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`owner` (
                                                     `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `active` BIT(1) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone_number` INT NOT NULL,
    `role` ENUM('GUEST', 'ADMINISTRATOR', 'AGENT', 'OWNER') NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`agency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`agency` (
                                                      `agency_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `report` DOUBLE NULL DEFAULT NULL,
                                                      `owner_id` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`agency_id`),
    UNIQUE INDEX `UK_reh4jl3k81cc1w6auo5p3wuwm` (`owner_id` ASC) VISIBLE,
    CONSTRAINT `FK6yi3kwsi7tlcv3urgi1xihftt`
    FOREIGN KEY (`owner_id`)
    REFERENCES `urbanhuntdb`.`agent` (`user_id`),
    CONSTRAINT `FKs3x3bxq8bik43y9b5n6v56rjr`
    FOREIGN KEY (`owner_id`)
    REFERENCES `urbanhuntdb`.`owner` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`guest` (
                                                     `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `address` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone_number` INT NOT NULL,
    `role` ENUM('GUEST', 'ADMINISTRATOR', 'AGENT', 'OWNER') NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `active` BIT(1) NOT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`real_estate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`real_estate` (
                                                           `real_estate_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `active` BIT(1) NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `price` FLOAT NOT NULL,
    `rating` FLOAT NULL DEFAULT NULL,
    `real_estate_type` ENUM('HOUSE', 'APARTMENT', 'OFFICE') NOT NULL,
    `surface_area` FLOAT NULL DEFAULT NULL,
    `transaction_type` ENUM('SALE', 'RENT') NULL DEFAULT NULL,
    `view_count` INT NULL DEFAULT NULL,
    `agency_agency_id` BIGINT NULL DEFAULT NULL,
    `agent_id` BIGINT NOT NULL,
    PRIMARY KEY (`real_estate_id`),
    INDEX `FKmagtujqg3lfun66gn0l0dqomi` (`agency_agency_id` ASC) VISIBLE,
    INDEX `FKemkj5m5yn3mtlx296sqcg0sdx` (`agent_id` ASC) VISIBLE,
    CONSTRAINT `FKemkj5m5yn3mtlx296sqcg0sdx`
    FOREIGN KEY (`agent_id`)
    REFERENCES `urbanhuntdb`.`agent` (`user_id`),
    CONSTRAINT `FKmagtujqg3lfun66gn0l0dqomi`
    FOREIGN KEY (`agency_agency_id`)
    REFERENCES `urbanhuntdb`.`agency` (`agency_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`guest_real_estate_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`guest_real_estate_rating` (
                                                                        `guest_id` BIGINT NOT NULL,
                                                                        `is_liked` BIT(1) NULL DEFAULT NULL,
    `real_estate_id` BIGINT NOT NULL,
    PRIMARY KEY (`guest_id`, `real_estate_id`),
    INDEX `FK1jkic418x8rubi34xm59jmk1a` (`real_estate_id` ASC) VISIBLE,
    CONSTRAINT `FK1jkic418x8rubi34xm59jmk1a`
    FOREIGN KEY (`real_estate_id`)
    REFERENCES `urbanhuntdb`.`real_estate` (`real_estate_id`),
    CONSTRAINT `FKjnhtu9xexsfumbr2118rajsma`
    FOREIGN KEY (`guest_id`)
    REFERENCES `urbanhuntdb`.`guest` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`image` (
                                                     `image_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `image_path` VARCHAR(255) NOT NULL,
    `real_estate_id` BIGINT NOT NULL,
    PRIMARY KEY (`image_id`),
    INDEX `FKmfm6ojv3rbvpngu4gt9kqlip8` (`real_estate_id` ASC) VISIBLE,
    CONSTRAINT `FKmfm6ojv3rbvpngu4gt9kqlip8`
    FOREIGN KEY (`real_estate_id`)
    REFERENCES `urbanhuntdb`.`real_estate` (`real_estate_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `urbanhuntdb`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `urbanhuntdb`.`tour` (
                                                    `tour_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `accepted` BIT(1) NOT NULL,
    `finished` BIT(1) NOT NULL,
    `start_time` DATETIME(6) NOT NULL,
    `agency_agency_id` BIGINT NULL DEFAULT NULL,
    `agent_user_id` BIGINT NULL DEFAULT NULL,
    `guest_user_id` BIGINT NULL DEFAULT NULL,
    `real_estate_real_estate_id` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`tour_id`),
    INDEX `FK75me5315uv8ycq1wj1oi2g6wr` (`agency_agency_id` ASC) VISIBLE,
    INDEX `FKjl3ibwtro2fu391vggqop4j2h` (`agent_user_id` ASC) VISIBLE,
    INDEX `FKmt4aan50a8r5bojry5ch71sd6` (`guest_user_id` ASC) VISIBLE,
    INDEX `FKlea8eokq4gudgoes32t2dlirl` (`real_estate_real_estate_id` ASC) VISIBLE,
    CONSTRAINT `FK75me5315uv8ycq1wj1oi2g6wr`
    FOREIGN KEY (`agency_agency_id`)
    REFERENCES `urbanhuntdb`.`agency` (`agency_id`),
    CONSTRAINT `FKjl3ibwtro2fu391vggqop4j2h`
    FOREIGN KEY (`agent_user_id`)
    REFERENCES `urbanhuntdb`.`agent` (`user_id`),
    CONSTRAINT `FKlea8eokq4gudgoes32t2dlirl`
    FOREIGN KEY (`real_estate_real_estate_id`)
    REFERENCES `urbanhuntdb`.`real_estate` (`real_estate_id`),
    CONSTRAINT `FKmt4aan50a8r5bojry5ch71sd6`
    FOREIGN KEY (`guest_user_id`)
    REFERENCES `urbanhuntdb`.`guest` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
