-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ufartdb
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `keywords`
--

DROP TABLE IF EXISTS `keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keywords` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  `PersonId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `KeywordNameIndex` (`Name`(767)),
  KEY `FKPersonKeywords_idx` (`PersonId`),
  CONSTRAINT `FKPersonKeywords` FOREIGN KEY (`PersonId`) REFERENCES `persons` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keywords`
--

LOCK TABLES `keywords` WRITE;
/*!40000 ALTER TABLE `keywords` DISABLE KEYS */;
INSERT INTO `keywords` VALUES (1,'Президент',1),(2,'Путин',1),(3,'Путину',1),(4,'Путиным',1),(5,'Медеведев',2),(6,'Медведеву',2),(7,'Медвединым',2),(8,'Шайгу',3);
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pages` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Url` varchar(2048) DEFAULT NULL,
  `SiteId` int(11) NOT NULL,
  `FoundDateTime` datetime DEFAULT NULL,
  `LastScanDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `PagesFoundDateIndex` (`FoundDateTime`),
  KEY `PagesLastScanDateIndex` (`LastScanDate`),
  KEY `PagesUrlnameIndex` (`Url`(767)),
  KEY `FKSitePage_idx` (`SiteId`),
  CONSTRAINT `FKSitePage` FOREIGN KEY (`SiteId`) REFERENCES `sites` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
INSERT INTO `pages` VALUES (1,'https://lenta.ru/rubrics/russia/',3,'2026-04-17 00:00:00','2026-04-17 00:00:00'),(2,'https://lenta.ru/news/2017/04/27/sovfed_usa/',3,'2026-04-17 00:00:00','2026-04-17 00:00:00'),(3,'https://lenta.ru/articles/2017/04/27/fathers/',3,'2027-04-17 00:00:00','2027-04-17 00:00:00'),(4,'https://lenta.ru/news/2017/04/27/papa/',3,'2027-04-17 00:00:00','2027-04-17 00:00:00'),(5,'http://www.rbc.ru/rbcfreenews/59019bb79a794740e0d3c1a1',1,'2026-04-17 00:00:00','2026-04-17 00:00:00'),(6,'http://www.rbc.ru/filter/',1,'2026-04-17 00:00:00','2026-04-17 00:00:00'),(7,'http://www.rbc.ru/rbcfreenews/590193de9a79473d81983bcf',1,'2026-04-17 00:00:00','2026-04-17 00:00:00'),(8,'http://www.rbc.ru/rbcfreenews/5901a04a9a7947421abdae2c',1,'2027-04-17 00:00:00','2027-04-17 00:00:00'),(9,'http://www.rbc.ru/rbcfreenews/59018c229a79473b0cc83f3e',1,'2027-04-17 00:00:00','2027-04-17 00:00:00');
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personpagerank`
--

DROP TABLE IF EXISTS `personpagerank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personpagerank` (
  `PersonId` int(11) NOT NULL,
  `PageId` int(11) NOT NULL,
  `Rank` int(11) NOT NULL,
  KEY `PersonIdPageIdIndex` (`PersonId`,`PageId`),
  KEY `FKPageRank_idx` (`PageId`),
  CONSTRAINT `FKPersonRank` FOREIGN KEY (`PersonId`) REFERENCES `persons` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKPageRank` FOREIGN KEY (`PageId`) REFERENCES `pages` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personpagerank`
--

LOCK TABLES `personpagerank` WRITE;
/*!40000 ALTER TABLE `personpagerank` DISABLE KEYS */;
INSERT INTO `personpagerank` VALUES (1,1,100),(1,2,150),(1,3,124),(1,4,130),(1,5,140),(1,6,200),(1,7,110),(1,8,90),(1,9,50),(2,1,90),(2,2,50),(2,3,70),(2,4,30),(2,5,25),(2,6,75),(2,7,34),(2,8,22),(2,9,88),(3,1,16),(3,2,30),(3,3,24),(3,4,12),(3,5,15),(3,6,17),(3,7,30),(3,8,22),(3,9,11);
/*!40000 ALTER TABLE `personpagerank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `PersonNameIndex` (`Name`(767))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'Путин'),(2,'Медведев'),(3,'Шайгу');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sites` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SitesNameIndex` (`Name`(767))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sites`
--

LOCK TABLES `sites` WRITE;
/*!40000 ALTER TABLE `sites` DISABLE KEYS */;
INSERT INTO `sites` VALUES (1,'http://www.rbc.ru'),(2,'https://news.mail.ru/'),(3,'https://lenta.ru/');
/*!40000 ALTER TABLE `sites` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-27 10:55:41
