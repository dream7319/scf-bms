-- MySQL dump 10.13  Distrib 5.5.54, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: elastic
-- ------------------------------------------------------
-- Server version	5.5.54

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
-- Table structure for table `scf_menu`
--

DROP TABLE IF EXISTS `scf_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `menu_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_menu_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_menu`
--

LOCK TABLES `scf_menu` WRITE;
/*!40000 ALTER TABLE `scf_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_menu_resources`
--

DROP TABLE IF EXISTS `scf_menu_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_menu_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `resource_url` varchar(50) DEFAULT NULL,
  `resource_status` tinyint(1) DEFAULT '1',
  `menu_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_menu_resources_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_menu_resources`
--

LOCK TABLES `scf_menu_resources` WRITE;
/*!40000 ALTER TABLE `scf_menu_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_menu_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_role`
--

DROP TABLE IF EXISTS `scf_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_role_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_role`
--

LOCK TABLES `scf_role` WRITE;
/*!40000 ALTER TABLE `scf_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_role_menu`
--

DROP TABLE IF EXISTS `scf_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `menu_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_role_menu_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_role_menu`
--

LOCK TABLES `scf_role_menu` WRITE;
/*!40000 ALTER TABLE `scf_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_user`
--

DROP TABLE IF EXISTS `scf_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telphone` varchar(11) DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT '1',
  `birthday` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT '0',
  `user_type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_user`
--

LOCK TABLES `scf_user` WRITE;
/*!40000 ALTER TABLE `scf_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_user_role`
--

DROP TABLE IF EXISTS `scf_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `user_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_user_role_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_user_role`
--

LOCK TABLES `scf_user_role` WRITE;
/*!40000 ALTER TABLE `scf_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `scf_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-14 17:57:47
