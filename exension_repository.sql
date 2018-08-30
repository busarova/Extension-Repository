-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for extension_repository
CREATE DATABASE IF NOT EXISTS `extension_repository` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `extension_repository`;

-- Dumping structure for table extension_repository.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL DEFAULT 1,
  `last_successful_sync` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.admin: ~1 rows (approximately)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `last_successful_sync`) VALUES
	(1, '2018-08-30');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table extension_repository.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`),
  CONSTRAINT `FK__users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.authorities: ~9 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('Aladin', 'ROLE_USER'),
	('drago', 'ROLE_ADMIN'),
	('drago', 'ROLE_USER'),
	('gosho', 'ROLE_USER'),
	('misho', 'ROLE_USER'),
	('pesho', 'ROLE_ADMIN'),
	('pesho', 'ROLE_USER'),
	('rosi', 'ROLE_ADMIN'),
	('rosi', 'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extension
CREATE TABLE IF NOT EXISTS `extension` (
  `extension_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `version` varchar(50) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `number_of_downloads` int(11) NOT NULL DEFAULT 0,
  `tags` varchar(50) NOT NULL,
  `file_id` int(11) NOT NULL,
  `git_id` int(11) NOT NULL,
  `upload_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `featured` tinyint(2) NOT NULL DEFAULT 1,
  `approved` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`extension_id`),
  KEY `gitExtensionInfoID` (`git_id`),
  KEY `FK_extension_files` (`file_id`),
  CONSTRAINT `FK_extension_files` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`),
  CONSTRAINT `FK_extension_git_extension_info` FOREIGN KEY (`git_id`) REFERENCES `git_extension_info` (`git_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extension: ~1 rows (approximately)
/*!40000 ALTER TABLE `extension` DISABLE KEYS */;
INSERT INTO `extension` (`extension_id`, `name`, `description`, `version`, `owner`, `number_of_downloads`, `tags`, `file_id`, `git_id`, `upload_date`, `featured`, `approved`) VALUES
	(27, 'Masha Extension', 'Great extension about marketplaces', '1.0', 'drago', 0, '#great #marketplace #extensions', 29, 27, '2018-08-30 10:28:01', 1, 0),
	(28, 'Apple Extension', 'Apple extension with no GitHubLink', '1.2', 'drago', 0, '#great #apple #wow', 31, 28, '2018-08-30 11:16:21', 1, 0),
	(29, 'Telerik Extension', 'Oh great Telerik fill me with knowledge', '1.5', 'drago', 0, '#telerik #great #awesome', 32, 29, '2018-08-30 11:17:17', 0, 0),
	(30, 'Smoking Extension', 'smoking...', '1.0', 'drago', 0, '#smoking #great', 33, 30, '2018-08-30 11:17:42', 1, 0),
	(31, 'Oh My God Extension', 'Oh my god', '1.0', 'drago', 0, '#god #great #wow', 34, 31, '2018-08-30 11:18:54', 1, 0),
	(32, 'Brabus Extension', 'Just hit the GAAAAAS', '1.1', 'drago', 0, '#brabus #great #wow #gas', 35, 32, '2018-08-30 11:19:38', 0, 0),
	(33, 'The Cool Extension', 'I am cool...', '1.3', 'rosi', 0, '#cool #great #chillout', 36, 33, '2018-08-30 11:20:27', 1, 0),
	(34, 'Roberto Extension', 'Roberto Tombaaaa', '1.4', 'rosi', 0, '#roberto #wow', 37, 34, '2018-08-30 11:20:44', 1, 0),
	(35, 'Britney Spears Extension', 'Hit me baby one more time', '1.8', 'rosi', 0, '#britney #wow #baby', 38, 35, '2018-08-30 11:21:17', 0, 0),
	(36, 'Misho Extension', 'My first extension yayyyy', '1.0', 'misho', 0, '#misho #great #wow', 39, 36, '2018-08-30 11:22:18', 1, 0),
	(37, 'WoW extensions', 'It is just WOOOW', '1.1', 'misho', 0, '#wow #new', 40, 37, '2018-08-30 11:22:50', 1, 0),
	(38, 'Gosho Extension', 'Just Gosho extension', '1.1', 'gosho', 0, '#gosho #great', 41, 38, '2018-08-30 11:23:43', 1, 0),
	(39, 'Mountain Extension', 'Oh the great beauty of mountains...', '1.9', 'gosho', 0, '#mountains #great', 42, 39, '2018-08-30 11:24:13', 0, 0);
/*!40000 ALTER TABLE `extension` ENABLE KEYS */;

-- Dumping structure for table extension_repository.files
CREATE TABLE IF NOT EXISTS `files` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) DEFAULT NULL,
  `file_data` longblob DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.files: ~1 rows (approximately)
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` (`file_id`, `file_name`, `file_data`) VALUES
	(29, NULL, NULL),
	(30, NULL, NULL),
	(31, NULL, NULL),
	(32, NULL, NULL),
	(33, NULL, NULL),
	(34, NULL, NULL),
	(35, NULL, NULL),
	(36, NULL, NULL),
	(37, NULL, NULL),
	(38, NULL, NULL),
	(39, NULL, NULL),
	(40, NULL, NULL),
	(41, NULL, NULL),
	(42, NULL, NULL);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_extension_info
CREATE TABLE IF NOT EXISTS `git_extension_info` (
  `git_id` int(11) NOT NULL AUTO_INCREMENT,
  `open_issues` int(11) DEFAULT NULL,
  `pull_requests` int(11) DEFAULT NULL,
  `last_commit_date` date DEFAULT NULL,
  `git_repo_link` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`git_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_extension_info: ~1 rows (approximately)
