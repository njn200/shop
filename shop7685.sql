/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log : Database - shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop7685`;

/*Table structure for table `admins` */

DROP TABLE IF EXISTS `admins`;

CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(50) DEFAULT '1' COMMENT '1代表销售人员，0代表管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `admins` */

insert  into `admins`(`id`,`username`,`password`,`role`) values (2,'admin','tuShOfiBrA8+br7ENrMS8A==',0),(3,'jack','tuShOfiBrA8+br7ENrMS8A==',1);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `image1` varchar(255) DEFAULT NULL COMMENT '细节图片1',
  `image2` varchar(255) DEFAULT NULL COMMENT '细节图片2',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `intro` varchar(255) DEFAULT NULL COMMENT '介绍',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `type_id` int(11) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`cover`,`image1`,`image2`,`price`,`intro`,`stock`,`type_id`) values (1,'自动雨伞','/picture/1-1.jpg','/picture/1-1.jpg','/picture/1-1.jpg',56,'不透光伞面，有效阻隔光线，有效阻隔紫外线',5,5),(2,'储物箱','/picture/2-1.jpg','/picture/2-1.jpg','/picture/2-1.jpg',8,'透明密封手提收纳箱，可存放零食，文具',1,5),(3,'佩奇玩偶','/picture/3-1.jpg','/picture/3-1.jpg','/picture/3-1.jpg',30,'柔软的面料，环保PP棉填充，无异味',1,5),(4,'塑料水杯','/picture/4-1.jpg','/picture/4-1.jpg','/picture/4-1.jpg',11,'大容量解决您的日常生活，还有简约的刻度显示，能够清楚知道水的容量',0,5),(5,'全棉床单','/picture/5-1.jpg','/picture/5-1.jpg','/picture/5-1.jpg',80,'本产品是纯棉三件套，包括一个被套一个床单一个枕套，适合学生宿舍床',0,5),(6,'保温盒','/picture/6-1.jpg','/picture/6-1.jpg','/picture/6-1.jpg',40,'对食品进行保温，让你的味蕾更具享受',0,5),(11,'充电宝','/picture/11-1.jpg','/picture/11-1.jpg','/picture/11-1.jpg',38,'更薄，续航更持久.',1,2),(12,'手机数据线','/picture/12-1.jpg','/picture/12-1.jpg','/picture/12-1.jpg',5,'强兼容设计，一条线解决大部分安卓智能手机充电问题.',0,2),(13,'迷你音响','/picture/13-1.jpg','/picture/13-1.jpg','/picture/13-1.jpg',36,'美妙环绕立体声，尽享娱乐影音，游戏，流行音乐.',1,2),(14,'吹风机','/picture/14-1.jpg','/picture/14-1.jpg','/picture/14-1.jpg',29,'快速升温，恒温不伤发.',1,2);

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL COMMENT '购买时价格',
  `amount` int(11) DEFAULT NULL COMMENT '购买数量',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `good_id` int(11) DEFAULT NULL COMMENT '物品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `items` */

insert  into `items`(`id`,`price`,`amount`,`order_id`,`good_id`) values (1,11,1,1,4),(2,30,1,2,3),(3,40,1,3,6),(4,5,1,4,12),(5,80,1,5,5),(6,8,1,5,2);

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `ID` int(32) NOT NULL AUTO_INCREMENT,
  `broswer` varchar(100) DEFAULT NULL,
  `logcontent` longtext,
  `loglevel` smallint(6) DEFAULT NULL,
  `ip` varchar(23) DEFAULT NULL,
  `operatetime` datetime DEFAULT NULL,
  `operatetype` smallint(6) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `roleid` int(32) DEFAULT NULL COMMENT '0代表管理员，1代表销售员，2代表客户',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `logs` */

insert  into `logs`(`ID`,`broswer`,`logcontent`,`loglevel`,`ip`,`operatetime`,`operatetype`,`userid`,`roleid`) values (4,'Firefox','销售人员: admin登录成功',1,'192.168.184.1','2020-06-02 09:46:17',1,'2',0),(5,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 09:47:56',1,'2',1),(6,'Firefox','管理员: admin退出成功',1,'192.168.184.1','2020-06-02 09:50:46',1,'2',1),(7,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 09:50:48',1,'2',1),(8,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 10:12:36',1,'2',1),(9,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 10:19:55',1,'2',1),(10,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 10:33:11',1,'2',1),(11,'Firefox','管理员: admin退出成功',1,'192.168.184.1','2020-06-02 10:33:19',1,'2',1),(12,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 10:38:50',1,'2',1),(13,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 10:48:01',1,'2',1),(14,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 11:41:02',1,'2',1),(15,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 11:41:27',1,'3',0),(16,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:44:18',5,'2',1),(17,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:44:27',5,'2',1),(18,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:44:32',5,'2',1),(19,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:47:37',5,'2',1),(20,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:47:41',5,'2',1),(21,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:47:45',5,'2',1),(22,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:51:22',5,'2',1),(23,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 11:51:44',5,'2',1),(24,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 11:59:45',1,'3',0),(25,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 12:01:46',1,'3',0),(26,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 15:29:15',1,'2',1),(27,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 15:30:06',1,'3',0),(28,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 15:44:04',1,'3',0),(29,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:04:41',1,'3',0),(30,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:05:48',1,'3',0),(31,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:07:45',1,'3',0),(32,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:08:10',1,'3',0),(33,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:09:57',1,'3',0),(34,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:22:46',1,'3',0),(35,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 16:49:29',1,'3',0),(36,'Firefox','销售人员: jack修改自己的密码',1,'192.168.184.1','2020-06-02 16:49:36',5,'3',1),(37,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 17:02:26',1,'3',0),(38,'Firefox','销售人员: jack修改自己的密码',1,'192.168.184.1','2020-06-02 17:02:30',5,'3',1),(39,'Firefox','销售人员: jack退出成功',1,'192.168.184.1','2020-06-02 17:02:42',1,'3',1),(40,'Firefox','管理员: admin登录成功',1,'192.168.184.1','2020-06-02 17:05:57',1,'2',1),(41,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 17:05:58',5,'2',1),(42,'Firefox','管理员: admin退出成功',1,'192.168.184.1','2020-06-02 17:07:54',1,'2',1),(43,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 17:08:01',1,'3',0),(44,'Firefox','管理员: admin修改自己的密码',1,'192.168.184.1','2020-06-02 17:08:12',5,'2',1),(45,'Firefox','管理员: admin退出成功',1,'192.168.184.1','2020-06-02 17:08:16',1,'2',1),(46,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 17:19:55',1,'3',0),(47,'Firefox','用户: user1登录成功',1,'192.168.184.1','2020-06-02 17:52:35',1,'3',2),(48,'Firefox','用户: user1下单成功',1,'192.168.184.1','2020-06-02 17:52:44',2,'3',2),(49,'Firefox','用户: user1订单[5]支付成功',1,'192.168.184.1','2020-06-02 17:52:52',2,'3',2),(50,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 17:53:32',1,'3',0),(51,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-02 17:54:33',1,'3',0),(52,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-03 10:40:22',1,'3',0),(53,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-03 10:46:22',1,'3',0),(54,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-03 10:46:48',1,'3',0),(55,'Firefox','销售人员: jack修改自己的密码',1,'192.168.184.1','2020-06-03 10:46:50',5,'3',1),(56,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-03 10:50:01',1,'3',0),(57,'Firefox','销售人员: jack登录成功',1,'192.168.184.1','2020-06-03 10:51:25',1,'3',0),(58,'Firefox','销售人员: jack退出成功',1,'192.168.184.1','2020-06-03 10:51:48',1,'3',1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '总价',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '商品总数',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单状态(1未付款/2已付款/3已发货/4已完成)',
  `paytype` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付方式 (1微信/2支付宝/3货到付款)',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `systime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `user_id` int(11) DEFAULT NULL COMMENT '下单用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `orders` */

