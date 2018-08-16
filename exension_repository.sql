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

-- Dumping data for table extension_repository.authorities: ~6 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('drago', 'ROLE_ADMIN'),
	('drago', 'ROLE_USER'),
	('gosho', 'ROLE_USER'),
	('misho', 'ROLE_USER'),
	('pesho', 'ROLE_ADMIN'),
	('pesho', 'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table extension_repository.download_file
CREATE TABLE IF NOT EXISTS `download_file` (
  `downloadFileID` int(11) NOT NULL AUTO_INCREMENT,
  `file` blob NOT NULL,
  PRIMARY KEY (`downloadFileID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.download_file: ~0 rows (approximately)
/*!40000 ALTER TABLE `download_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `download_file` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extensions
CREATE TABLE IF NOT EXISTS `extensions` (
  `extensionID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `version` varchar(50) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `numberOfDownloads` int(11) NOT NULL DEFAULT 0,
  `tags` varchar(50) NOT NULL,
  `downloadFileID` int(11) NOT NULL,
  `gitExtensionInfoID` int(11) NOT NULL,
  `uploadDate` varchar(50) NOT NULL,
  `featured` tinyint(2) NOT NULL,
  PRIMARY KEY (`extensionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extensions: ~0 rows (approximately)
/*!40000 ALTER TABLE `extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_extension_info
CREATE TABLE IF NOT EXISTS `git_extension_info` (
  `gitExtensionInfoID` int(11) NOT NULL AUTO_INCREMENT,
  `openIssues` int(11) NOT NULL,
  `pullRequests` int(11) NOT NULL,
  `lastCommitDate` varchar(50) NOT NULL,
  `gitRepoLink` varchar(100) NOT NULL,
  PRIMARY KEY (`gitExtensionInfoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_extension_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `git_extension_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `git_extension_info` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('drago', '{noop}123', 1),
	('gosho', '{noop}pass2', 1),
	('misho', '{noop}pass3', 1),
	('pesho', '{noop}123', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
