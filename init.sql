-- --------------------------------------------------------
-- Hostitel:                     127.0.0.1
-- Verze serveru:                10.6.5-MariaDB-1:10.6.5+maria~focal - mariadb.org binary distribution
-- OS serveru:                   debian-linux-gnu
-- HeidiSQL Verze:               11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Exportování struktury databáze pro
DROP DATABASE IF EXISTS `warehouse`;
CREATE DATABASE IF NOT EXISTS `warehouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `warehouse`;

-- Exportování struktury pro tabulka warehouse.bill
DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` char(1) COLLATE utf8mb3_czech_ci NOT NULL,
  `paired` varchar(25) COLLATE utf8mb3_czech_ci NOT NULL,
  `unit_id` int(10) unsigned NOT NULL,
  `bill_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_unit` (`unit_id`),
  CONSTRAINT `FK_bill_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_czech_ci;

-- Exportování dat pro tabulku warehouse.bill: ~28 rows (přibližně)
DELETE FROM `bill`;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`id`, `type`, `paired`, `unit_id`, `bill_date`) VALUES
	(1, 'P', '435361', 2, '2019-07-22'),
	(2, 'V', '46558485', 6, '2019-07-23'),
	(3, 'P', '5241564', 3, '2019-07-23'),
	(4, 'V', '56465415', 8, '2019-07-24'),
	(5, 'V', '142352', 4, '2021-11-14'),
	(6, 'V', '123454', 8, '2021-11-14'),
	(7, 'P', '456467', 7, '2021-11-14'),
	(10, 'V', '356467', 6, '2021-11-14'),
	(11, 'P', '3467587', 2, '2021-11-14'),
	(12, 'V', '2344', 4, '2021-11-14'),
	(13, 'P', '3245', 2, '2021-11-14'),
	(15, 'P', '54356', 1, '2021-11-20'),
	(16, 'P', '9797', 1, '2021-11-21'),
	(17, 'P', '9797', 7, '2021-11-21'),
	(18, 'V', '979', 9, '2021-11-21'),
	(19, 'P', '333', 3, '2021-11-20'),
	(20, 'V', '333', 6, '2021-11-21'),
	(21, 'P', '123', 2, '2021-11-21'),
	(22, 'V', '123', 5, '2021-11-21'),
	(23, 'P', '321', 3, '2021-11-21'),
	(31, 'V', '54356', 8, '2021-11-21'),
	(33, 'P', '333', 7, '2021-11-21'),
	(34, 'V', '333', 6, '2021-11-21'),
	(36, 'P', '54356', 1, '2021-12-04'),
	(37, 'V', '3456', 9, '2021-12-04'),
	(38, 'P', '1234', 3, '2022-01-06'),
	(39, 'V', '123', 6, '2022-01-06'),
	(40, 'P', '54356', 1, '2022-01-08');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- Exportování struktury pro tabulka warehouse.part
DROP TABLE IF EXISTS `part`;
CREATE TABLE IF NOT EXISTS `part` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) COLLATE utf8mb3_czech_ci NOT NULL,
  `price_id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8mb3_czech_ci NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  `min` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_spare_part_price` (`price_id`),
  CONSTRAINT `FK_spare_part_price` FOREIGN KEY (`price_id`) REFERENCES `price` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_czech_ci;

-- Exportování dat pro tabulku warehouse.part: ~22 rows (přibližně)
DELETE FROM `part`;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` (`id`, `code`, `price_id`, `name`, `qty`, `min`) VALUES
	(1, '279', 3, 'víko malé', 0, 5),
	(2, '123', 4, 'víko velké', 0, 10),
	(3, '653', 2, 'víko střední', 0, 4),
	(4, '543', 4, 'ozubené kolo', 0, 9),
	(5, '257', 1, 'hadička', 0, 1),
	(6, '858', 5, 'ventilátor', 0, 1),
	(7, '987', 4, 'motor', 0, 1),
	(8, '584', 4, 'mlýnek', 0, 0),
	(9, '259', 3, 'těsnění', 0, 0),
	(10, '547', 6, 'deska elektroniky', 0, 0),
	(11, '654', 1, 'těsnění', 0, 0),
	(12, '354', 2, 'kryt', 0, 0),
	(13, '745', 3, 'vypínač', 0, 5),
	(14, '425', 2, 'těsnění', 0, 0),
	(15, '254', 3, 'motor', 0, 0),
	(16, '158', 2, 'přepínač', 0, 0),
	(17, '135', 2, 'pojistka', 0, 20),
	(18, '750', 4, 'motor', 0, 0),
	(19, '784', 5, 'metly', 0, 0),
	(20, '698', 3, 'hadice', 0, 0),
	(21, '951', 3, 'držák', 0, 0),
	(22, '982', 5, 'mlýnek', 0, 0);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;

