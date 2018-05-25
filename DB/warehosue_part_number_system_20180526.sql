-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for warehouse_part_number_system
CREATE DATABASE IF NOT EXISTS `warehouse_part_number_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `warehouse_part_number_system`;

-- Dumping structure for table warehouse_part_number_system.document_types
CREATE TABLE IF NOT EXISTS `document_types` (
  `documentTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `documentTypeNumber` varchar(4) NOT NULL,
  `documentTypeDescription` varchar(200) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `typeStatusId` int(11) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `createdByUserId` int(11) NOT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `updatedByUserId` int(11) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`documentTypeId`),
  UNIQUE KEY `documentTypeNumber` (`documentTypeNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.document_types: ~0 rows (approximately)
DELETE FROM `document_types`;
/*!40000 ALTER TABLE `document_types` DISABLE KEYS */;
INSERT INTO `document_types` (`documentTypeId`, `documentTypeNumber`, `documentTypeDescription`, `comment`, `typeStatusId`, `dateCreated`, `createdByUserId`, `dateUpdated`, `updatedByUserId`, `isActive`, `isDeleted`) VALUES
	(1, 'ABCD', 'Test Description.', 'Test Comment.', 1, '2017-09-24 21:32:44', 1, NULL, NULL, 1, 0);
/*!40000 ALTER TABLE `document_types` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.document_type_status
CREATE TABLE IF NOT EXISTS `document_type_status` (
  `typeStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `typeStatusName` varchar(45) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`typeStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.document_type_status: ~3 rows (approximately)
DELETE FROM `document_type_status`;
/*!40000 ALTER TABLE `document_type_status` DISABLE KEYS */;
INSERT INTO `document_type_status` (`typeStatusId`, `typeStatusName`, `isActive`, `isDeleted`) VALUES
	(1, 'Active', 1, 0),
	(2, 'Inactive', 1, 0),
	(3, 'Closed', 1, 0);
/*!40000 ALTER TABLE `document_type_status` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.parts
CREATE TABLE IF NOT EXISTS `parts` (
  `partId` int(11) NOT NULL AUTO_INCREMENT,
  `partNumber` int(4) NOT NULL,
  `partNumberFull` varchar(14) DEFAULT NULL,
  `partDescription` varchar(200) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `projectId` int(11) NOT NULL,
  `documentTypeId` int(11) NOT NULL,
  `partRevisionNumber` varchar(2) NOT NULL DEFAULT '00',
  `partStatusId` int(11) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `createdByUserId` int(11) NOT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `updatedByUserId` int(11) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`partId`),
  UNIQUE KEY `partNumberFull` (`partNumberFull`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.parts: ~2 rows (approximately)
DELETE FROM `parts`;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` (`partId`, `partNumber`, `partNumberFull`, `partDescription`, `comment`, `projectId`, `documentTypeId`, `partRevisionNumber`, `partStatusId`, `dateCreated`, `createdByUserId`, `dateUpdated`, `updatedByUserId`, `isActive`, `isDeleted`) VALUES
	(1, 1, '1000-ABCD-0001', 'Test Description Updated One more time.', 'Test Comment Updated One more time.', 1, 1, '00', 1, '2017-09-24 21:33:24', 1, '2017-09-25 20:41:42', 1, 1, 0),
	(2, 2, '1000-ABCD-0002', 'Updating description.', 'Updating Comment.', 1, 1, '00', 1, '2017-09-24 21:42:18', 1, '2017-09-30 01:33:18', 1, 1, 0);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.parts_change_log
CREATE TABLE IF NOT EXISTS `parts_change_log` (
  `partChangeLogId` int(11) NOT NULL AUTO_INCREMENT,
  `partId` int(11) NOT NULL,
  `partNumber` int(4) DEFAULT NULL,
  `partNumberFull` varchar(14) DEFAULT NULL,
  `partDescription` varchar(200) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `projectId` int(11) DEFAULT NULL,
  `documentTypeId` int(11) DEFAULT NULL,
  `partRevisionNumber` varchar(2) DEFAULT NULL,
  `partStatusId` int(11) DEFAULT NULL,
  `isActive` int(1) DEFAULT NULL,
  `isDeleted` int(1) DEFAULT NULL,
  `oldPartNumber` int(4) DEFAULT NULL,
  `oldPartNumberFull` varchar(14) DEFAULT NULL,
  `oldPartDescription` varchar(200) DEFAULT NULL,
  `oldComment` varchar(200) DEFAULT NULL,
  `oldProjectId` int(11) DEFAULT NULL,
  `oldDocumentTypeId` int(11) DEFAULT NULL,
  `oldPartRevisionNumber` varchar(2) DEFAULT NULL,
  `oldPartStatusId` int(11) DEFAULT NULL,
  `oldIsActive` int(1) DEFAULT NULL,
  `oldIsDeleted` int(1) DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `updatedByUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partChangeLogId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.parts_change_log: ~5 rows (approximately)
DELETE FROM `parts_change_log`;
/*!40000 ALTER TABLE `parts_change_log` DISABLE KEYS */;
INSERT INTO `parts_change_log` (`partChangeLogId`, `partId`, `partNumber`, `partNumberFull`, `partDescription`, `comment`, `projectId`, `documentTypeId`, `partRevisionNumber`, `partStatusId`, `isActive`, `isDeleted`, `oldPartNumber`, `oldPartNumberFull`, `oldPartDescription`, `oldComment`, `oldProjectId`, `oldDocumentTypeId`, `oldPartRevisionNumber`, `oldPartStatusId`, `oldIsActive`, `oldIsDeleted`, `dateUpdated`, `updatedByUserId`) VALUES
	(1, 1, 1, '1000-ABCD-0001', 'Test Description.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, 1, '1000-ABCD-0001', 'Test Description.', 'Test Comment.', 1, 1, '00', 1, 1, 0, '2017-09-24 21:37:31', 1),
	(2, 1, 1, '1000-ABCD-0001', 'Test Description Updated.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, 1, '1000-ABCD-0001', 'Test Description.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, '2017-09-24 21:41:59', 1),
	(3, 2, 2, '1000-ABCD-0002', 'Updating description.', 'Updating Comment.', 1, 1, '00', 1, 1, 0, 2, '1000-ABCD-0002', 'Testing out.', 'Testing out.', 1, 1, '00', 1, 1, 0, '2017-09-24 21:42:42', 1),
	(4, 1, 1, '1000-ABCD-0001', 'Test Description Updated One more time.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, 1, '1000-ABCD-0001', 'Test Description Updated.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, '2017-09-25 20:41:33', 1),
	(5, 1, 1, '1000-ABCD-0001', 'Test Description Updated One more time.', 'Test Comment Updated One more time.', 1, 1, '00', 1, 1, 0, 1, '1000-ABCD-0001', 'Test Description Updated One more time.', 'Test Comment Updated.', 1, 1, '00', 1, 1, 0, '2017-09-25 20:41:42', 1),
	(6, 2, 2, '1000-ABCD-0002', 'Updating description.', 'Updating Comment.', 1, 1, '00', 1, 1, 0, 2, '1000-ABCD-0002', 'Updating description.', 'Updating Comment.', 1, 1, '00', 1, 1, 0, '2017-09-30 01:33:18', 1);
/*!40000 ALTER TABLE `parts_change_log` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.part_status
CREATE TABLE IF NOT EXISTS `part_status` (
  `partStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `partStatusName` varchar(45) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`partStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.part_status: ~3 rows (approximately)
DELETE FROM `part_status`;
/*!40000 ALTER TABLE `part_status` DISABLE KEYS */;
INSERT INTO `part_status` (`partStatusId`, `partStatusName`, `isActive`, `isDeleted`) VALUES
	(1, 'Active', 1, 0),
	(2, 'Inactive', 1, 0),
	(3, 'Obsolete', 1, 0);
/*!40000 ALTER TABLE `part_status` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.projects
CREATE TABLE IF NOT EXISTS `projects` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectNumber` int(4) NOT NULL,
  `isLegacyProject` int(1) NOT NULL,
  `projectCode` varchar(8) NOT NULL,
  `projectDescription` varchar(200) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `projectStatusId` int(11) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `createdByUserId` int(11) NOT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `updatedByUserId` int(11) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`projectId`),
  UNIQUE KEY `projectNumber_UNIQUE` (`projectNumber`),
  UNIQUE KEY `projectCode` (`projectCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.projects: ~0 rows (approximately)
DELETE FROM `projects`;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` (`projectId`, `projectNumber`, `isLegacyProject`, `projectCode`, `projectDescription`, `comment`, `projectStatusId`, `dateCreated`, `createdByUserId`, `dateUpdated`, `updatedByUserId`, `isActive`, `isDeleted`) VALUES
	(1, 1000, 0, '1234ABCD', 'Test Description.', 'Test Comment.', 1, '2017-09-24 21:33:05', 1, NULL, NULL, 1, 0),
	(2, 999, 1, '0999ABCD', 'Test Project Description.', 'Test Project Comment.', 1, '2017-10-05 06:58:34', 1, NULL, NULL, 1, 0);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.project_status
CREATE TABLE IF NOT EXISTS `project_status` (
  `projectStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `projectStatusName` varchar(45) DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`projectStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.project_status: ~4 rows (approximately)
DELETE FROM `project_status`;
/*!40000 ALTER TABLE `project_status` DISABLE KEYS */;
INSERT INTO `project_status` (`projectStatusId`, `projectStatusName`, `isActive`, `isDeleted`) VALUES
	(1, 'Active', 1, 0),
	(2, 'Inactive', 1, 0),
	(3, 'Obsolete', 1, 0),
	(4, 'Closed', 1, 0);
/*!40000 ALTER TABLE `project_status` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.users
CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `userLogin` varchar(200) DEFAULT NULL,
  `userTypeId` int(11) NOT NULL,
  `dateLastLogin` datetime DEFAULT NULL,
  `dateCreated` datetime NOT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userLogin` (`userLogin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.users: ~0 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userId`, `userName`, `userLogin`, `userTypeId`, `dateLastLogin`, `dateCreated`, `dateUpdated`, `isActive`, `isDeleted`) VALUES
	(1, 'Guest1 NumberOne', 'guest1', 1, '2017-11-01 15:14:58', '2017-09-24 21:31:51', NULL, 1, 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table warehouse_part_number_system.user_types
CREATE TABLE IF NOT EXISTS `user_types` (
  `userTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `userTypeName` varchar(50) NOT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1',
  `isDeleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table warehouse_part_number_system.user_types: ~2 rows (approximately)
DELETE FROM `user_types`;
/*!40000 ALTER TABLE `user_types` DISABLE KEYS */;
INSERT INTO `user_types` (`userTypeId`, `userTypeName`, `isActive`, `isDeleted`) VALUES
	(1, 'Administrator', 1, 0),
	(2, 'Staff', 1, 0);
/*!40000 ALTER TABLE `user_types` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
