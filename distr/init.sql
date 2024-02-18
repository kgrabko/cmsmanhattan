CREATE DATABASE  IF NOT EXISTS `cmsdb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cmsdb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cmsdb
-- ------------------------------------------------------
-- Server version	8.0.35

-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: cmsdb
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.4-MariaDB-1:10.6.4+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accesslevel`
--

DROP TABLE IF EXISTS `accesslevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesslevel` (
  `ACCESSLEVEL_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`ACCESSLEVEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesslevel`
--

LOCK TABLES `accesslevel` WRITE;
/*!40000 ALTER TABLE `accesslevel` DISABLE KEYS */;
/*!40000 ALTER TABLE `accesslevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `AMOUNT` double NOT NULL DEFAULT 0,
  `CURR` int(11) DEFAULT NULL,
  `DATE_INPUT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `COMPLETE` tinyint(1) NOT NULL DEFAULT 0,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT 0,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `TREE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (100717701038604429,100717701038604428,0,3,'2024-02-18 19:39:56',' new_account ',1,1,1,NULL),(100717701038604431,100717701038604430,0,3,'2024-02-18 19:39:56',' new_account ',1,1,1,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_hist`
--

DROP TABLE IF EXISTS `account_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_hist` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ADD_AMOUNT` double DEFAULT 0,
  `OLD_AMOUNT` double DEFAULT NULL,
  `NUM_DAY` int(11) DEFAULT NULL,
  `DATE_INPUT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `COMPLETE` tinyint(1) DEFAULT NULL,
  `DECSRIPTION` varchar(200) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `REZULT_CD` varchar(10) DEFAULT NULL,
  `DATE_END` timestamp NULL DEFAULT NULL,
  `SYSDATE` timestamp NULL DEFAULT NULL,
  `TAX` double DEFAULT 0,
  `SOFT_ID` bigint(20) DEFAULT NULL,
  `RATE` double NOT NULL DEFAULT 0,
  `CURRENCY_ID_ADD` int(11) DEFAULT NULL,
  `CURRENCY_ID_OLD` int(11) DEFAULT NULL,
  `CURRENCY_ID_TOTAL` int(11) DEFAULT NULL,
  `TOTAL_AMOUNT` double DEFAULT NULL,
  `WITHTAX_TOTAL_AMOUNT` double DEFAULT NULL,
  `USER_IP` varchar(15) DEFAULT NULL,
  `USER_HEADER` varchar(500) DEFAULT NULL,
  `USER_LOCALE` varchar(2) DEFAULT NULL,
  `USER_OS` varchar(10) DEFAULT NULL,
  `USER_OS_PACK` varchar(10) DEFAULT NULL,
  `ORDER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_hist`
--

LOCK TABLES `account_hist` WRITE;
/*!40000 ALTER TABLE `account_hist` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket` (
  `BASKET_ID` bigint(20) NOT NULL,
  `PRODUCT_ID` bigint(20) DEFAULT NULL,
  `ORDER_ID` bigint(20) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`BASKET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `big_images`
--

DROP TABLE IF EXISTS `big_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `big_images` (
  `BIG_IMAGES_ID` bigint(20) NOT NULL,
  `IMGNAME` varchar(100) DEFAULT NULL,
  `IMG_URL` varchar(200) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`BIG_IMAGES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `big_images`
--

LOCK TABLES `big_images` WRITE;
/*!40000 ALTER TABLE `big_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `big_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `CALENDAR_ID` bigint(20) NOT NULL,
  `SOFT_ID` bigint(20) DEFAULT NULL,
  `HOLDDATE` int(11) DEFAULT NULL,
  `FIST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `FATHER_NAME` varchar(50) DEFAULT NULL,
  `DOCUMENT_NUMBER` varchar(50) DEFAULT NULL,
  `DOCUMENT_TYPE` varchar(50) DEFAULT NULL,
  `AGE` varchar(50) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `NOTE` varchar(500) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `NODE_NAME` varchar(500) DEFAULT NULL,
  `SITE_ID` bigint(20) NOT NULL,
  `BASKET_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CALENDAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATALOG_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `TAX` double DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `SITE_ID` bigint(20) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `CATALOG_IMAGE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,-1,NULL,'New arrivals',1,NULL,NULL,2,2,0,NULL),(2,-2,NULL,'Home page',1,NULL,NULL,2,2,0,NULL),(3,-3,NULL,'Change Site Design',1,NULL,NULL,2,2,0,NULL),(4,100717701038604432,NULL,'Selection1',1,NULL,NULL,2,2,-2,NULL),(5,100717701038604433,NULL,'Sub selection1',1,NULL,NULL,2,2,100717701038604432,NULL),(6,100717701038604434,NULL,'Selection2',1,NULL,NULL,2,2,-2,NULL),(7,100717701038604435,NULL,'Sub selection2',1,NULL,NULL,2,2,100717701038604434,NULL),(8,100717701038604436,NULL,'Selection3',1,NULL,NULL,2,2,-2,NULL),(9,100717701038604437,NULL,'Sub selection3',1,NULL,NULL,2,2,100717701038604436,NULL),(10,100717701038604438,NULL,'Selection4',1,NULL,NULL,2,2,-2,NULL),(11,100717701038604439,NULL,'Sub selection4',1,NULL,NULL,2,2,100717701038604438,NULL),(12,100717701038604440,NULL,'Selection5',1,NULL,NULL,2,2,-2,NULL),(13,100717701038604441,NULL,'Sub selection5',1,NULL,NULL,2,2,100717701038604440,NULL);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog_images`
--

DROP TABLE IF EXISTS `catalog_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog_images` (
  `CATALOG_IMAGES_ID` bigint(20) NOT NULL,
  `IMGNAME` varchar(100) DEFAULT NULL,
  `IMG_URL` varchar(200) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CATALOG_IMAGES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_images`
--

LOCK TABLES `catalog_images` WRITE;
/*!40000 ALTER TABLE `catalog_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalog_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `CITY_ID` bigint(20) DEFAULT NULL,
  `TELCODE` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `LANG_ID` int(11) DEFAULT NULL,
  `LOCALE` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,812,'St. Petersburg','St. Petersburg',1,2,'EN'),(2,95,'Moscow','Moscow',1,2,'EN'),(107,22,'York','York',2,2,'EN'),(103,22,'London','London',3,2,'EN'),(700,61,'Ottawa','Ottawa',13,2,'EN'),(7001,2,'Canberra','Canberra',20,2,'EN');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `TELCODE` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LOCALE` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,7,'Russia','Russia',2,'EN'),(2,1,'USA','USA',2,'EN'),(3,44,'United Kingdom','United Kingdom',2,'EN'),(13,1,'Canada','Canada',2,'EN'),(20,1,'Australia','Australia',2,'EN');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria1`
--

DROP TABLE IF EXISTS `creteria1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria1` (
  `CRETERIA1_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA1_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria1`
--

LOCK TABLES `creteria1` WRITE;
/*!40000 ALTER TABLE `creteria1` DISABLE KEYS */;
INSERT INTO `creteria1` VALUES (0,'Not chosen',1,2,0,2,'Criterion1'),(1,'Test1',1,2,0,2,'Criterion1'),(2,'Test2',1,2,0,2,'Criterion1');
/*!40000 ALTER TABLE `creteria1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria10`
--

DROP TABLE IF EXISTS `creteria10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria10` (
  `CRETERIA10_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA10_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria10`
--

LOCK TABLES `creteria10` WRITE;
/*!40000 ALTER TABLE `creteria10` DISABLE KEYS */;
INSERT INTO `creteria10` VALUES (0,'Not chosen',1,2,0,2,'Criterion10');
/*!40000 ALTER TABLE `creteria10` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria2`
--

DROP TABLE IF EXISTS `creteria2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria2` (
  `CRETERIA2_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA2_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria2`
--

LOCK TABLES `creteria2` WRITE;
/*!40000 ALTER TABLE `creteria2` DISABLE KEYS */;
INSERT INTO `creteria2` VALUES (0,'Not chosen',1,2,0,2,'Criterion2');
/*!40000 ALTER TABLE `creteria2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria3`
--

DROP TABLE IF EXISTS `creteria3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria3` (
  `CRETERIA3_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA3_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria3`
--

LOCK TABLES `creteria3` WRITE;
/*!40000 ALTER TABLE `creteria3` DISABLE KEYS */;
INSERT INTO `creteria3` VALUES (0,'Not chosen',1,2,0,2,'Criterion3');
/*!40000 ALTER TABLE `creteria3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria4`
--

DROP TABLE IF EXISTS `creteria4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria4` (
  `CRETERIA4_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA4_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria4`
--

LOCK TABLES `creteria4` WRITE;
/*!40000 ALTER TABLE `creteria4` DISABLE KEYS */;
INSERT INTO `creteria4` VALUES (0,'Not chosen',1,2,0,2,'Criterion4');
/*!40000 ALTER TABLE `creteria4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria5`
--

DROP TABLE IF EXISTS `creteria5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria5` (
  `CRETERIA5_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA5_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria5`
--

LOCK TABLES `creteria5` WRITE;
/*!40000 ALTER TABLE `creteria5` DISABLE KEYS */;
INSERT INTO `creteria5` VALUES (0,'Not chosen',1,2,0,2,'Criterion5');
/*!40000 ALTER TABLE `creteria5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria6`
--

DROP TABLE IF EXISTS `creteria6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria6` (
  `CRETERIA6_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA6_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria6`
--

LOCK TABLES `creteria6` WRITE;
/*!40000 ALTER TABLE `creteria6` DISABLE KEYS */;
INSERT INTO `creteria6` VALUES (0,'Not chosen',1,2,0,2,'Criterion6');
/*!40000 ALTER TABLE `creteria6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria7`
--

DROP TABLE IF EXISTS `creteria7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria7` (
  `CRETERIA7_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA7_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria7`
--

LOCK TABLES `creteria7` WRITE;
/*!40000 ALTER TABLE `creteria7` DISABLE KEYS */;
INSERT INTO `creteria7` VALUES (0,'Not chosen',1,2,0,2,'Criterion7');
/*!40000 ALTER TABLE `creteria7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria8`
--

DROP TABLE IF EXISTS `creteria8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria8` (
  `CRETERIA8_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA8_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria8`
--

LOCK TABLES `creteria8` WRITE;
/*!40000 ALTER TABLE `creteria8` DISABLE KEYS */;
INSERT INTO `creteria8` VALUES (0,'Not chosen',1,2,0,2,'Criterion8');
/*!40000 ALTER TABLE `creteria8` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creteria9`
--

DROP TABLE IF EXISTS `creteria9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria9` (
  `CRETERIA9_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `LINK_ID` bigint(20) NOT NULL,
  `CATALOG_ID` bigint(20) NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA9_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creteria9`
--

LOCK TABLES `creteria9` WRITE;
/*!40000 ALTER TABLE `creteria9` DISABLE KEYS */;
INSERT INTO `creteria9` VALUES (0,'Not chosen',1,2,0,2,'Criterion9');
/*!40000 ALTER TABLE `creteria9` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `CURRENCY_ID` bigint(20) NOT NULL,
  `RATE` double DEFAULT NULL,
  `CURRENCY_LABLE` varchar(10) DEFAULT NULL,
  `CURRENCY_DESC` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `CURRENCY_CD` varchar(100) DEFAULT NULL,
  `CURSDATE` varchar(100) DEFAULT NULL,
  `CHANGEDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`CURRENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (0,0,'UNKNOWN','Not selected',1,'0',NULL,'2024-02-18 19:39:56'),(1,36,'USD','U.S Dollar',1,'USD',NULL,'2024-02-18 19:39:56'),(2,45,'EUR',' EURO',1,'EUR',NULL,'2024-02-18 19:39:56'),(3,1,'RUB','RF Ruble',1,'RUB',NULL,'2024-02-18 19:39:56'),(4,1,'CNY','China money',1,'CNY',NULL,'2024-02-18 19:39:56'),(5,1,'JPY','Japan Yen',1,'JPY',NULL,'2024-02-18 19:39:56'),(6,1,'TRY','Turkey Lira',1,'TRY',NULL,'2024-02-18 19:39:56'),(7,1,'MXN','Mexico Peso',1,'MXN',NULL,'2024-02-18 19:39:56'),(8,1,'CAD','United Kingdom Pound',1,'CAD',NULL,'2024-02-18 19:39:56'),(9,1,'GBP','Brazil Real',1,'GBP',NULL,'2024-02-18 19:39:56'),(10,1,'BRL','India Rupee',1,'BRL',NULL,'2024-02-18 19:39:56'),(11,1,'INR','Bitcoin',1,'INR',NULL,'2024-02-18 19:39:56'),(12,1,'BTC','Litecoin',1,'BTC',NULL,'2024-02-18 19:39:56'),(13,1,'LTC','Ethereum',1,'LTC',NULL,'2024-02-18 19:39:56'),(14,1,'ETH','PayPal Dollar',1,'ETH',NULL,'2024-02-18 19:39:56'),(15,1,'PYUSD','U.S Digital Dollar',1,'PYUSD',NULL,'2024-02-18 19:39:56'),(16,1,'CBDC','ERROR',1,'CBDC',NULL,'2024-02-18 19:39:56');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_converter`
--

DROP TABLE IF EXISTS `currency_converter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_converter` (
  `CURRENCY_ID` bigint(20) NOT NULL,
  `1` double DEFAULT NULL,
  `2` double DEFAULT NULL,
  `3` double DEFAULT NULL,
  `4` double DEFAULT NULL,
  `5` double DEFAULT NULL,
  `6` double DEFAULT NULL,
  `7` double DEFAULT NULL,
  `8` double DEFAULT NULL,
  `9` double DEFAULT NULL,
  `10` double DEFAULT NULL,
  `11` double DEFAULT NULL,
  `12` double DEFAULT NULL,
  `13` double DEFAULT NULL,
  `14` double DEFAULT NULL,
  `15` double DEFAULT NULL,
  `16` double DEFAULT NULL,
  PRIMARY KEY (`CURRENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_converter`
--

LOCK TABLES `currency_converter` WRITE;
/*!40000 ALTER TABLE `currency_converter` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency_converter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_rate`
--

DROP TABLE IF EXISTS `currency_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_rate` (
  `CURRENCY_RATE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CURRENCY_RATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_rate`
--

LOCK TABLES `currency_rate` WRITE;
/*!40000 ALTER TABLE `currency_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliverystatus`
--

DROP TABLE IF EXISTS `deliverystatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliverystatus` (
  `DELIVERYSTATUS_ID` bigint(20) NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `LANG` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`DELIVERYSTATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliverystatus`
--

LOCK TABLES `deliverystatus` WRITE;
/*!40000 ALTER TABLE `deliverystatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliverystatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinamic_fields`
--

DROP TABLE IF EXISTS `dinamic_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinamic_fields` (
  `DINAMIC_FIELD_ID` bigint(20) NOT NULL,
  `PRODUCT_ID` bigint(20) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`DINAMIC_FIELD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinamic_fields`
--

LOCK TABLES `dinamic_fields` WRITE;
/*!40000 ALTER TABLE `dinamic_fields` DISABLE KEYS */;
/*!40000 ALTER TABLE `dinamic_fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain_proc`
--

DROP TABLE IF EXISTS `domain_proc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domain_proc` (
  `DOMAIN_PROC_ID` bigint(20) NOT NULL,
  `LAST_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain_proc`
--

LOCK TABLES `domain_proc` WRITE;
/*!40000 ALTER TABLE `domain_proc` DISABLE KEYS */;
/*!40000 ALTER TABLE `domain_proc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `FILE_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FILEDATA` binary(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `SIZE` varchar(15) DEFAULT NULL,
  `PATH` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `IMAGE_ID` bigint(20) NOT NULL,
  `IMGNAME` varchar(50) DEFAULT NULL,
  `IMG_URL` varchar(500) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IMAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lang`
--

DROP TABLE IF EXISTS `lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lang` (
  `LANG_ID` bigint(20) NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`LANG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lang`
--

LOCK TABLES `lang` WRITE;
/*!40000 ALTER TABLE `lang` DISABLE KEYS */;
INSERT INTO `lang` VALUES (1,'ru','Russian',1),(2,'en','English',1);
/*!40000 ALTER TABLE `lang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licence`
--

DROP TABLE IF EXISTS `licence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licence` (
  `LICENCE_ID` bigint(20) NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `SITE_ID` bigint(20) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`LICENCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licence`
--

LOCK TABLES `licence` WRITE;
/*!40000 ALTER TABLE `licence` DISABLE KEYS */;
/*!40000 ALTER TABLE `licence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `one_sequences`
--

DROP TABLE IF EXISTS `one_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `one_sequences` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `one_sequences`
--

LOCK TABLES `one_sequences` WRITE;
/*!40000 ALTER TABLE `one_sequences` DISABLE KEYS */;
/*!40000 ALTER TABLE `one_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ORDER_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `DELIVERY_TIMEEND` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `AMOUNT` double NOT NULL DEFAULT 0,
  `TAX` double DEFAULT NULL,
  `END_AMOUNT` double DEFAULT NULL,
  `DELIVERY_AMOUNT` double DEFAULT NULL,
  `DELIVERY_LONG` int(11) DEFAULT NULL,
  `PAYSTATUS_ID` bigint(20) DEFAULT NULL,
  `DELIVERY_START` timestamp NULL DEFAULT NULL,
  `CDATE` timestamp NULL DEFAULT NULL,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `PHONE` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `ZIP` int(11) DEFAULT NULL,
  `TREE_ID` int(11) DEFAULT NULL,
  `IMEI` int(11) DEFAULT NULL,
  `PHONEMODEL_ID` int(11) DEFAULT NULL,
  `DELIVERYSTATUS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (100717701038604472,1,'2024-02-18 19:40:48',0,0,0,0,NULL,0,NULL,'2024-02-18 00:00:00',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_hist`
--

DROP TABLE IF EXISTS `orders_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_hist` (
  `ORDER_HIST_ID` bigint(20) NOT NULL,
  `ORDER_HIST_CD` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `POSITION_ID` int(11) DEFAULT NULL,
  `DELIVERY_TIMEEND` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `AMOUNT` double DEFAULT NULL,
  `TAX` double DEFAULT NULL,
  `END_AMOUNT` double DEFAULT NULL,
  `DELIVERY_AMOUNT` double DEFAULT NULL,
  `CARD_ID` int(11) DEFAULT NULL,
  `DELIVERY_LONG` int(11) DEFAULT NULL,
  `SHIPMENT_ADDRESS_ID` int(11) DEFAULT NULL,
  `SERIAL_NUMBER` varchar(50) DEFAULT NULL,
  `PAYSTATUS_ID` bigint(20) DEFAULT NULL,
  `DELIVERYSTATUS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ORDER_HIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_hist`
--

LOCK TABLES `orders_hist` WRITE;
/*!40000 ALTER TABLE `orders_hist` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_gateway`
--

DROP TABLE IF EXISTS `pay_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_gateway` (
  `PAY_GATEWAY_ID` bigint(20) NOT NULL,
  `NAME_GATEWAY` varchar(50) DEFAULT NULL,
  `FULLNAME_GATEWAY` varchar(500) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `FAX` varchar(15) DEFAULT NULL,
  `SITE_URL` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_gateway`
--

LOCK TABLES `pay_gateway` WRITE;
/*!40000 ALTER TABLE `pay_gateway` DISABLE KEYS */;
/*!40000 ALTER TABLE `pay_gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paystatus`
--

DROP TABLE IF EXISTS `paystatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paystatus` (
  `PAYSTATUS_ID` bigint(20) NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `LANG` longtext DEFAULT NULL,
  PRIMARY KEY (`PAYSTATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paystatus`
--

LOCK TABLES `paystatus` WRITE;
/*!40000 ALTER TABLE `paystatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `paystatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paysystem`
--

DROP TABLE IF EXISTS `paysystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paysystem` (
  `PAYSYSTEM_ID` bigint(20) NOT NULL,
  `PAYSYSTEM_CD` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `IMG_URL` varchar(500) DEFAULT NULL,
  `PAY_GATEWAY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PAYSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paysystem`
--

LOCK TABLES `paysystem` WRITE;
/*!40000 ALTER TABLE `paysystem` DISABLE KEYS */;
/*!40000 ALTER TABLE `paysystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portlettype`
--

DROP TABLE IF EXISTS `portlettype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portlettype` (
  `PORTLETTYPE_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PORTLETTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portlettype`
--

LOCK TABLES `portlettype` WRITE;
/*!40000 ALTER TABLE `portlettype` DISABLE KEYS */;
/*!40000 ALTER TABLE `portlettype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salelogic`
--

DROP TABLE IF EXISTS `salelogic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salelogic` (
  `SALELOGIC_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIBE` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`SALELOGIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salelogic`
--

LOCK TABLES `salelogic` WRITE;
/*!40000 ALTER TABLE `salelogic` DISABLE KEYS */;
/*!40000 ALTER TABLE `salelogic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_status_events`
--

DROP TABLE IF EXISTS `service_status_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_status_events` (
  `SITE_ID` bigint(20) DEFAULT NULL,
  `DATE_SEND` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `SERVICE_STATUS` int(11) DEFAULT NULL,
  `ISNOTIFY` tinyint(1) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_status_events`
--

LOCK TABLES `service_status_events` WRITE;
/*!40000 ALTER TABLE `service_status_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_status_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_status_events_history`
--

DROP TABLE IF EXISTS `service_status_events_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_status_events_history` (
  `SITE_ID` bigint(20) DEFAULT NULL,
  `DATE_SEND` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `SERVICE_STATUS` int(11) DEFAULT NULL,
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `ISNOTIFY` tinyint(1) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_status_events_history`
--

LOCK TABLES `service_status_events_history` WRITE;
/*!40000 ALTER TABLE `service_status_events_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_status_events_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `SHOP_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SHOP_CD` int(11) NOT NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `LOGIN` varchar(50) DEFAULT NULL,
  `SITE_ID` bigint(20) DEFAULT NULL,
  `PAY_GATEWAY_ID` bigint(20) DEFAULT NULL,
  `PASSWD` varchar(50) DEFAULT NULL,
  `CDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,84473,100717701038604430,'HCO-CENTE-406',2,1,'91KiBFRtE8fF7VHc8tvr','2024-02-18 19:39:56');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site` (
  `SITE_ID` bigint(20) NOT NULL,
  `HOST` varchar(100) DEFAULT NULL,
  `OWNER` bigint(20) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `HOME_DIR` varchar(100) DEFAULT NULL,
  `SITE_DIR` varchar(100) DEFAULT NULL,
  `PERSON` varchar(200) DEFAULT NULL,
  `PHONE` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(300) DEFAULT NULL,
  `SUBJECT_SITE` varchar(300) DEFAULT NULL,
  `NICK_SITE` varchar(200) DEFAULT NULL,
  `COMPANY_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (2,'localhost',1,1,'shops.online-spb.com','shops.online-spb.com','Administrator','+z(xxx)yyy-xxxx','',NULL,NULL,NULL);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soft`
--

DROP TABLE IF EXISTS `soft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soft` (
  `SOFT_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `VERSION` varchar(10) DEFAULT NULL,
  `COST` double NOT NULL DEFAULT 0,
  `CURRENCY` int(11) NOT NULL DEFAULT 1,
  `SERIAL_NUBMER` varchar(50) DEFAULT NULL,
  `FILE_ID` bigint(20) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `LEVELUP` bigint(20) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `PHONETYPE_ID` int(11) DEFAULT NULL,
  `PROGNAME_ID` bigint(20) DEFAULT NULL,
  `IMAGE_ID` bigint(20) DEFAULT NULL,
  `BIGIMAGE_ID` bigint(20) DEFAULT NULL,
  `WEIGHT` double DEFAULT NULL,
  `COUNT` int(11) DEFAULT NULL,
  `LANG_ID` bigint(20) DEFAULT NULL,
  `PHONEMODEL_ID` int(11) DEFAULT NULL,
  `LICENCE_ID` bigint(20) DEFAULT NULL,
  `CATALOG_ID` bigint(20) DEFAULT NULL,
  `SALELOGIC_ID` bigint(20) DEFAULT NULL,
  `TREE_ID` bigint(20) DEFAULT NULL,
  `CARD_NUMBER` int(11) DEFAULT NULL,
  `CARD_CODE` int(11) DEFAULT NULL,
  `START_DIAL_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `END_DIAL_TIME` timestamp NULL DEFAULT NULL,
  `START_ACTIVATION_CARD` tinyint(1) NOT NULL DEFAULT 0,
  `END_ACTIVATION_CARD` tinyint(1) NOT NULL DEFAULT 0,
  `TYPE_CARD_ID` int(11) DEFAULT NULL,
  `PRODUCT_CODE` int(11) DEFAULT NULL,
  `FULLDESCRIPTION` varchar(5000) DEFAULT NULL,
  `SITE_ID` bigint(20) DEFAULT NULL,
  `SEARCH` varchar(1) DEFAULT NULL,
  `PORTLETTYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `CRETERIA1_ID` bigint(20) DEFAULT NULL,
  `CRETERIA2_ID` bigint(20) DEFAULT NULL,
  `CRETERIA3_ID` bigint(20) DEFAULT NULL,
  `CRETERIA4_ID` bigint(20) DEFAULT NULL,
  `CRETERIA5_ID` bigint(20) DEFAULT NULL,
  `CRETERIA6_ID` bigint(20) DEFAULT NULL,
  `CRETERIA7_ID` bigint(20) DEFAULT NULL,
  `CRETERIA8_ID` bigint(20) DEFAULT NULL,
  `CRETERIA9_ID` bigint(20) DEFAULT NULL,
  `CRETERIA10_ID` bigint(20) DEFAULT NULL,
  `STATISTIC_ID` bigint(20) NOT NULL DEFAULT 0,
  `CDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CRETERIA11_ID` bigint(20) DEFAULT NULL,
  `CRETERIA12_ID` bigint(20) DEFAULT NULL,
  `CRETERIA13_ID` bigint(20) DEFAULT NULL,
  `CRETERIA14_ID` bigint(20) DEFAULT NULL,
  `CRETERIA15_ID` bigint(20) DEFAULT NULL,
  `SQUARE` int(11) DEFAULT NULL,
  `RATING_SUMM1` int(11) NOT NULL DEFAULT 0,
  `RATING_SUMM2` int(11) NOT NULL DEFAULT 0,
  `RATING_SUMM3` int(11) NOT NULL DEFAULT 0,
  `COUNTPOST_RATING1` int(11) NOT NULL DEFAULT 0,
  `COUNTPOST_RATING2` int(11) NOT NULL DEFAULT 0,
  `COUNTPOST_RATING3` int(11) NOT NULL DEFAULT 0,
  `MIDLE_BAL1` int(11) NOT NULL DEFAULT 0,
  `MIDLE_BAL2` int(11) NOT NULL DEFAULT 0,
  `MIDLE_BAL3` int(11) NOT NULL DEFAULT 0,
  `SHOW_RATING1` tinyint(1) NOT NULL DEFAULT 0,
  `SHOW_RATING2` tinyint(1) NOT NULL DEFAULT 0,
  `SHOW_RATING3` tinyint(1) NOT NULL DEFAULT 0,
  `SHOW_BLOG` tinyint(1) NOT NULL DEFAULT 0,
  `NAME2` varchar(50) DEFAULT NULL,
  `SEARCH2` varchar(1) DEFAULT NULL,
  `AMOUNT1` double NOT NULL DEFAULT 0,
  `AMOUNT2` double NOT NULL DEFAULT 0,
  `AMOUNT3` double NOT NULL DEFAULT 0,
  `JSP_URL` varchar(100) DEFAULT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SOFT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soft`
--

LOCK TABLES `soft` WRITE;
/*!40000 ALTER TABLE `soft` DISABLE KEYS */;
INSERT INTO `soft` VALUES (100717701038604442,' Demo item 1  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604443,' Demo item 2  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604444,' Demo item 3  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604445,' Demo item 4  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604446,' Demo item 5  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604447,' Demo item 6  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604448,' Demo item 7  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604449,' Demo item 8  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604450,' Demo item 9  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604451,' Demo item 10  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604452,' Demo item 11  ','<r>Demo new item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo new item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604453,' Demo item 12  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604454,' Demo item 13  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604455,' Demo item 14  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604456,' Demo item 15  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604457,' Demo item 16  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604458,' Demo item 17  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,10,10,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604459,' Demo item 18  ','<r>Demo item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,0,0,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604460,' Demo item 19  ','<r>Demo item review brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item review full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604461,' Demo item 20  ','<r>Demo item review brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo item review full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604462,' Demo item 21  ','<r>Demo sponsored item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,10,10,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo sponsored item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604463,' Demo item 22  ','<r>Demo sponsored item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,10,10,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo sponsored item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604464,' Demo item 23  ','<r>Demo sponsored item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo sponsored item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604465,' Demo item 24  ','<r>Demo recommended item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo recommended item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604466,' Demo item 25  ','<r>Demo recommended item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo recommended item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604467,' Demo item 26  ','<r>Demo recommended item brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo recommended item full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604468,' Demo item 27  ','<r>About company brief description .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-7,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.bout company full  description ,</r><r>Demo item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604469,' About company business  ','<r>Demo footer item brief description about company business .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo footer item full  description about company business ,</r><r>Demo footer item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604470,' About company phone  ','<r>Demo footer item brief description about company phone .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo footer item full  description about company  phone,</r><r>Demo footer item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL),(100717701038604471,' About company contacts  ','<r>Demo footer item brief description about company contacts .</r>',NULL,0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56',NULL,0,0,NULL,NULL,'<r>1.Demo footer item full  description about company  contacts,</r><r>Demo footer item full  description to replace with real text .</r><r> Test conext demo .</r><r> use admin mode to replace or delete the test  </r><r> Login as admin and password admin  </r><r> then you will see button to edit or delete it </r><r> to add new item click manage button to get publish items web page </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-18 19:39:56',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0,0,0,NULL,NULL);
/*!40000 ALTER TABLE `soft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_session`
--

DROP TABLE IF EXISTS `store_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_session` (
  `IDSESSION_HASH1` bigint(20) DEFAULT NULL,
  `IDSESSION_HASH2` bigint(20) DEFAULT NULL,
  `IDSESSION_HASH3` bigint(20) DEFAULT NULL,
  `IDSESSION_HASH4` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `TYPE` varchar(200) DEFAULT NULL,
  `LASTURL` varchar(500) DEFAULT NULL,
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `BCLASSBODY` longblob DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `CDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_session`
--

LOCK TABLES `store_session` WRITE;
/*!40000 ALTER TABLE `store_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tree`
--

DROP TABLE IF EXISTS `tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tree` (
  `ITEM_ID` bigint(20) NOT NULL,
  `NODE_ID` bigint(20) DEFAULT NULL,
  `FOLDER_NAME` varchar(50) DEFAULT NULL,
  `OWNER_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `DOC_ID` int(11) DEFAULT NULL,
  `CDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `LAST_TOUCH_ID` int(11) DEFAULT NULL,
  `LAST_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree`
--

LOCK TABLES `tree` WRITE;
/*!40000 ALTER TABLE `tree` DISABLE KEYS */;
/*!40000 ALTER TABLE `tree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuser` (
  `USER_ID` bigint(20) NOT NULL,
  `LOGIN` varchar(50) DEFAULT NULL,
  `PASSWD` varchar(50) DEFAULT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `COMPANY` varchar(50) DEFAULT NULL,
  `E_MAIL` varchar(50) DEFAULT NULL,
  `PHONE` varchar(50) DEFAULT NULL,
  `MOBIL_PHONE` varchar(50) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `ICQ` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `QUESTION` varchar(200) DEFAULT NULL,
  `ANSWER` varchar(200) DEFAULT NULL,
  `IDSESSION` varchar(50) DEFAULT NULL,
  `BIRTHDAY` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `REGDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LEVELUP_CD` bigint(20) DEFAULT NULL,
  `BANK_CD` bigint(20) DEFAULT NULL,
  `ACVIVE_SESSION` tinyint(1) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `COUNTRY` varchar(20) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `STATE` varchar(10) DEFAULT NULL,
  `SCOUNTRY` varchar(50) DEFAULT NULL,
  `MIDDLENAME` varchar(50) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `TREE_ID` bigint(20) DEFAULT NULL,
  `SITE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuser`
--

LOCK TABLES `tuser` WRITE;
/*!40000 ALTER TABLE `tuser` DISABLE KEYS */;
INSERT INTO `tuser` VALUES (100717701038604428,'admin','admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56','2024-02-18 19:39:56',2,0,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,NULL,2),(100717701038604430,'user','user',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-02-18 19:39:56','2024-02-18 19:39:56',0,0,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,NULL,2);
/*!40000 ALTER TABLE `tuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typesoft`
--

DROP TABLE IF EXISTS `typesoft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typesoft` (
  `TYPE_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `TYPE_LABLE` varchar(50) DEFAULT NULL,
  `TYPE_DESC` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `TAX` double NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typesoft`
--

LOCK TABLES `typesoft` WRITE;
/*!40000 ALTER TABLE `typesoft` DISABLE KEYS */;
INSERT INTO `typesoft` VALUES (0,1,'Default mode','Default mode',1,0),(1,1,'Make item invisible on your page','Make item invisible on your page',1,0),(2,1,'Make item visible in Marker place','Make item visible in Marker place',1,0),(3,1,'Make item visible on your page','Make item visible on your page',1,0);
/*!40000 ALTER TABLE `typesoft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xsl_style`
--

DROP TABLE IF EXISTS `xsl_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xsl_style` (
  `XSL_STYLE_ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `PRODUCER_ID` int(11) NOT NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `COST` double NOT NULL,
  `CURRENCY_ID` bigint(20) NOT NULL,
  `SYS_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `SITE_ID` bigint(20) NOT NULL,
  `DIRNAME` varchar(200) DEFAULT NULL,
  `XSL_SUBJ_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`XSL_STYLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xsl_style`
--

LOCK TABLES `xsl_style` WRITE;
/*!40000 ALTER TABLE `xsl_style` DISABLE KEYS */;
/*!40000 ALTER TABLE `xsl_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xsl_subj`
--

DROP TABLE IF EXISTS `xsl_subj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xsl_subj` (
  `XSL_SUBJ_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`XSL_SUBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xsl_subj`
--

LOCK TABLES `xsl_subj` WRITE;
/*!40000 ALTER TABLE `xsl_subj` DISABLE KEYS */;
/*!40000 ALTER TABLE `xsl_subj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cmsdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-18 14:51:28