-- Exportování struktury pro tabulka warehouse.movement
DROP TABLE IF EXISTS `movement`;
CREATE TABLE IF NOT EXISTS `movement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bill_id` int(10) unsigned NOT NULL,
  `part_id` int(10) unsigned NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_movement_bill` (`bill_id`),
  KEY `FK_movement_spare_part` (`part_id`) USING BTREE,
  CONSTRAINT `FK_movement_bill` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_movement_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_czech_ci;

-- Exportování struktury pro trigger warehouse.movement_delete
DROP TRIGGER IF EXISTS `movement_delete`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER movement_delete
    AFTER DELETE
    ON movement FOR EACH ROW
BEGIN
    SELECT b.`type` INTO @b_type FROM bill b WHERE b.id = OLD.bill_id;
    IF @b_type = 'P' THEN UPDATE part p SET p.qty = p.qty - OLD.qty WHERE p.id = OLD.part_id;
	 ELSEIF @b_type = 'V' THEN UPDATE part p SET p.qty = p.qty + OLD.qty WHERE p.id = OLD.part_id;	 
	 END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Exportování struktury pro trigger warehouse.movement_insert
DROP TRIGGER IF EXISTS `movement_insert`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER movement_insert
    AFTER INSERT
    ON movement FOR EACH ROW
BEGIN
    SELECT b.`type` INTO @b_type FROM bill b WHERE b.id = NEW.bill_id;
    IF @b_type = 'P' THEN UPDATE part p SET p.qty = p.qty + NEW.qty WHERE p.id = NEW.part_id;
	 ELSEIF @b_type = 'V' THEN UPDATE part p SET p.qty = p.qty - NEW.qty WHERE p.id = NEW.part_id;	 
	 END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Exportování struktury pro trigger warehouse.movement_update
DROP TRIGGER IF EXISTS `movement_update`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER movement_update
    AFTER UPDATE
    ON movement FOR EACH ROW
BEGIN
    SELECT b.`type` INTO @b_type FROM bill b WHERE b.id = NEW.bill_id;
    IF @b_type = 'P' THEN UPDATE part p SET p.qty = p.qty + NEW.qty - OLD.qty WHERE p.id = NEW.part_id;
	 ELSEIF @b_type = 'V' THEN UPDATE part p SET p.qty = p.qty + OLD.qty - NEW.qty WHERE p.id = NEW.part_id;	 
	 END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Exportování dat pro tabulku warehouse.movement: ~31 rows (přibližně)
DELETE FROM `movement`;
/*!40000 ALTER TABLE `movement` DISABLE KEYS */;
INSERT INTO `movement` (`id`, `bill_id`, `part_id`, `qty`) VALUES
	(1, 1, 1, 2),
	(2, 1, 2, 8),
	(4, 1, 4, 8),
	(6, 1, 6, 11),
	(7, 2, 6, 3),
	(8, 2, 2, 2),
	(9, 2, 4, 2),
	(12, 2, 1, 2),
	(13, 3, 1, 5),
	(14, 3, 8, 5),
	(15, 4, 6, 2),
	(16, 4, 4, 2),
	(19, 1, 5, 5),
	(20, 1, 3, 3),
	(21, 1, 12, 30),
	(22, 1, 19, 5),
	(24, 1, 10, 7),
	(25, 1, 9, 2),
	(27, 1, 19, 4),
	(31, 1, 11, 6),
	(32, 1, 21, 3),
	(33, 1, 7, 3),
	(34, 1, 20, 11),
	(35, 39, 2, 5),
	(36, 39, 12, 4);
/*!40000 ALTER TABLE `movement` ENABLE KEYS */;

-- Exportování struktury pro tabulka warehouse.price
DROP TABLE IF EXISTS `price`;
CREATE TABLE IF NOT EXISTS `price` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(6) COLLATE utf8mb3_czech_ci NOT NULL,
  `purchase` decimal(10,2) unsigned NOT NULL,
  `sale` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_czech_ci;

-- Exportování dat pro tabulku warehouse.price: ~10 rows (přibližně)
DELETE FROM `price`;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` (`id`, `name`, `purchase`, `sale`) VALUES
	(1, '11A', 0.20, 5.00),
	(2, '12B', 10.00, 250.00),
	(3, '12C', 11.00, 275.00),
	(4, '13D', 15.20, 380.00),
	(5, '14E', 20.00, 500.00),
	(6, '20A', 32.00, 800.00),
	(7, '21A', 36.00, 900.00),
	(10, '22B', 100.00, 2500.00),
	(11, '22C', 120.00, 3000.00),
	(12, '22D', 140.00, 3500.00);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;

-- Exportování struktury pro tabulka warehouse.unit
DROP TABLE IF EXISTS `unit`;
CREATE TABLE IF NOT EXISTS `unit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` char(1) COLLATE utf8mb3_czech_ci NOT NULL,
  `name` varchar(30) COLLATE utf8mb3_czech_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_czech_ci;

-- Exportování dat pro tabulku warehouse.unit: ~10 rows (přibližně)
DELETE FROM `unit`;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` (`id`, `type`, `name`) VALUES
	(1, 'P', 'Dodavatel 1'),
	(2, 'P', 'Dodavatel 2'),
	(3, 'P', 'Dodavatel 3'),
	(4, 'V', 'Odběratel 1'),
	(5, 'V', 'Odběratel 2'),
	(6, 'V', 'Odběratel 3'),
	(7, 'P', 'Dodavatel 4'),
	(8, 'V', 'Odběratel 4'),
	(9, 'V', 'Odběratel 5'),
	(13, 'P', 'Dodavatel 5');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
