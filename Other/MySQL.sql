/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.3.0 : Database - tododb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tododb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tododb`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `classname` varchar(30) NOT NULL,
  `color` varchar(10) NOT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert  into `category`(`categoryid`,`userid`,`classname`,`color`) values 
(24,13,'学习','#335cff'),
(26,13,'游戏','#ff33ee'),
(27,13,'测试','#FF5733');

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `taskid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `title` varchar(50) NOT NULL,
  `text` varchar(100) DEFAULT NULL,
  `tasktime` datetime NOT NULL,
  `categoryid` int NOT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `task` */

insert  into `task`(`taskid`,`userid`,`title`,`text`,`tasktime`,`categoryid`,`createdate`) values 
(18,13,'写作业','完成数学作业','2025-01-24 23:37:00',24,'2025-01-24 23:35:19'),
(19,13,'打游戏','完成游戏每日任务','2025-01-24 23:37:00',26,'2025-01-24 23:35:44'),
(20,13,'1111','11111','2025-01-24 23:39:00',27,'2025-01-24 23:36:11'),
(21,13,'2222','222','2025-01-25 23:38:00',27,'2025-01-24 23:36:46'),
(22,13,'2233','3333','2025-01-26 23:36:00',27,'2025-01-24 23:36:58'),
(24,13,'5555','65555','2025-01-24 23:38:00',27,'2025-01-24 23:37:51'),
(26,13,'7777','77777777','2025-01-02 23:38:00',27,'2025-01-24 23:38:18'),
(27,13,'1133','12123','2025-01-17 23:38:00',27,'2025-01-24 23:38:41'),
(28,13,'1122','123213','2025-01-09 23:38:00',27,'2025-01-24 23:38:53'),
(29,13,'1144','123141','2025-01-03 23:39:00',27,'2025-01-24 23:39:06');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `picid` int NOT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`email`,`password`,`picid`,`createdate`) values 
(13,'Static','2975395593@qq.com','123456a',1,'2025-01-24 23:12:25'),
(14,'用户2','123456@qq.com','123a',2,'2025-01-24 23:12:49'),
(16,'用户3','1234567@qq.com','123a',1,'2025-01-24 23:13:09'),
(17,'用户4','12345678@qq.com','123a',1,'2025-01-24 23:13:20'),
(18,'用户5','123456789@163.com','123a',2,'2025-01-24 23:13:31');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