insert  into `orders`(`id`,`total`,`amount`,`status`,`paytype`,`name`,`phone`,`address`,`systime`,`user_id`) values (4,5,1,4,1,'k','138','zxcvbn','2019-07-05 12:35:59',3),(5,8,1,2,1,'k','138','zxcvbn','2020-06-02 17:52:44',3);

/*Table structure for table `tops` */

DROP TABLE IF EXISTS `tops`;

CREATE TABLE `tops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '推荐类型(1条幅/2大图/3小图)',
  `good_id` int(11) DEFAULT NULL COMMENT '物品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='首页推荐商品';

/*Data for the table `tops` */

insert  into `tops`(`id`,`type`,`good_id`) values (14,2,12),(15,2,11),(22,3,5),(24,3,4),(25,2,6),(29,3,13),(30,3,12),(33,3,2),(34,3,1),(37,1,14),(38,2,14),(40,2,13),(41,2,5),(42,2,4),(43,2,3),(44,2,2),(45,2,1);

/*Table structure for table `types` */

DROP TABLE IF EXISTS `types`;

CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `types` */

insert  into `types`(`id`,`name`) values (2,'数码系列'),(4,'课本系列'),(5,'生活系列');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`name`,`phone`,`address`) values (3,'user1','HAMVRZRssPCADKqGjGWJtQ==','k','138','zxcvbn'),(4,'user2','HAMVRZRssPCADKqGjGWJtQ==','sfrasg','rggreg','gfarrg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
