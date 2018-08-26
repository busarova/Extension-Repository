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

-- Dumping structure for table extension_repository.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`),
  CONSTRAINT `FK__users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.authorities: ~8 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
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
  `upload_date` varchar(50) NOT NULL DEFAULT '0',
  `featured` tinyint(2) NOT NULL DEFAULT 1,
  `approved` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`extension_id`),
  KEY `gitExtensionInfoID` (`git_id`),
  KEY `FK_extension_files` (`file_id`),
  CONSTRAINT `FK_extension_files` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`),
  CONSTRAINT `FK_extension_git_extension_info` FOREIGN KEY (`git_id`) REFERENCES `git_extension_info` (`git_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extension: ~1 rows (approximately)
/*!40000 ALTER TABLE `extension` DISABLE KEYS */;
INSERT INTO `extension` (`extension_id`, `name`, `description`, `version`, `owner`, `number_of_downloads`, `tags`, `file_id`, `git_id`, `upload_date`, `featured`, `approved`) VALUES
	(1, 'New Test Extension', 'SUPER DUPER TROOPER', '1.0', 'drago', 0, 'super trooper', 1, 1, '15/08/2018', 1, 0);
/*!40000 ALTER TABLE `extension` ENABLE KEYS */;

-- Dumping structure for table extension_repository.files
CREATE TABLE IF NOT EXISTS `files` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) DEFAULT NULL,
  `file_data` longblob DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.files: ~0 rows (approximately)
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` (`file_id`, `file_name`, `file_data`) VALUES
	(1, NULL, NULL);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_extension_info
CREATE TABLE IF NOT EXISTS `git_extension_info` (
  `git_id` int(11) NOT NULL AUTO_INCREMENT,
  `open_issues` int(11) DEFAULT NULL,
  `pull_requests` int(11) DEFAULT NULL,
  `last_commit_date` varchar(50) DEFAULT NULL,
  `git_repo_link` varchar(100) NOT NULL,
  PRIMARY KEY (`git_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_extension_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `git_extension_info` DISABLE KEYS */;
INSERT INTO `git_extension_info` (`git_id`, `open_issues`, `pull_requests`, `last_commit_date`, `git_repo_link`) VALUES
	(1, 0, 0, NULL, 'wow.com');
/*!40000 ALTER TABLE `git_extension_info` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users: ~5 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('drago', '{noop}123', 1),
	('gosho', '{noop}123', 1),
	('misho', '{noop}123', 1),
	('pesho', '{noop}123', 1),
	('rosi', '{noop}123', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
