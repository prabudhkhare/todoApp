/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.23 : Database - arm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`arm` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `arm`;

/*Table structure for table `t_user_details` */

DROP TABLE IF EXISTS `t_user_details`;

CREATE TABLE `t_user_details` (
  `email_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) NOT NULL,
  `record_status` char(1) DEFAULT 'C',
  `failed_login_attempts` int DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `last_password_changed` datetime(6) DEFAULT NULL,
  `password` varchar(5000) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_details` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
