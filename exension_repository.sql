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
  `extensionID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `version` varchar(50) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `numberOfDownloads` int(11) NOT NULL DEFAULT 0,
  `tags` varchar(50) NOT NULL,
  `File_Id` int(11) unsigned DEFAULT 0,
  `gitExtensionInfoID` int(11) unsigned NOT NULL,
  `uploadDate` varchar(50) DEFAULT NULL,
  `featured` tinyint(2) NOT NULL DEFAULT 1,
  `approved` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`extensionID`),
  KEY `gitExtensionInfoID` (`gitExtensionInfoID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extension: ~15 rows (approximately)
/*!40000 ALTER TABLE `extension` DISABLE KEYS */;
INSERT INTO `extension` (`extensionID`, `name`, `description`, `version`, `owner`, `numberOfDownloads`, `tags`, `File_Id`, `gitExtensionInfoID`, `uploadDate`, `featured`, `approved`) VALUES
	(1, 'Apple Extension', 'bla bla bla blaaaaaaah', '1.0', 'drago', 3, 'super awesome', 0, 1, '16/08/2018', 0, 0),
	(2, 'IBM Extension', 'OLELE LELELE', '1.0', 'drago', 5, 'veryGood great', 0, 2, '16/08/2018', 0, 0),
	(3, 'Rovena Galactic', 'LULU LULI LUUUUU', '1.0', 'drago', 1, 'notGood blah', 0, 3, '12/08/2018', 0, 0),
	(4, 'Gardner Super', 'DJUPAMPAAAAAAAA OLE', '1.1', 'rosi', 7, 'dasdas', 0, 4, '05/08/2018', 0, 0),
	(5, 'Pliska Cosmos', 'O MUDRI VOINIKO', '1.2', 'rosi', 3, 'sadasdas', 0, 5, '07/08/2018', 1, 0),
	(6, 'Bravos Extension', 'JIVOTA E RADOST I TUGA ', '1.1', 'rosi', 4, 'asdasdasd', 0, 6, '09/08/2018', 0, 0),
	(7, 'Test SuperTest', 'SUS 100 KILOMETRA V CHAS', '1.5', 'drago', 7, 'ksadklasda', 0, 7, '14/08/2018', 0, 0),
	(8, 'Great Overall Extension', '100 patrona ', '1.3', 'pesho', 2, 'asdasdas', 0, 8, '15/08/2018', 1, 0),
	(9, 'Roberto Extension', 'Karai si kolata lelelel', '1.1', 'pesho', 10, 'asdasdas', 0, 9, '16/08/2018', 0, 0),
	(10, 'Britney Spears Extension', 'Oops I did it again', '1.2', 'pesho', 4, 'adasdasdasd', 0, 10, '15/08/2018', 0, 0),
	(13, 'Gosho Extension', 'great overall ext', '1.0', 'gosho', 0, 'wow great theBest', 0, 11, '15/08/2018', 0, 0),
	(14, 'Sony Extension', 'New Tech Ext', '1.0', 'gosho', 0, 'sonyExt newExt', 0, 12, '15/08/2018', 0, 0),
	(18, 'Misho Extension', 'My first extension yayyyy', '1.0', 'misho', 0, 'great firstExtension', 0, 16, '15/08/2018', 1, 1),
	(25, 'Manually Created Ext', 'First Manually Created Ext with file', '1.0', 'drago', 0, 'first great', 0, 23, '15/08/2018', 0, 0),
	(26, 'Burning Extension', 'Burning...', '1.0', 'drago', 0, 'burning yes', 0, 25, '15/08/2018', 1, 0);
/*!40000 ALTER TABLE `extension` ENABLE KEYS */;

-- Dumping structure for table extension_repository.files
CREATE TABLE IF NOT EXISTS `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `File_Name` varchar(50) NOT NULL DEFAULT '0',
  `File_Data` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.files: ~3 rows (approximately)
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_extension_info
CREATE TABLE IF NOT EXISTS `git_extension_info` (
  `gitExtensionInfoID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openIssues` int(11) DEFAULT NULL,
  `pullRequests` int(11) DEFAULT NULL,
  `lastCommitDate` varchar(50) DEFAULT NULL,
  `gitRepoLink` varchar(100) NOT NULL,
  PRIMARY KEY (`gitExtensionInfoID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_extension_info: ~8 rows (approximately)
/*!40000 ALTER TABLE `git_extension_info` DISABLE KEYS */;
INSERT INTO `git_extension_info` (`gitExtensionInfoID`, `openIssues`, `pullRequests`, `lastCommitDate`, `gitRepoLink`) VALUES
	(11, 0, 0, NULL, 'www.tctc.com'),
	(12, 0, 0, NULL, 'www.sony.com'),
	(16, 0, 0, NULL, 'wow.com'),
	(21, 0, 0, NULL, 'asdas'),
	(22, 0, 0, NULL, 'asdas'),
	(23, 0, 0, NULL, 'www.wow.com'),
	(24, 0, 0, NULL, 'www.burning.com'),
	(25, 0, 0, NULL, 'www.burning.com');
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
