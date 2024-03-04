CREATE DATABASE  IF NOT EXISTS `cmsdb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cmsdb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cmsdb
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
  `ACCESSLEVEL_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`ACCESSLEVEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `ACCOUNT_ID` bigint NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `AMOUNT` double NOT NULL DEFAULT '0',
  `CURR` int DEFAULT NULL,
  `DATE_INPUT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `COMPLETE` tinyint(1) NOT NULL DEFAULT '0',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '0',
  `CURRENCY_ID` bigint DEFAULT NULL,
  `TREE_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_hist`
--

DROP TABLE IF EXISTS `account_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_hist` (
  `ID` bigint NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `ADD_AMOUNT` double DEFAULT '0',
  `OLD_AMOUNT` double DEFAULT NULL,
  `NUM_DAY` int DEFAULT NULL,
  `DATE_INPUT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `COMPLETE` tinyint(1) DEFAULT NULL,
  `DECSRIPTION` varchar(200) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `REZULT_CD` varchar(10) DEFAULT NULL,
  `DATE_END` timestamp NULL DEFAULT NULL,
  `SYSDATE` timestamp NULL DEFAULT NULL,
  `TAX` double DEFAULT '0',
  `SOFT_ID` bigint DEFAULT NULL,
  `RATE` double NOT NULL DEFAULT '0',
  `CURRENCY_ID_ADD` int DEFAULT NULL,
  `CURRENCY_ID_OLD` int DEFAULT NULL,
  `CURRENCY_ID_TOTAL` int DEFAULT NULL,
  `TOTAL_AMOUNT` double DEFAULT NULL,
  `WITHTAX_TOTAL_AMOUNT` double DEFAULT NULL,
  `USER_IP` varchar(15) DEFAULT NULL,
  `USER_HEADER` varchar(500) DEFAULT NULL,
  `USER_LOCALE` varchar(2) DEFAULT NULL,
  `USER_OS` varchar(10) DEFAULT NULL,
  `USER_OS_PACK` varchar(10) DEFAULT NULL,
  `ORDER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket` (
  `BASKET_ID` bigint NOT NULL,
  `PRODUCT_ID` bigint DEFAULT NULL,
  `ORDER_ID` bigint DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  PRIMARY KEY (`BASKET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `big_images`
--

DROP TABLE IF EXISTS `big_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `big_images` (
  `BIG_IMAGES_ID` bigint NOT NULL,
  `IMGNAME` varchar(100) DEFAULT NULL,
  `IMG_URL` varchar(200) DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`BIG_IMAGES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `CALENDAR_ID` bigint NOT NULL,
  `SOFT_ID` bigint DEFAULT NULL,
  `HOLDDATE` int DEFAULT NULL,
  `FIST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `FATHER_NAME` varchar(50) DEFAULT NULL,
  `DOCUMENT_NUMBER` varchar(50) DEFAULT NULL,
  `DOCUMENT_TYPE` varchar(50) DEFAULT NULL,
  `AGE` varchar(50) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `NOTE` varchar(500) DEFAULT NULL,
  `PARENT_ID` bigint DEFAULT NULL,
  `NODE_NAME` varchar(500) DEFAULT NULL,
  `SITE_ID` bigint NOT NULL,
  `BASKET_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`CALENDAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CATALOG_ID` bigint NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `TAX` double DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `SITE_ID` bigint DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `PARENT_ID` bigint DEFAULT NULL,
  `CATALOG_IMAGE_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=765 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `catalog_images`
--

DROP TABLE IF EXISTS `catalog_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog_images` (
  `CATALOG_IMAGES_ID` bigint NOT NULL,
  `IMGNAME` varchar(100) DEFAULT NULL,
  `IMG_URL` varchar(200) DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`CATALOG_IMAGES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `CITY_ID` bigint DEFAULT NULL,
  `TELCODE` int DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `COUNTRY_ID` bigint DEFAULT NULL,
  `LANG_ID` int DEFAULT NULL,
  `LOCALE` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `COUNTRY_ID` bigint DEFAULT NULL,
  `TELCODE` int DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LOCALE` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria1`
--

DROP TABLE IF EXISTS `creteria1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria1` (
  `CRETERIA1_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA1_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria10`
--

DROP TABLE IF EXISTS `creteria10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria10` (
  `CRETERIA10_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA10_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria2`
--

DROP TABLE IF EXISTS `creteria2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria2` (
  `CRETERIA2_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA2_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria3`
--

DROP TABLE IF EXISTS `creteria3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria3` (
  `CRETERIA3_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA3_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria4`
--

DROP TABLE IF EXISTS `creteria4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria4` (
  `CRETERIA4_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA4_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria5`
--

DROP TABLE IF EXISTS `creteria5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria5` (
  `CRETERIA5_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA5_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria6`
--

DROP TABLE IF EXISTS `creteria6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria6` (
  `CRETERIA6_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA6_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria7`
--

DROP TABLE IF EXISTS `creteria7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria7` (
  `CRETERIA7_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA7_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria8`
--

DROP TABLE IF EXISTS `creteria8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria8` (
  `CRETERIA8_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA8_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `creteria9`
--

DROP TABLE IF EXISTS `creteria9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creteria9` (
  `CRETERIA9_ID` bigint NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `LINK_ID` bigint NOT NULL,
  `CATALOG_ID` bigint NOT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CRETERIA9_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `CURRENCY_ID` bigint NOT NULL,
  `RATE` double DEFAULT NULL,
  `CURRENCY_LABLE` varchar(10) DEFAULT NULL,
  `CURRENCY_DESC` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `CURRENCY_CD` varchar(100) DEFAULT NULL,
  `CURSDATE` varchar(100) DEFAULT NULL,
  `CHANGEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CURRENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency_converter`
--

DROP TABLE IF EXISTS `currency_converter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_converter` (
  `CURRENCY_ID` bigint NOT NULL,
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
-- Table structure for table `currency_rate`
--

DROP TABLE IF EXISTS `currency_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_rate` (
  `CURRENCY_RATE_ID` bigint NOT NULL,
  PRIMARY KEY (`CURRENCY_RATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `deliverystatus`
--

DROP TABLE IF EXISTS `deliverystatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliverystatus` (
  `DELIVERYSTATUS_ID` bigint NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `LANG` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`DELIVERYSTATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dinamic_fields`
--

DROP TABLE IF EXISTS `dinamic_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinamic_fields` (
  `DINAMIC_FIELD_ID` bigint NOT NULL,
  `PRODUCT_ID` bigint DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`DINAMIC_FIELD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `domain_proc`
--

DROP TABLE IF EXISTS `domain_proc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domain_proc` (
  `DOMAIN_PROC_ID` bigint NOT NULL,
  `LAST_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `FILE_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FILEDATA` binary(255) DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `SIZE` varchar(15) DEFAULT NULL,
  `PATH` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `IMAGE_ID` bigint NOT NULL,
  `IMGNAME` varchar(50) DEFAULT NULL,
  `IMG_URL` varchar(500) DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`IMAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lang`
--

DROP TABLE IF EXISTS `lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lang` (
  `LANG_ID` bigint NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`LANG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `licence`
--

DROP TABLE IF EXISTS `licence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licence` (
  `LICENCE_ID` bigint NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `SITE_ID` bigint DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`LICENCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `one_sequences`
--

DROP TABLE IF EXISTS `one_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `one_sequences` (
  `ID` bigint NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ORDER_ID` bigint NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `DELIVERY_TIMEEND` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `AMOUNT` double NOT NULL DEFAULT '0',
  `TAX` double DEFAULT NULL,
  `END_AMOUNT` double DEFAULT NULL,
  `DELIVERY_AMOUNT` double DEFAULT NULL,
  `DELIVERY_LONG` int DEFAULT NULL,
  `PAYSTATUS_ID` bigint DEFAULT NULL,
  `DELIVERY_START` timestamp NULL DEFAULT NULL,
  `CDATE` timestamp NULL DEFAULT NULL,
  `CURRENCY_ID` bigint DEFAULT NULL,
  `COUNTRY_ID` bigint DEFAULT NULL,
  `CITY_ID` bigint DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `PHONE` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `ZIP` int DEFAULT NULL,
  `TREE_ID` int DEFAULT NULL,
  `IMEI` int DEFAULT NULL,
  `PHONEMODEL_ID` int DEFAULT NULL,
  `DELIVERYSTATUS_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders_hist`
--

DROP TABLE IF EXISTS `orders_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_hist` (
  `ORDER_HIST_ID` bigint NOT NULL,
  `ORDER_HIST_CD` bigint DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `POSITION_ID` int DEFAULT NULL,
  `DELIVERY_TIMEEND` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `AMOUNT` double DEFAULT NULL,
  `TAX` double DEFAULT NULL,
  `END_AMOUNT` double DEFAULT NULL,
  `DELIVERY_AMOUNT` double DEFAULT NULL,
  `CARD_ID` int DEFAULT NULL,
  `DELIVERY_LONG` int DEFAULT NULL,
  `SHIPMENT_ADDRESS_ID` int DEFAULT NULL,
  `SERIAL_NUMBER` varchar(50) DEFAULT NULL,
  `PAYSTATUS_ID` bigint DEFAULT NULL,
  `DELIVERYSTATUS_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ORDER_HIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pay_gateway`
--

DROP TABLE IF EXISTS `pay_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_gateway` (
  `PAY_GATEWAY_ID` bigint NOT NULL,
  `NAME_GATEWAY` varchar(50) DEFAULT NULL,
  `FULLNAME_GATEWAY` varchar(500) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `FAX` varchar(15) DEFAULT NULL,
  `SITE_URL` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paystatus`
--

DROP TABLE IF EXISTS `paystatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paystatus` (
  `PAYSTATUS_ID` bigint NOT NULL,
  `LABLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `LANG` longtext,
  PRIMARY KEY (`PAYSTATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paysystem`
--

DROP TABLE IF EXISTS `paysystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paysystem` (
  `PAYSYSTEM_ID` bigint NOT NULL,
  `PAYSYSTEM_CD` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `IMG_URL` varchar(500) DEFAULT NULL,
  `PAY_GATEWAY_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`PAYSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `portlettype`
--

DROP TABLE IF EXISTS `portlettype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portlettype` (
  `PORTLETTYPE_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`PORTLETTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salelogic`
--

DROP TABLE IF EXISTS `salelogic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salelogic` (
  `SALELOGIC_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIBE` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`SALELOGIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_status_events`
--

DROP TABLE IF EXISTS `service_status_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_status_events` (
  `SITE_ID` bigint DEFAULT NULL,
  `DATE_SEND` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `SERVICE_STATUS` int DEFAULT NULL,
  `ISNOTIFY` tinyint(1) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_status_events_history`
--

DROP TABLE IF EXISTS `service_status_events_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_status_events_history` (
  `SITE_ID` bigint DEFAULT NULL,
  `DATE_SEND` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SERVICE_STATUS` int DEFAULT NULL,
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `ISNOTIFY` tinyint(1) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `SHOP_ID` bigint NOT NULL AUTO_INCREMENT,
  `SHOP_CD` int NOT NULL,
  `OWNER_ID` bigint NOT NULL,
  `LOGIN` varchar(50) DEFAULT NULL,
  `SITE_ID` bigint DEFAULT NULL,
  `PAY_GATEWAY_ID` bigint DEFAULT NULL,
  `PASSWD` varchar(50) DEFAULT NULL,
  `CDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site` (
  `SITE_ID` bigint NOT NULL,
  `HOST` varchar(100) DEFAULT NULL,
  `OWNER` bigint DEFAULT NULL,
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
-- Table structure for table `soft`
--

DROP TABLE IF EXISTS `soft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soft` (
  `SOFT_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `VERSION` varchar(10) DEFAULT NULL,
  `COST` double NOT NULL DEFAULT '0',
  `CURRENCY` int NOT NULL DEFAULT '1',
  `SERIAL_NUBMER` varchar(50) DEFAULT NULL,
  `FILE_ID` bigint DEFAULT NULL,
  `TYPE_ID` bigint DEFAULT NULL,
  `LEVELUP` bigint DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `PHONETYPE_ID` int DEFAULT NULL,
  `PROGNAME_ID` bigint DEFAULT NULL,
  `IMAGE_ID` bigint DEFAULT NULL,
  `BIGIMAGE_ID` bigint DEFAULT NULL,
  `WEIGHT` double DEFAULT NULL,
  `COUNT` int DEFAULT NULL,
  `LANG_ID` bigint DEFAULT NULL,
  `PHONEMODEL_ID` int DEFAULT NULL,
  `LICENCE_ID` bigint DEFAULT NULL,
  `CATALOG_ID` bigint DEFAULT NULL,
  `SALELOGIC_ID` bigint DEFAULT NULL,
  `TREE_ID` bigint DEFAULT NULL,
  `CARD_NUMBER` int DEFAULT NULL,
  `CARD_CODE` int DEFAULT NULL,
  `START_DIAL_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `END_DIAL_TIME` timestamp NULL DEFAULT NULL,
  `START_ACTIVATION_CARD` tinyint(1) NOT NULL DEFAULT '0',
  `END_ACTIVATION_CARD` tinyint(1) NOT NULL DEFAULT '0',
  `TYPE_CARD_ID` int DEFAULT NULL,
  `PRODUCT_CODE` int DEFAULT NULL,
  `FULLDESCRIPTION` varchar(5000) DEFAULT NULL,
  `SITE_ID` bigint DEFAULT NULL,
  `SEARCH` varchar(1) DEFAULT NULL,
  `PORTLETTYPE_ID` bigint NOT NULL DEFAULT '0',
  `CRETERIA1_ID` bigint DEFAULT NULL,
  `CRETERIA2_ID` bigint DEFAULT NULL,
  `CRETERIA3_ID` bigint DEFAULT NULL,
  `CRETERIA4_ID` bigint DEFAULT NULL,
  `CRETERIA5_ID` bigint DEFAULT NULL,
  `CRETERIA6_ID` bigint DEFAULT NULL,
  `CRETERIA7_ID` bigint DEFAULT NULL,
  `CRETERIA8_ID` bigint DEFAULT NULL,
  `CRETERIA9_ID` bigint DEFAULT NULL,
  `CRETERIA10_ID` bigint DEFAULT NULL,
  `STATISTIC_ID` bigint NOT NULL DEFAULT '0',
  `CDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CRETERIA11_ID` bigint DEFAULT NULL,
  `CRETERIA12_ID` bigint DEFAULT NULL,
  `CRETERIA13_ID` bigint DEFAULT NULL,
  `CRETERIA14_ID` bigint DEFAULT NULL,
  `CRETERIA15_ID` bigint DEFAULT NULL,
  `SQUARE` int DEFAULT NULL,
  `RATING_SUMM1` int NOT NULL DEFAULT '0',
  `RATING_SUMM2` int NOT NULL DEFAULT '0',
  `RATING_SUMM3` int NOT NULL DEFAULT '0',
  `COUNTPOST_RATING1` int NOT NULL DEFAULT '0',
  `COUNTPOST_RATING2` int NOT NULL DEFAULT '0',
  `COUNTPOST_RATING3` int NOT NULL DEFAULT '0',
  `MIDLE_BAL1` int NOT NULL DEFAULT '0',
  `MIDLE_BAL2` int NOT NULL DEFAULT '0',
  `MIDLE_BAL3` int NOT NULL DEFAULT '0',
  `SHOW_RATING1` tinyint(1) NOT NULL DEFAULT '0',
  `SHOW_RATING2` tinyint(1) NOT NULL DEFAULT '0',
  `SHOW_RATING3` tinyint(1) NOT NULL DEFAULT '0',
  `SHOW_BLOG` tinyint(1) NOT NULL DEFAULT '0',
  `NAME2` varchar(50) DEFAULT NULL,
  `SEARCH2` varchar(1) DEFAULT NULL,
  `AMOUNT1` double NOT NULL DEFAULT '0',
  `AMOUNT2` double NOT NULL DEFAULT '0',
  `AMOUNT3` double NOT NULL DEFAULT '0',
  `JSP_URL` varchar(100) DEFAULT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SOFT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_session`
--

DROP TABLE IF EXISTS `store_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_session` (
  `IDSESSION_HASH1` bigint DEFAULT NULL,
  `IDSESSION_HASH2` bigint DEFAULT NULL,
  `IDSESSION_HASH3` bigint DEFAULT NULL,
  `IDSESSION_HASH4` bigint DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `TYPE` varchar(200) DEFAULT NULL,
  `LASTURL` varchar(500) DEFAULT NULL,
  `CLASSBODY` varchar(250) DEFAULT NULL,
  `BCLASSBODY` longblob,
  `ACTIVE` tinyint(1) NOT NULL,
  `CDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tree`
--

DROP TABLE IF EXISTS `tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tree` (
  `ITEM_ID` bigint NOT NULL,
  `NODE_ID` bigint DEFAULT NULL,
  `FOLDER_NAME` varchar(50) DEFAULT NULL,
  `OWNER_ID` bigint DEFAULT NULL,
  `GROUP_ID` int DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `DOC_ID` int DEFAULT NULL,
  `CDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_TOUCH_ID` int DEFAULT NULL,
  `LAST_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuser` (
  `USER_ID` bigint NOT NULL,
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
  `BIRTHDAY` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REGDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LEVELUP_CD` bigint DEFAULT NULL,
  `BANK_CD` bigint DEFAULT NULL,
  `ACVIVE_SESSION` tinyint(1) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `COUNTRY` varchar(20) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `STATE` varchar(10) DEFAULT NULL,
  `SCOUNTRY` varchar(50) DEFAULT NULL,
  `MIDDLENAME` varchar(50) DEFAULT NULL,
  `CITY_ID` bigint DEFAULT NULL,
  `COUNTRY_ID` bigint DEFAULT NULL,
  `CURRENCY_ID` bigint DEFAULT NULL,
  `TREE_ID` bigint DEFAULT NULL,
  `SITE_ID` bigint NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `typesoft`
--

DROP TABLE IF EXISTS `typesoft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typesoft` (
  `TYPE_ID` bigint NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `TYPE_LABLE` varchar(50) DEFAULT NULL,
  `TYPE_DESC` varchar(100) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `TAX` double NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `xsl_style`
--

DROP TABLE IF EXISTS `xsl_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xsl_style` (
  `XSL_STYLE_ID` int NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `PRODUCER_ID` int NOT NULL,
  `OWNER_ID` bigint NOT NULL,
  `COST` double NOT NULL,
  `CURRENCY_ID` bigint NOT NULL,
  `SYS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SITE_ID` bigint NOT NULL,
  `DIRNAME` varchar(200) DEFAULT NULL,
  `XSL_SUBJ_ID` int DEFAULT NULL,
  PRIMARY KEY (`XSL_STYLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `xsl_subj`
--

DROP TABLE IF EXISTS `xsl_subj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xsl_subj` (
  `XSL_SUBJ_ID` bigint NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`XSL_SUBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 21:06:33

INSERT INTO cmsdb.account (ACCOUNT_ID,USER_ID,AMOUNT,CURR,DATE_INPUT,DESCRIPTION,COMPLETE,ACTIVE,CURRENCY_ID,TREE_ID) VALUES
	 (100728482950021286,100728482950021285,0.0,3,'2024-02-26 00:00:00',' new_account ',0,0,1,NULL),
	 (100728482950021288,100728482950021287,0.0,3,'2024-02-26 00:00:00',' new_account ',0,0,1,NULL);
INSERT INTO cmsdb.`catalog` (CATALOG_ID,USER_ID,LABLE,ACTIVE,TAX,DESCRIPTION,SITE_ID,LANG_ID,PARENT_ID,CATALOG_IMAGE_ID) VALUES
	 (-1,NULL,'News',1,NULL,NULL,2,2,0,NULL),
	 (-2,NULL,'Main page',1,NULL,NULL,2,2,0,NULL),
	 (100728482950021289,NULL,'Selection1',1,NULL,NULL,2,2,0,NULL),
	 (100728482950021290,NULL,'Sub selection1',1,NULL,NULL,2,2,100728482950021289,NULL),
	 (100728482950021291,NULL,'Sub selection11',1,NULL,NULL,2,2,100728482950021289,NULL),
	 (100728482950021292,NULL,'Sub selection111',1,NULL,NULL,2,2,100728482950021289,NULL),
	 (100728482950021293,NULL,'Selection2',1,NULL,NULL,2,2,0,NULL),
	 (100728482950021294,NULL,'Sub selection2',1,NULL,NULL,2,2,100728482950021293,NULL),
	 (100728482950021295,NULL,'Sub selection22',1,NULL,NULL,2,2,100728482950021293,NULL),
	 (100728482950021296,NULL,'Sub selection222',1,NULL,NULL,2,2,100728482950021293,NULL);
INSERT INTO cmsdb.`catalog` (CATALOG_ID,USER_ID,LABLE,ACTIVE,TAX,DESCRIPTION,SITE_ID,LANG_ID,PARENT_ID,CATALOG_IMAGE_ID) VALUES
	 (100728482950021297,NULL,'Selection3',1,NULL,NULL,2,2,0,NULL),
	 (100728482950021298,NULL,'Sub selection3',1,NULL,NULL,2,2,100728482950021297,NULL),
	 (100728482950021299,NULL,'Sub selection33',1,NULL,NULL,2,2,100728482950021297,NULL),
	 (100728482950021300,NULL,'Sub selection333',1,NULL,NULL,2,2,100728482950021297,NULL),
	 (100728482950021301,NULL,'Selection4',1,NULL,NULL,2,2,0,NULL),
	 (100728482950021302,NULL,'Sub selection4',1,NULL,NULL,2,2,100728482950021301,NULL),
	 (100728482950021303,NULL,'Sub selection44',1,NULL,NULL,2,2,100728482950021301,NULL),
	 (100728482950021304,NULL,'Sub selection444',1,NULL,NULL,2,2,100728482950021301,NULL);
INSERT INTO cmsdb.city (CITY_ID,TELCODE,NAME,FULLNAME,COUNTRY_ID,LANG_ID,LOCALE) VALUES
	 (1,812,'St. Petersburg','St. Petersburg',1,2,'EN'),
	 (2,95,'Moscow','Moscow',1,2,'EN'),
	 (107,22,'York','York',2,2,'EN'),
	 (103,22,'London','London',3,2,'EN'),
	 (700,61,'Ottawa','Ottawa',13,2,'EN'),
	 (7001,2,'Canberra','Canberra',20,2,'EN');
INSERT INTO cmsdb.country (COUNTRY_ID,TELCODE,NAME,FULLNAME,LANG_ID,LOCALE) VALUES
	 (1,7,'Russia','Russia',2,'EN'),
	 (2,1,'USA','USA',2,'EN'),
	 (3,44,'United Kingdom','United Kingdom',2,'EN'),
	 (13,1,'Canada','Canada',2,'EN'),
	 (20,1,'Australia','Australia',2,'EN');
INSERT INTO cmsdb.creteria1 (CRETERIA1_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion1'),
	 (1,'Test1',1,2,0,2,'Criterion1'),
	 (2,'Test2',1,2,0,2,'Criterion1');
INSERT INTO cmsdb.creteria10 (CRETERIA10_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion10');
INSERT INTO cmsdb.creteria2 (CRETERIA2_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion2');
INSERT INTO cmsdb.creteria3 (CRETERIA3_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion3');
INSERT INTO cmsdb.creteria4 (CRETERIA4_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion4');
INSERT INTO cmsdb.creteria5 (CRETERIA5_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion5');
INSERT INTO cmsdb.creteria6 (CRETERIA6_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion6');
INSERT INTO cmsdb.creteria7 (CRETERIA7_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion7');
INSERT INTO cmsdb.creteria8 (CRETERIA8_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion8');
INSERT INTO cmsdb.creteria9 (CRETERIA9_ID,NAME,ACTIVE,LANG_ID,LINK_ID,CATALOG_ID,LABEL) VALUES
	 (0,'Not chosen',1,2,0,2,'Criterion9');
INSERT INTO cmsdb.currency (CURRENCY_ID,RATE,CURRENCY_LABLE,CURRENCY_DESC,ACTIVE,CURRENCY_CD,CURSDATE,CHANGEDATE) VALUES
	 (0,0.0,'UNKNOWN','Not selected',1,'0',NULL,'2024-02-26 04:25:13'),
	 (1,36.0,'USD','U.S Dollar',1,'USD',NULL,'2024-02-26 04:25:13'),
	 (2,45.0,'EUR',' EURO',1,'EUR',NULL,'2024-02-26 04:25:13'),
	 (3,1.0,'RUB','RF Ruble',1,'RUB',NULL,'2024-02-26 04:25:13'),
	 (4,1.0,'CNY','China money',1,'CNY',NULL,'2024-02-26 04:25:13'),
	 (5,1.0,'JPY','Japan Yen',1,'JPY',NULL,'2024-02-26 04:25:13'),
	 (6,1.0,'TRY','Turkey Lira',1,'TRY',NULL,'2024-02-26 04:25:13'),
	 (7,1.0,'MXN','Mexico Peso',1,'MXN',NULL,'2024-02-26 04:25:13'),
	 (8,1.0,'CAD','United Kingdom Pound',1,'CAD',NULL,'2024-02-26 04:25:13'),
	 (9,1.0,'GBP','Brazil Real',1,'GBP',NULL,'2024-02-26 04:25:13');
INSERT INTO cmsdb.currency (CURRENCY_ID,RATE,CURRENCY_LABLE,CURRENCY_DESC,ACTIVE,CURRENCY_CD,CURSDATE,CHANGEDATE) VALUES
	 (10,1.0,'BRL','India Rupee',1,'BRL',NULL,'2024-02-26 04:25:13'),
	 (11,1.0,'INR','Bitcoin',1,'INR',NULL,'2024-02-26 04:25:13'),
	 (12,1.0,'BTC','Litecoin',1,'BTC',NULL,'2024-02-26 04:25:13'),
	 (13,1.0,'LTC','Ethereum',1,'LTC',NULL,'2024-02-26 04:25:13'),
	 (14,1.0,'ETH','PayPal Dollar',1,'ETH',NULL,'2024-02-26 04:25:13'),
	 (15,1.0,'PYUSD','U.S Digital Dollar',1,'PYUSD',NULL,'2024-02-26 04:25:13'),
	 (16,1.0,'CBDC','ERROR',1,'CBDC',NULL,'2024-02-26 04:25:13');
INSERT INTO cmsdb.lang (LANG_ID,LABLE,DESCRIPTION,ACTIVE) VALUES
	 (1,'ru','Russian',1),
	 (2,'en','English',1);
INSERT INTO cmsdb.shop (SHOP_CD,OWNER_ID,LOGIN,SITE_ID,PAY_GATEWAY_ID,PASSWD,CDATE) VALUES
	 (84473,100728482950021287,'HCO-CENTE-406',2,1,'91KiBFRtE8fF7VHc8tvr','2024-02-26 00:00:00');
INSERT INTO cmsdb.site (SITE_ID,HOST,OWNER,ACTIVE,HOME_DIR,SITE_DIR,PERSON,PHONE,ADDRESS,SUBJECT_SITE,NICK_SITE,COMPANY_NAME) VALUES
	 (2,'localhost',1,1,'shops.online-spb.com','shops.online-spb.com','Administrator','+z(xxx)yyy-xxxx','',NULL,NULL,NULL);
INSERT INTO cmsdb.soft (SOFT_ID,NAME,DESCRIPTION,VERSION,COST,CURRENCY,SERIAL_NUBMER,FILE_ID,TYPE_ID,LEVELUP,ACTIVE,USER_ID,PHONETYPE_ID,PROGNAME_ID,IMAGE_ID,BIGIMAGE_ID,WEIGHT,COUNT,LANG_ID,PHONEMODEL_ID,LICENCE_ID,CATALOG_ID,SALELOGIC_ID,TREE_ID,CARD_NUMBER,CARD_CODE,START_DIAL_TIME,END_DIAL_TIME,START_ACTIVATION_CARD,END_ACTIVATION_CARD,TYPE_CARD_ID,PRODUCT_CODE,FULLDESCRIPTION,SITE_ID,`SEARCH`,PORTLETTYPE_ID,CRETERIA1_ID,CRETERIA2_ID,CRETERIA3_ID,CRETERIA4_ID,CRETERIA5_ID,CRETERIA6_ID,CRETERIA7_ID,CRETERIA8_ID,CRETERIA9_ID,CRETERIA10_ID,STATISTIC_ID,CDATE,CRETERIA11_ID,CRETERIA12_ID,CRETERIA13_ID,CRETERIA14_ID,CRETERIA15_ID,SQUARE,RATING_SUMM1,RATING_SUMM2,RATING_SUMM3,COUNTPOST_RATING1,COUNTPOST_RATING2,COUNTPOST_RATING3,MIDLE_BAL1,MIDLE_BAL2,MIDLE_BAL3,SHOW_RATING1,SHOW_RATING2,SHOW_RATING3,SHOW_BLOG,NAME2,SEARCH2,AMOUNT1,AMOUNT2,AMOUNT3,JSP_URL,COLOR) VALUES
	 (100728482950021305,'New arrivals section 1','<r> New arrivals section -  Search possibilities of Internet shop </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> 1. For high-grade work CMS Business One, you need to adjust the expanded search in criteria </r><r> under the problems. It will allow your clients to find at once the necessary goods, the information or the document. </r><r> 2. It is not necessary to adjust search in the text, it works by default. </r><r> 3. (Only at registered users) it is not necessary to adjust search in the subject authority, it works by default. </r><r> the Instruction on adjustment of the expanded search on criteria </r><r> Our system provides 10 edited criteria and search in the price </r><r> Editing of criteria is made in the form of addition of the information module in subsection INSTALLATION of CRITERIA FOR SEARCH of THIS INFORMATION ON the SITE. </r><r> Criteria can be independent. For example: the Manufacturer, an engineering Kind, Colour - all these criteria are independent from each other. </r><r> And can be dependent on any criterion. For example: the Country-city-area. Here the criterion the Country - independent, criterion the City depends on criterion the Country, and criterion Area the City depends on criterion. </r><r> </r><r> Dependence of criterion which you change, is advanced by a horizontal pink strip. The changeable criterion depends on the criterion allocated with a pink strip. </r><r> the Step-by-step description of input of independent criterion: </r><r> we Will assume that we need to enter criterion the Manufacturer with its items. </r><r> 1. We allocate the necessary criterion with a pink strip, for example Kriterij1 </r><r> 2. We press the button to CHANGE. The form for change of Kriterija1 will open. </r><r> 3. In the top part of the form we change the name Kriterij1 on the Manufacturer and we press the button to CHANGE. Now our criterion on main page will be called the Manufacturer. </r><r> 4. In the bottom part of the form it is visible it is not chosen - this record is not edited and DOES NOT LEAVE. </r><r> 5. To add an item in criterion, we press the button to ADD. To opened form we write the necessary item, for example Proizvoditel1, and we press the button to SAVE. Other items it is got similarly. </r><r> For removal or editing of an item of criterion use respective buttons opposite to an item. </r><r> </r><r> Other independent criteria it is got similarly. Look items 1-5 </r><r> </r><r> the Step-by-step description of input of dependent criterion: </r><r> we Will assume that at us the independent criterion the Country with the items is already entered and we need to enter dependent criterion the City. </r><r> 1. We allocate with a pink strip criterion on which our criterion the City, in this case criterion the Country will depend. </r><r> Strana1 Is chosen in criterion the Country the necessary item, for example. </r><r> 2. Opposite to criterion the City we press the button to CHANGE. The form for criterion change will open. </r><r> 3. In the top part of the form we change the criterion name for the City and we press the button to CHANGE. </r><r> 4. We get the necessary items Gorod1-1, Gorod1-2 etc., corresponding to the chosen item Strana1. </r><r> If it is necessary to continue input of cities on other countries we come back to criterion the Country and we choose an item Strana2. </r><r> Further we pass to item 2 etc. </r><r> </r><r> If at you quantity of criteria less than ten the remained criteria can be hidden, by removal of the name of criterion. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021306,'New arrovals ','<r> Discussion of the goods, service, news </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r>We have built in a forum ours CMS Business One. </r><r>For each information or news module the discussion system </r><r> the discussion System can be included or switched off for each concrete module. </r><r> All new messages gather in С‚РѕРї on main page for the review of fresh discussions </r><r> </r><r> forum Inclusion is made in the form of addition of the information or news module by an option choice to INCLUDE DISCUSSION. </r><r> By default the forum is switched off. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021307,'New arrovals 2','<r> News module of Internet shop </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r>We have established the news module in Internet shop </r><r> Novosnoj the module displays the text, a picture and addition date. </r><r> the Module of news appears on all pages in all sections. </r><r> your news does not remain not noticed where there would be no your client. </r><r> </r><r> the Instruction on addition of the news module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press to ADD the INFORMATION MODULE With SEARCH IN CRITERIA. </r><r> 3. We find subsection the FORM of ADDITION OR INFORMATION CHANGE. </r><r> 4. In the field HEADING we write heading. </r><r> 5. In the field CHOSEN SECTION we choose NEWS. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 8. In the field the PRICE the price not to mark, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written a brief information on news. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of news. </r><r> 13. At an option choice to INCLUDE VOTING, your news has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your news has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE the significance should be established is published. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021308,'Advertising','<r> Advertising place in Internet shop </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r>We have built in for you advertising blocks that you could sell the space in your Internet shop. </r><r> On the right and to the left of the central block advertising modules </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021309,'Internet shop','<r>Reference to your Internet shop </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-1,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> your Internet shop is accessible  by link http://www.siteforyou.com/Productlist.jsp?site=2</r><r> also you can buy domain here </r><r> Save somewhere this information. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021310,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021311,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021312,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021313,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021314,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL);
INSERT INTO cmsdb.soft (SOFT_ID,NAME,DESCRIPTION,VERSION,COST,CURRENCY,SERIAL_NUBMER,FILE_ID,TYPE_ID,LEVELUP,ACTIVE,USER_ID,PHONETYPE_ID,PROGNAME_ID,IMAGE_ID,BIGIMAGE_ID,WEIGHT,COUNT,LANG_ID,PHONEMODEL_ID,LICENCE_ID,CATALOG_ID,SALELOGIC_ID,TREE_ID,CARD_NUMBER,CARD_CODE,START_DIAL_TIME,END_DIAL_TIME,START_ACTIVATION_CARD,END_ACTIVATION_CARD,TYPE_CARD_ID,PRODUCT_CODE,FULLDESCRIPTION,SITE_ID,`SEARCH`,PORTLETTYPE_ID,CRETERIA1_ID,CRETERIA2_ID,CRETERIA3_ID,CRETERIA4_ID,CRETERIA5_ID,CRETERIA6_ID,CRETERIA7_ID,CRETERIA8_ID,CRETERIA9_ID,CRETERIA10_ID,STATISTIC_ID,CDATE,CRETERIA11_ID,CRETERIA12_ID,CRETERIA13_ID,CRETERIA14_ID,CRETERIA15_ID,SQUARE,RATING_SUMM1,RATING_SUMM2,RATING_SUMM3,COUNTPOST_RATING1,COUNTPOST_RATING2,COUNTPOST_RATING3,MIDLE_BAL1,MIDLE_BAL2,MIDLE_BAL3,SHOW_RATING1,SHOW_RATING2,SHOW_RATING3,SHOW_BLOG,NAME2,SEARCH2,AMOUNT1,AMOUNT2,AMOUNT3,JSP_URL,COLOR) VALUES
	 (100728482950021315,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021316,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021317,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021318,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021319,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,3,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021320,' Item review section 1 ','<r> Item review section - You can talk about  goods or news and a price of sheet on your forum.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,100728482950021319,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r>  The information module for main page 1.</r><r> You need to change the contents of this module to place here  your information. </r><r> For this you should enter on site under  your the password  </r><r> So you can observe buttons:  remove, edit, update (To add the new module). </r><r> In during  change of the informational unit you can put its other section .</r><r> You also can edit ,  add, delete this section name. </r><r> Also you can change parameters  criteria for search of  information  unit </r><r> For this purpose should change criteria on the form of change of the information with title (Installation of criteria for information search on a site ). </r>',2,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021321,'Item review section 2 ','<r>Item review section - Thanks from Center Business Solutions Ltd  that  you using  this CMS .</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,100728482950021319,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> The information module for main page 1.</r><r> You need to change the contents of this module to place here  your information. </r><r> For this you should enter on site under  your the password  </r><r> So you can observe buttons:  remove, edit, update (To add the new module). </r><r> In during  change of the informational unit you can put its other section .</r><r> You also can edit ,  add, delete this section name. </r><r> Also you can change parameters  criteria for search of  information  unit </r><r> For this purpose should change criteria on the form of change of the information with title (Installation of criteria for information search on a site ). </r>',2,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021322,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,10,10,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021323,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,10,10,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021324,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL);
INSERT INTO cmsdb.soft (SOFT_ID,NAME,DESCRIPTION,VERSION,COST,CURRENCY,SERIAL_NUBMER,FILE_ID,TYPE_ID,LEVELUP,ACTIVE,USER_ID,PHONETYPE_ID,PROGNAME_ID,IMAGE_ID,BIGIMAGE_ID,WEIGHT,COUNT,LANG_ID,PHONEMODEL_ID,LICENCE_ID,CATALOG_ID,SALELOGIC_ID,TREE_ID,CARD_NUMBER,CARD_CODE,START_DIAL_TIME,END_DIAL_TIME,START_ACTIVATION_CARD,END_ACTIVATION_CARD,TYPE_CARD_ID,PRODUCT_CODE,FULLDESCRIPTION,SITE_ID,`SEARCH`,PORTLETTYPE_ID,CRETERIA1_ID,CRETERIA2_ID,CRETERIA3_ID,CRETERIA4_ID,CRETERIA5_ID,CRETERIA6_ID,CRETERIA7_ID,CRETERIA8_ID,CRETERIA9_ID,CRETERIA10_ID,STATISTIC_ID,CDATE,CRETERIA11_ID,CRETERIA12_ID,CRETERIA13_ID,CRETERIA14_ID,CRETERIA15_ID,SQUARE,RATING_SUMM1,RATING_SUMM2,RATING_SUMM3,COUNTPOST_RATING1,COUNTPOST_RATING2,COUNTPOST_RATING3,MIDLE_BAL1,MIDLE_BAL2,MIDLE_BAL3,SHOW_RATING1,SHOW_RATING2,SHOW_RATING3,SHOW_BLOG,NAME2,SEARCH2,AMOUNT1,AMOUNT2,AMOUNT3,JSP_URL,COLOR) VALUES
	 (100728482950021325,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021326,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021327,'Information module','<r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> </r><r> In the information module  you can  place  the text, to place  images on forms with short and detailed description  and attach files, to set a price, and also to include voting or a forum for each concrete information module. </r><r> the Instruction on addition of the information module: </r><r> 1. To come into MANAGEMENT of the SITE having pressed the button to UPDATE. </r><r> 2. To press (To add the information, the goods, news in the central module with search in criteria)  </r><r> 3. We find subsection ( Form for an institution or information change on a site ). </r><r> 4. In the field HEADING we will write heading. </r><r> 5. In the field CHOSEN SECTION you can chose a section  where is place of  information module.If section is not exist then add new section in (Form for catalogue construction ) for detail see creation catalog. </r><r> 6. In the field IN OTHER LANGUAGE FOR SEARCH of anything it is not necessary to enter HEADING. Is in working out. </r><r> 7. In the field PICTURE 1 it is given the chance to add the image on page with the short description. The image can be added in two methods: UPLOAD PICTURE 1 FOR SHORT - upload from the computer of the user, PICTURE 1 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 8. If you mark the price in the field the PRICE it is displayed. If the price is not necessary to you, keep a zero. </r><r> 9. In the field the BRIEF INFORMATION is written goods or service brief information. </r><r> 10. In the field PICTURE 2 it is given the chance to add the image on page with detailed description. The image can be added in two methods: UPLOAD PICTURE 2 FOR DETAILED - upload from the computer of the user, PICTURE 2 FROM BASE - to choose from present base. The image uploading in any formats which are supported by your browser. The maximum size of the image 1,5 Mb. </r><r> 11. In the field the DETAILED INFORMATION is written the detailed information of the goods or service. </r><r> 12. In the field ATTACH the FILE you can to attach a file in two methods: UPLOAD the FILE - upload from the computer of the user to CHOOSE the FILE - to choose from present base. A file uploading in any format which supports your browser. The maximum size of a file. 1,5 Mb. </r><r> 13. At an option choice to INCLUDE VOTING, your information module has a voting. By default this option is switched off. </r><r> 14. At an option choice to INCLUDE DISCUSSION, your information module has a forum. By default this option is switched off. </r><r> 15. In an option I APPROVE, if choose it is published, the information module will be displayed only in the catalogue. If will choose to SHOW ON MAIN PAGE this module will be displayed both in the catalogue and on main page. </r><r> 16. Enter from a picture a code. </r><r> 17. Press the button to SAVE. </r>',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021328,' About company  ','<r> About company.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> Name of company: your company name <r> Phone +1(xxx)yyy-xx-xx  </r><r> Fax:  +1(xxx)yyy-xx-xx </r><r> EMail:  your@mail </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021329,' Delivery ','<r> Delivery.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> Delivery from your company name</r><r> Payment perform by way  translation of money resources on the company score through payment systems or through bank by direct payment </r><r> Account Web Money: 1111222333444 </r><r> Account Yandex Money: 333442234455 </r><r> Bank Of Test ltd  ...... </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021330,'Return policy','<r> How can I return item.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> Order payment your company name </r><r> Payment perform by way  translation of money resources on the company score through payment systems or through bank by direct payment </r><r> Account Web Money: 1111222333444 </r><r> Account Yandex Money: 333442234455 </r><r> Bank Of Test ltd  ...... </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021331,'Contacts us','<r> Contacts us.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> Contacts us your company name </r><r> Payment perform by way  translation of money resources on the company score through payment systems or through bank by direct payment </r><r> Account Web Money: 1111222333444 </r><r> Account Yandex Money: 333442234455 </r><r> Bank Of Test ltd  ...... </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL),
	 (100728482950021332,'Careers','<r> Careers.</r>',NULL,0.0,1,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,-2,NULL,NULL,NULL,NULL,'2024-02-26 04:25:13',NULL,0,0,NULL,NULL,'<r> Careers with your company name </r><r> Payment perform by way  translation of money resources on the company score through payment systems or through bank by direct payment </r><r> Account Web Money: 1111222333444 </r><r> Account Yandex Money: 333442234455 </r><r> Bank Of Test ltd  ...... </r>',2,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2024-02-26 04:25:13',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,0.0,0.0,0.0,NULL,NULL);
INSERT INTO cmsdb.tuser (USER_ID,LOGIN,PASSWD,FIRST_NAME,LAST_NAME,COMPANY,E_MAIL,PHONE,MOBIL_PHONE,FAX,ICQ,WEBSITE,QUESTION,ANSWER,IDSESSION,BIRTHDAY,REGDATE,LEVELUP_CD,BANK_CD,ACVIVE_SESSION,ACTIVE,COUNTRY,CITY,ZIP,STATE,SCOUNTRY,MIDDLENAME,CITY_ID,COUNTRY_ID,CURRENCY_ID,TREE_ID,SITE_ID) VALUES
	 (100728482950021285,'admin','admin',NULL,NULL,' your company name ',' your@mail',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-02-26 00:00:00','2024-02-26 00:00:00',2,0,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,NULL,2),
	 (100728482950021287,'user','user',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-02-26 00:00:00','2024-02-26 00:00:00',0,0,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,NULL,2);
INSERT INTO cmsdb.typesoft (TYPE_ID,USER_ID,TYPE_LABLE,TYPE_DESC,ACTIVE,TAX) VALUES
	 (0,1,'Default mode','Default mode',1,0.0),
	 (1,1,'Make item invisible on your page','Make item invisible on your page',1,0.0),
	 (2,1,'Make item visible in Marker place','Make item visible in Marker place',1,0.0),
	 (3,1,'Make item visible on your page','Make item visible on your page',1,0.0);
