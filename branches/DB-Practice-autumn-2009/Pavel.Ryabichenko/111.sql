SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `billing` ;
CREATE SCHEMA IF NOT EXISTS `billing` DEFAULT CHARACTER SET cp1251 ;

-- -----------------------------------------------------
-- Table `billing`.`TARIFF`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`TARIFF` ;

CREATE  TABLE IF NOT EXISTS `billing`.`TARIFF` (
  `tariff` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `type` VARCHAR(255) NOT NULL ,
  `months` INT(11) NOT NULL ,
  `days` INT(11) NOT NULL ,
  `speed` INT(11) NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `traffic` DECIMAL(10,3) NOT NULL ,
  `traffic_add` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`tariff`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2011
DEFAULT CHARACTER SET = cp1251;


-- -----------------------------------------------------
-- Table `billing`.`STREETS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`STREETS` ;

CREATE  TABLE IF NOT EXISTS `billing`.`STREETS` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `street` VARCHAR(45) NULL ,
  `district` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = cp1251;

CREATE UNIQUE INDEX `DS` USING BTREE ON `billing`.`STREETS` (`street` ASC, `district` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`USERS` ;

CREATE  TABLE IF NOT EXISTS `billing`.`USERS` (
  `n_user` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `street` INT UNSIGNED NOT NULL ,
  `house` VARCHAR(255) NOT NULL ,
  `korp` VARCHAR(255) NOT NULL ,
  `kv` VARCHAR(255) NOT NULL ,
  `tariff_group` VARCHAR(255) NOT NULL ,
  `tariff` VARCHAR(255) NOT NULL DEFAULT '' ,
  `next_tariff` VARCHAR(255) NOT NULL DEFAULT '' ,
  `tariff_start` DATE NOT NULL ,
  `tariff_end` DATE NOT NULL ,
  `block_type` VARCHAR(255) NOT NULL ,
  `money` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
  `block_money` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
  `promise_money` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
  `sms_phone` BIGINT(11) NOT NULL ,
  `sms_allow` INT(1) NOT NULL DEFAULT '1' ,
  `sms_bad_number` INT(11) NOT NULL ,
  PRIMARY KEY (`n_user`) ,
  CONSTRAINT `fk_USERS_TARIFF1`
    FOREIGN KEY (`tariff` )
    REFERENCES `billing`.`TARIFF` (`tariff` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USERS_TARIFF2`
    FOREIGN KEY (`next_tariff` )
    REFERENCES `billing`.`TARIFF` (`tariff` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USERS_STREETS1`
    FOREIGN KEY (`street` )
    REFERENCES `billing`.`STREETS` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 128053
DEFAULT CHARACTER SET = cp1251;

CREATE INDEX `fk_USERS_TARIFF1` ON `billing`.`USERS` (`tariff` ASC) ;

CREATE INDEX `fk_USERS_TARIFF2` ON `billing`.`USERS` (`next_tariff` ASC) ;

CREATE INDEX `fk_USERS_STREETS1` ON `billing`.`USERS` (`street` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`LOGINS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`LOGINS` ;

CREATE  TABLE IF NOT EXISTS `billing`.`LOGINS` (
  `n_login` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `n_user` INT(10) UNSIGNED NOT NULL DEFAULT '0' ,
  `login` VARCHAR(255) NOT NULL DEFAULT '' ,
  `password` VARCHAR(255) NOT NULL DEFAULT '' ,
  `ip_loc` VARCHAR(255) NOT NULL DEFAULT '' ,
  `ip_inet` VARCHAR(255) NOT NULL DEFAULT '' ,
  `mac` VARCHAR(255) NOT NULL ,
  `cid` VARCHAR(255) NOT NULL ,
  `rid` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`n_login`, `n_user`) ,
  CONSTRAINT `fk_LOGINS_USERS1`
    FOREIGN KEY (`n_user` )
    REFERENCES `billing`.`USERS` (`n_user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 128053
DEFAULT CHARACTER SET = cp1251;

CREATE INDEX `fk_LOGINS_USERS1` ON `billing`.`LOGINS` (`n_user` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`MONEY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`MONEY` ;

CREATE  TABLE IF NOT EXISTS `billing`.`MONEY` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `deleted` ENUM('LOG','NO','YES') NOT NULL DEFAULT 'NO' ,
  `n_user` INT(10) UNSIGNED NOT NULL DEFAULT '0' ,
  `date` DATE NOT NULL ,
  `end_date` DATE NOT NULL ,
  `money` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
  `money_group` VARCHAR(255) NOT NULL ,
  `type` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`, `n_user`) ,
  CONSTRAINT `fk_MONEY_USERS1`
    FOREIGN KEY (`n_user` )
    REFERENCES `billing`.`USERS` (`n_user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 715615
DEFAULT CHARACTER SET = cp1251;

CREATE INDEX `N_USER` ON `billing`.`MONEY` (`n_user` ASC, `date` ASC) ;

CREATE INDEX `TYPE` ON `billing`.`MONEY` (`type` ASC) ;

CREATE INDEX `DATE` ON `billing`.`MONEY` (`date` ASC) ;

CREATE INDEX `fk_MONEY_USERS1` ON `billing`.`MONEY` (`n_user` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`NUDGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`NUDGE` ;

CREATE  TABLE IF NOT EXISTS `billing`.`NUDGE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `n_user` INT(10) UNSIGNED NOT NULL ,
  `type` VARCHAR(255) NOT NULL ,
  `target_date` DATE NOT NULL ,
  `nudged_with` VARCHAR(255) NOT NULL ,
  `last_attempt` DATE NOT NULL ,
  `last_sms_send` DATE NOT NULL ,
  `last_sms_delivered` DATE NOT NULL ,
  `old` INT(1) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`, `n_user`, `type`, `target_date`) ,
  CONSTRAINT `fk_NUDGE_USERS1`
    FOREIGN KEY (`n_user` )
    REFERENCES `billing`.`USERS` (`n_user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3372
DEFAULT CHARACTER SET = cp1251;

CREATE UNIQUE INDEX `n_user_type` ON `billing`.`NUDGE` (`n_user` ASC, `type` ASC, `target_date` ASC) ;

CREATE INDEX `fk_NUDGE_USERS1` ON `billing`.`NUDGE` (`n_user` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`TARIFF_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`TARIFF_GROUP` ;

CREATE  TABLE IF NOT EXISTS `billing`.`TARIFF_GROUP` (
  `name` VARCHAR(255) NOT NULL ,
  `desc` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2007
DEFAULT CHARACTER SET = cp1251;


-- -----------------------------------------------------
-- Table `billing`.`TARIFF_IN_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`TARIFF_IN_GROUP` ;

CREATE  TABLE IF NOT EXISTS `billing`.`TARIFF_IN_GROUP` (
  `tariff` VARCHAR(255) NOT NULL ,
  `tariff_group` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`tariff`, `tariff_group`) ,
  CONSTRAINT `fk_TARIFF_IN_GROUP_TARIFF_GROUP1`
    FOREIGN KEY (`tariff_group` )
    REFERENCES `billing`.`TARIFF_GROUP` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TARIFF_IN_GROUP_TARIFF1`
    FOREIGN KEY (`tariff` )
    REFERENCES `billing`.`TARIFF` (`tariff` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5049
DEFAULT CHARACTER SET = cp1251;

CREATE INDEX `T_GROUP` ON `billing`.`TARIFF_IN_GROUP` (`tariff_group` ASC) ;


-- -----------------------------------------------------
-- Table `billing`.`TARIFF_IN_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billing`.`TARIFF_IN_GROUP` ;

CREATE  TABLE IF NOT EXISTS `billing`.`TARIFF_IN_GROUP` (
  `tariff` VARCHAR(255) NOT NULL ,
  `tariff_group` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`tariff`, `tariff_group`) ,
  CONSTRAINT `fk_TARIFF_IN_GROUP_TARIFF_GROUP1`
    FOREIGN KEY (`tariff_group` )
    REFERENCES `billing`.`TARIFF_GROUP` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TARIFF_IN_GROUP_TARIFF1`
    FOREIGN KEY (`tariff` )
    REFERENCES `billing`.`TARIFF` (`tariff` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5049
DEFAULT CHARACTER SET = cp1251;

CREATE INDEX `fk_TARIFF_IN_GROUP_TARIFF_GROUP1` ON `billing`.`TARIFF_IN_GROUP` (`tariff_group` ASC) ;

CREATE INDEX `fk_TARIFF_IN_GROUP_TARIFF1` ON `billing`.`TARIFF_IN_GROUP` (`tariff` ASC) ;


-- -----------------------------------------------------
-- Placeholder table for view `billing`.`SAMPLE_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billing`.`SAMPLE_VIEW` (`n_user` INT, `money` INT);

-- -----------------------------------------------------
-- View `billing`.`SAMPLE_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `billing`.`SAMPLE_VIEW` ;
DROP TABLE IF EXISTS `billing`.`SAMPLE_VIEW`;
USE billing;
CREATE  OR REPLACE VIEW billing.SAMPLE_VIEW AS  SELECT n_user, SUM(money) AS money FROM MONEY GROUP BY n_user;;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