/*!40000 ALTER TABLE `git_extension_info` DISABLE KEYS */;
INSERT INTO `git_extension_info` (`git_id`, `open_issues`, `pull_requests`, `last_commit_date`, `git_repo_link`) VALUES
	(27, 0, 0, '2018-08-29', 'https://github.com/mmgrigorova/ExtensionMarket42'),
	(28, 0, 0, NULL, NULL),
	(29, 0, 0, NULL, NULL),
	(30, 0, 0, NULL, NULL),
	(31, 0, 0, NULL, NULL),
	(32, 0, 0, NULL, NULL),
	(33, 0, 0, NULL, NULL),
	(34, 0, 0, NULL, NULL),
	(35, 0, 0, NULL, NULL),
	(36, 0, 0, NULL, NULL),
	(37, 0, 0, NULL, NULL),
	(38, 0, 0, NULL, NULL),
	(39, 0, 0, NULL, NULL);
/*!40000 ALTER TABLE `git_extension_info` ENABLE KEYS */;

-- Dumping structure for table extension_repository.tags
CREATE TABLE IF NOT EXISTS `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `owners_id_list` varchar(1000) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.tags: ~3 rows (approximately)
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` (`tag_id`, `name`, `owners_id_list`) VALUES
	(9, '#great', '27 0 28 29 30 31 32 33 36 38 39'),
	(10, '#marketplace', '27'),
	(11, '#extensions', '27'),
	(12, '#apple', '0 28'),
	(13, '#wow', '0 28 31 32 34 35 36 37'),
	(14, '#telerik', '29'),
	(15, '#awesome', '29'),
	(16, '#smoking', '30'),
	(17, '#god', '31'),
	(18, '#brabus', '32'),
	(19, '#gas', '32'),
	(20, '#cool', '33'),
	(21, '#chillout', '33'),
	(22, '#roberto', '34'),
	(23, '#britney', '35'),
	(24, '#baby', '35'),
	(25, '#misho', '36'),
	(26, '#new', '37'),
	(27, '#gosho', '38'),
	(28, '#mountains', '39');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users: ~6 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('Aladin', '{bcrypt}$2a$10$Fwf3q1Az8UPmlXU4FAQHIuhuXmok4F/t3SMwmgbFaIjlJZGEo3S1m', 1),
	('drago', '{noop}123', 1),
	('gosho', '{noop}123', 1),
	('misho', '{noop}123', 1),
	('pesho', '{noop}123', 1),
	('rosi', '{noop}123', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
