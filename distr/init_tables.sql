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
