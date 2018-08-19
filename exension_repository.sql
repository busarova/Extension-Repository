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

-- Dumping structure for table extension_repository.download_file
CREATE TABLE IF NOT EXISTS `download_file` (
  `downloadFileID` int(11) NOT NULL AUTO_INCREMENT,
  `file` blob NOT NULL,
  PRIMARY KEY (`downloadFileID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.download_file: ~0 rows (approximately)
/*!40000 ALTER TABLE `download_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `download_file` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extension
CREATE TABLE IF NOT EXISTS `extension` (
  `extensionID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `version` varchar(50) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `numberOfDownloads` int(11) NOT NULL DEFAULT 0,
  `tags` varchar(50) NOT NULL,
  `downloadFileID` int(11) DEFAULT NULL,
  `gitExtensionInfoID` int(11) DEFAULT NULL,
  `uploadDate` varchar(50) DEFAULT NULL,
  `featured` tinyint(2) NOT NULL DEFAULT 1,
  `approved` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`extensionID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extension: ~12 rows (approximately)
/*!40000 ALTER TABLE `extension` DISABLE KEYS */;
INSERT INTO `extension` (`extensionID`, `name`, `description`, `version`, `owner`, `numberOfDownloads`, `tags`, `downloadFileID`, `gitExtensionInfoID`, `uploadDate`, `featured`, `approved`) VALUES
	(1, 'Apple Extension', 'bla bla bla blaaaaaaah', '1.0', 'drago', 3, 'super awesome', 1, 1, '16/08/2018', 0, 0),
	(2, 'IBM Extension', 'OLELE LELELE', '1.0', 'drago', 5, 'veryGood great', 2, 2, '16/08/2018', 0, 0),
	(3, 'Rovena Galactic', 'LULU LULI LUUUUU', '1.0', 'drago', 1, 'notGood blah', 3, 3, '12/08/2018', 1, 0),
	(4, 'Gardner Super', 'DJUPAMPAAAAAAAA OLE', '1.1', 'rosi', 7, 'dasdas', 4, 4, '05/08/2018', 0, 0),
	(5, 'Pliska Cosmos', 'O MUDRI VOINIKO', '1.2', 'rosi', 3, 'sadasdas', 5, 5, '07/08/2018', 1, 0),
	(6, 'Bravos Extension', 'JIVOTA E RADOST I TUGA ', '1.1', 'rosi', 4, 'asdasdasd', 6, 6, '09/08/2018', 0, 0),
	(7, 'Test SuperTest', 'SUS 100 KILOMETRA V CHAS', '1.5', 'drago', 7, 'ksadklasda', 7, 7, '14/08/2018', 0, 0),
	(8, 'Great Overall Extension', '100 patrona ', '1.3', 'pesho', 2, 'asdasdas', 8, 8, '15/08/2018', 1, 0),
	(9, 'Roberto Extension', 'Karai si kolata lelelel', '1.1', 'pesho', 10, 'asdasdas', 9, 9, '16/08/2018', 1, 0),
	(10, 'Britney Spears Extension', 'Oops I did it again', '1.2', 'pesho', 4, 'adasdasdasd', 10, 10, '15/08/2018', 0, 1),
	(13, 'Gosho Extention', 'great overall ext', '1.0', 'gosho', 0, 'wow great theBest', NULL, 11, '15/08/2018', 0, 0),
	(14, 'Sony Extention', 'New Tech Ext', '1.0', 'gosho', 0, 'sonyExt newExt', NULL, 12, '15/08/2018', 0, 0);
/*!40000 ALTER TABLE `extension` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_extension_info
CREATE TABLE IF NOT EXISTS `git_extension_info` (
  `gitExtensionInfoID` int(11) NOT NULL AUTO_INCREMENT,
  `openIssues` int(11) DEFAULT NULL,
  `pullRequests` int(11) DEFAULT NULL,
  `lastCommitDate` varchar(50) DEFAULT NULL,
  `gitRepoLink` varchar(100) NOT NULL,
  PRIMARY KEY (`gitExtensionInfoID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_extension_info: ~2 rows (approximately)
/*!40000 ALTER TABLE `git_extension_info` DISABLE KEYS */;
INSERT INTO `git_extension_info` (`gitExtensionInfoID`, `openIssues`, `pullRequests`, `lastCommitDate`, `gitRepoLink`) VALUES
	(11, 0, 0, NULL, 'www.tctc.com'),
	(12, 0, 0, NULL, 'www.sony.com');
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
