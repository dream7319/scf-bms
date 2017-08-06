-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elastic
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `abstracts` varchar(500) DEFAULT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `post_time` date DEFAULT NULL,
  `click_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'第一次使用elasticsearch','现在elasticsearch版本为5.1.1','ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便','2017-06-25',1000);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

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
  `icon_class` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_menu_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_menu`
--

LOCK TABLES `scf_menu` WRITE;
/*!40000 ALTER TABLE `scf_menu` DISABLE KEYS */;
INSERT INTO `scf_menu` VALUES (1,'后台管理',NULL,1,0,'2017-08-05 08:59:26',NULL,1,'icon-user'),(2,'用户管理','/views/user/index.html',2,1,'2017-08-05 08:59:50',NULL,1,NULL),(3,'角色管理','/views/role/index.html',2,1,'2017-08-05 09:00:30',NULL,1,NULL),(4,'菜单管理','/views/menu/index.html',2,1,'2017-08-05 09:01:01',NULL,1,NULL),(5,'资源管理','/views/resource/index.html',2,1,'2017-08-05 09:01:26',NULL,1,NULL),(6,'产品管理',NULL,1,0,'2017-08-05 11:41:14',NULL,1,'fa fa-product-hunt'),(7,'产品管理','/views/product/index.html',2,6,'2017-08-05 11:42:15',NULL,1,NULL),(8,'贷款管理',NULL,1,0,'2017-08-05 11:42:44',NULL,1,'fa fa-jpy'),(9,'贷款列表(全部)','/views/loan/lists.html',2,8,'2017-08-05 11:44:47',NULL,1,NULL),(10,'贷款列表(个人)','/views/loan/lists.html',2,8,'2017-08-05 11:48:28','2017-08-05 11:49:19',1,NULL);
/*!40000 ALTER TABLE `scf_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scf_resource`
--

DROP TABLE IF EXISTS `scf_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scf_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `resource_name` varchar(50) DEFAULT NULL,
  `resource_url` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `resource_status` tinyint(1) DEFAULT '1',
  `menu_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scf_menu_resources_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_resource`
--

LOCK TABLES `scf_resource` WRITE;
/*!40000 ALTER TABLE `scf_resource` DISABLE KEYS */;
INSERT INTO `scf_resource` VALUES (1,2,'新增用户','/api/user/add','2017-08-05 11:24:20',1,1),(2,2,'编辑用户','/api/user/update','2017-08-05 11:25:57',1,1),(3,2,'删除用户','/api/user/delete/','2017-08-05 11:26:08',1,1),(4,2,'分配角色','/api/userRole/addOrUpdate','2017-08-05 11:27:45',1,1),(5,2,'禁用用户','/api/user/update','2017-08-05 11:27:49',1,1),(6,2,'启用用户','/api/user/update','2017-08-05 11:27:49',1,1),(7,2,'用户详情','/api/user','2017-08-05 11:27:49',1,1),(8,2,'用户列表','/api/user/list','2017-08-05 11:33:54',1,1),(9,3,'编辑角色','/api/role/update','2017-08-05 11:29:49',1,1),(10,3,'分配菜单','/api/roleMenu/addOrUpdate','2017-08-05 11:30:05',1,1),(11,3,'删除角色','/api/role/delete/','2017-08-05 11:30:19',1,1),(12,3,'禁用角色','/api/role/update','2017-08-05 11:30:40',1,1),(13,3,'启用角色','/api/role/update','2017-08-05 11:30:55',1,1),(14,3,'新增角色','/api/role/add','2017-08-05 11:32:10',1,1),(15,3,'角色列表','/api/role/list','2017-08-05 11:34:02',1,1),(16,4,'菜单列表','/api/menu/list','2017-08-05 11:35:16',1,1),(17,4,'新增菜单','/api/menu/add','2017-08-05 11:35:18',1,1),(18,4,'编辑菜单','/api/menu/update','2017-08-05 11:35:49',1,1),(19,4,'删除菜单','/api/menu/delete/','2017-08-05 11:36:46',1,1),(20,4,'禁用菜单','/api/menu/update','2017-08-05 11:36:48',1,1),(21,4,'启用菜单','/api/menu/update','2017-08-05 11:36:50',1,1),(22,5,'资源列表','/api/resource/list','2017-08-05 11:38:20',1,1),(23,5,'新增资源','/api/resource/add','2017-08-05 11:38:21',1,1),(24,5,'编辑资源','/api/resource/update','2017-08-05 11:38:43',1,1),(25,5,'删除资源','/api/resource/delete/','2017-08-05 11:39:13',1,1),(26,5,'启用资源','/api/resource/update','2017-08-05 11:39:33',1,1),(27,5,'禁用资源','/api/resource/update','2017-08-05 11:39:45',1,1),(28,2,'加载用户角色列表','/api/role/user/','2017-08-06 18:11:22',1,1),(29,3,'角色详情','/api/role','2017-08-06 18:19:19',1,1),(30,4,'菜单详情','/api/menu','2017-08-06 18:22:15',1,1),(31,5,'资源详情','/api/resource','2017-08-06 18:24:41',1,1),(32,3,'加载角色菜单列表','/api/roleMenu/addOrUpdate','2017-08-06 18:26:39',1,1);
/*!40000 ALTER TABLE `scf_resource` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_role`
--

LOCK TABLES `scf_role` WRITE;
/*!40000 ALTER TABLE `scf_role` DISABLE KEYS */;
INSERT INTO `scf_role` VALUES (1,'超级管理员','2017-08-02 20:38:48','2017-08-05 11:00:51',1),(2,'一般管理员','2017-08-05 08:56:44',NULL,1),(3,'测试管理员','2017-08-05 08:58:09',NULL,1),(4,'产品管理员','2017-08-05 08:58:18',NULL,1),(5,'运营管理员','2017-08-05 08:58:30',NULL,1),(6,'运维管理员','2017-08-05 08:58:37',NULL,1),(7,'风控管理员','2017-08-06 17:58:23',NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_role_menu`
--

LOCK TABLES `scf_role_menu` WRITE;
/*!40000 ALTER TABLE `scf_role_menu` DISABLE KEYS */;
INSERT INTO `scf_role_menu` VALUES (20,1,1,1,1),(21,1,2,1,1),(22,1,3,1,1),(23,1,4,1,1),(24,1,5,1,1),(25,1,6,1,1),(26,1,7,1,1),(27,1,8,1,1),(28,1,9,1,1),(29,1,10,1,1);
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
  `user_type` varchar(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_user`
--

LOCK TABLES `scf_user` WRITE;
/*!40000 ALTER TABLE `scf_user` DISABLE KEYS */;
INSERT INTO `scf_user` VALUES (23,'miao',NULL,'苗','HCwY84byfArAq63hFhrFQkW/glOPQt/EAuGYutWnW/A=','FEMALE','miao@163.com','18839907323',NULL,1,'1992-01-30','2017-07-30 20:32:43',NULL,0,'0'),(24,'lierl',NULL,'垒','0RKLHD9q3efKGDYac3d6zExnrfzDPSjOvxvXCQx81aw=','MALE','lei@163.com','17610787323',NULL,1,'1989-06-30','2017-07-30 20:35:33',NULL,0,'0'),(25,'zhangsan',NULL,'三','tsHc4T5/Ll7Qv3d+y+76XUL4C/ew/zDx6FeZ3CnMrf0=','MALE','san@163.com','18639710226',NULL,1,'2017-06-25','2017-07-30 20:38:06',NULL,0,'0'),(26,'a',NULL,'a','tMsTbeP+RJGnItbzem/67Xg5vsq4YiTN8rYmLzbnYyE=','MALE','aa@163.com','18639710226',NULL,1,'2017-06-27','2017-07-30 21:03:47',NULL,0,'0'),(27,'b',NULL,'b','ut8GNgwz2TZO/lYk4bjzWj0U/wUSRYPhYNhjPu67zMM=','MALE','aa@163.com','18639710226',NULL,1,'2017-06-28','2017-07-30 21:03:58',NULL,0,'0'),(28,'c',NULL,'c','JX8ct8l3yDqo8z6TrT816Jjwfv5Kr2lbqgNMDwSJSR0=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-02','2017-07-30 21:04:08',NULL,0,'0'),(29,'d',NULL,'d','wAvKyACdEH0h2bkBJ62xUAjtCqT9tgo53mVbnANsklM=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-04','2017-07-30 21:04:19','2017-08-05 20:38:12',0,'0'),(30,'e',NULL,'e','82nvsxfLpWMpDCIFUEMvjdcWPKCbRx7wtRAp/vbGumI=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-06','2017-07-30 21:04:32',NULL,0,'0'),(31,'f',NULL,'f','u/JDxyw8at9mEaPAvfCEhfj9fonCbgSjlyJfaRyzLRQ=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-08','2017-07-30 21:04:42',NULL,0,'0'),(32,'g',NULL,'g','zlF2VN2NqiXfylOLgYNmBReCKYPIiw/6L4MML/hr4c0=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-10','2017-07-30 21:04:58',NULL,0,'0'),(33,'h',NULL,'h','EfwzqORoyd+hV2LaWNZKyJWwccaD9fg0Bh9PNsgN80Y=','MALE','aa@163.com','18639710226',NULL,1,'2017-07-12','2017-07-30 21:05:09',NULL,0,'0'),(34,'test',NULL,'test','1qG00yH6BfuF6h2QmmB8sEm0/LeHTQNdVqeC5963jUw=','MALE','test@qq.com','18839907323',NULL,1,'2017-08-01','2017-08-01 20:34:36',NULL,0,'0'),(35,'test',NULL,'test','1qG00yH6BfuF6h2QmmB8sEm0/LeHTQNdVqeC5963jUw=','MALE','aa@163.com','17610787323',NULL,0,'2017-07-31','2017-08-01 20:35:06',NULL,0,'0'),(36,'admin',NULL,'admin','LXdoqEFejebuetU/wuKFXe8ArPKpgJyMscyFSEZCnd8=','MALE','admin@163.com','18839907323',NULL,1,'2017-08-05','2017-08-05 15:38:47','2017-08-05 15:38:55',0,'1');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scf_user_role`
--

LOCK TABLES `scf_user_role` WRITE;
/*!40000 ALTER TABLE `scf_user_role` DISABLE KEYS */;
INSERT INTO `scf_user_role` VALUES (5,23,1,1,1);
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

-- Dump completed on 2017-08-06 19:14:45
