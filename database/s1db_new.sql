-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: s1db
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(50) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'CK');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Perfume');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_in_store`
--

DROP TABLE IF EXISTS `category_in_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_in_store` (
  `cat_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`cat_id`,`store_id`),
  KEY `fk_Category_has_Store_Store1_idx` (`store_id`),
  KEY `fk_Category_has_Store_Category_idx` (`cat_id`),
  CONSTRAINT `fk_Category_has_Store_Category` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Store_Store1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_in_store`
--

LOCK TABLES `category_in_store` WRITE;
/*!40000 ALTER TABLE `category_in_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_in_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `store_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_comment` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`store_id`,`user_id`),
  KEY `fk_Store_has_User_User1_idx` (`user_id`),
  KEY `fk_Store_has_User_Store1_idx` (`store_id`),
  CONSTRAINT `fk_Store_has_User_Store1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Store_has_User_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall`
--

DROP TABLE IF EXISTS `mall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall` (
  `mall_id` int(11) NOT NULL AUTO_INCREMENT,
  `mall_name` varchar(50) NOT NULL,
  `mall_location` varchar(50) NOT NULL,
  PRIMARY KEY (`mall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall`
--

LOCK TABLES `mall` WRITE;
/*!40000 ALTER TABLE `mall` DISABLE KEYS */;
INSERT INTO `mall` VALUES (1,'AEON','Tan Phu');
/*!40000 ALTER TABLE `mall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_has_store`
--

DROP TABLE IF EXISTS `mall_has_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_has_store` (
  `mall_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`mall_id`,`store_id`),
  KEY `fk_Mall_has_Store_Store1_idx` (`store_id`),
  KEY `fk_Mall_has_Store_Mall1_idx` (`mall_id`),
  CONSTRAINT `fk_Mall_has_Store_Mall1` FOREIGN KEY (`mall_id`) REFERENCES `mall` (`mall_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mall_has_Store_Store1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_has_store`
--

LOCK TABLES `mall_has_store` WRITE;
/*!40000 ALTER TABLE `mall_has_store` DISABLE KEYS */;
INSERT INTO `mall_has_store` VALUES (1,1);
/*!40000 ALTER TABLE `mall_has_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(250) NOT NULL,
  `store_address` varchar(255) NOT NULL,
  `store_email` varchar(250) NOT NULL,
  `store_phone` varchar(20) NOT NULL,
  `store_geolat` decimal(10,6) DEFAULT NULL,
  `store_geolng` decimal(10,6) DEFAULT NULL,
  `store_image` varchar(200) DEFAULT NULL,
  `store_code` varchar(10) DEFAULT NULL,
  `store_parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'ABC','Q5','abc@gmail.com','0939332766',NULL,NULL,NULL,NULL,NULL),(2,'ZARA','Vincom','zara@zara.com','01234334433',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_in_brand`
--

DROP TABLE IF EXISTS `store_in_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_in_brand` (
  `store_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  PRIMARY KEY (`store_id`,`brand_id`),
  KEY `fk_Store_has_Brand_Brand1_idx` (`brand_id`),
  KEY `fk_Store_has_Brand_Store1_idx` (`store_id`),
  CONSTRAINT `fk_Store_has_Brand_Brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Store_has_Brand_Store1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_in_brand`
--

LOCK TABLES `store_in_brand` WRITE;
/*!40000 ALTER TABLE `store_in_brand` DISABLE KEYS */;
INSERT INTO `store_in_brand` VALUES (1,1);
/*!40000 ALTER TABLE `store_in_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_location` varchar(50) DEFAULT NULL,
  `user_recent_search` varchar(50) DEFAULT NULL,
  `user_favorite` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nhut','123','123','123','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorite_malls`
--

DROP TABLE IF EXISTS `user_favorite_malls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favorite_malls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `mall_id` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_favorite_malls_user_idx` (`user_id`),
  KEY `fk_favorite_malls_mall_idx` (`mall_id`),
  CONSTRAINT `fk_favorite_malls_mall` FOREIGN KEY (`mall_id`) REFERENCES `mall` (`mall_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_favorite_malls_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorite_malls`
--

LOCK TABLES `user_favorite_malls` WRITE;
/*!40000 ALTER TABLE `user_favorite_malls` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favorite_malls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorite_stores`
--

DROP TABLE IF EXISTS `user_favorite_stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favorite_stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_favorite_stores_user_idx` (`user_id`),
  KEY `fk_user_favorite_stores_store_idx` (`store_id`),
  CONSTRAINT `fk_user_favorite_stores_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_user_favorite_stores_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorite_stores`
--

LOCK TABLES `user_favorite_stores` WRITE;
/*!40000 ALTER TABLE `user_favorite_stores` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favorite_stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_recent_search`
--

DROP TABLE IF EXISTS `user_recent_search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_recent_search` (
  `recent_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `search_string` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  PRIMARY KEY (`recent_id`),
  KEY `fk_user_recent_search_user_idx` (`user_id`),
  CONSTRAINT `fk_user_recent_search_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_recent_search`
--

LOCK TABLES `user_recent_search` WRITE;
/*!40000 ALTER TABLE `user_recent_search` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_recent_search` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-25 17:01:54
