-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: wangzijian.top    Database: pose-wcmp-rel
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `algorithm_configuration`
--

DROP TABLE IF EXISTS `algorithm_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm_configuration` (
                                           `link_code` varchar(255) NOT NULL COMMENT '小程序ID',
                                           `algorithm_id` int NOT NULL COMMENT '算法唯一ID',
                                           `name` varchar(50) NOT NULL COMMENT '算法名称',
                                           `sport_category` varchar(255) DEFAULT NULL COMMENT '体育种类',
                                           `enable` tinyint(1) DEFAULT NULL COMMENT '是否可用1：启用 0：禁用',
                                           `content` json DEFAULT NULL COMMENT '模板',
                                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                           `url` varchar(255) DEFAULT NULL COMMENT '算法调用地址',
                                           PRIMARY KEY (`link_code`,`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构算法同步表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm_configuration`
--

INSERT INTO `algorithm_configuration` VALUES ('2180bb43-4a86-463e-8a64-86af145af27e',1,'足球射门姿势算法','足球',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('2180bb43-4a86-463e-8a64-86af145af27e',3,'游泳姿势优化算法','游泳',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('3180bb43-4a86-463e-8a64-86af145af245',1,'足球射门姿势算法','足球',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('3180bb43-4a86-463e-8a64-86af145af245',3,'游泳姿势优化算法','游泳',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('4180bb43-4a86-463e-8a64-86af145af2op',1,'足球射门姿势算法','足球',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('4180bb43-4a86-463e-8a64-86af145af2op',14,'足球专用犯规识别算法','足球',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('4180bb43-4a86-463e-8a64-86af145af2op',16,'乒乓球角度识别算法','乒乓球',0,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score1'),('po80bb43-4a86-463e-8a64-86af145af212',1,'足球射门姿势算法','足球',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',14,'足球专用犯规识别算法','足球',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',15,'篮球手部识别算法','篮球',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',16,'乒乓球角度识别算法','乒乓球',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score1'),('po80bb43-4a86-463e-8a64-86af145af212',20,'跳远距离识别','跳远',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',21,'标枪弧度算法','标枪',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',22,'标枪算法','标枪',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score'),('po80bb43-4a86-463e-8a64-86af145af212',24,'高尔夫球道算法','高尔夫',1,'{\"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}','2023-09-25 09:46:40','https://wangzijian.top/score');

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `link_code` varchar(36) DEFAULT NULL COMMENT '小程序链接码',
                          `name` varchar(50) NOT NULL COMMENT '课程名称',
                          `type` int DEFAULT NULL COMMENT '0:文字 1：图片 2： 视频',
                          `cover_img` varchar(255) DEFAULT NULL COMMENT '课程封面',
                          `media_file` varchar(255) DEFAULT NULL COMMENT '媒体uuid',
                          `description` varchar(500) DEFAULT NULL COMMENT '课程描述',
                          `create_time` datetime NOT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

INSERT INTO `course` VALUES (1,'po80bb43-4a86-463e-8a64-86af145af212','乒乓球训练',0,'5197fa35-1a9a-45de-9155-133d5e28b912.png','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程1','2023-09-07 21:50:36','2023-09-07 21:50:36'),(2,'3180bb43-4a86-463e-8a64-86af145af245','游泳技巧',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','333','2023-09-07 22:03:35','2023-09-07 22:03:35'),(3,'2180bb43-4a86-463e-8a64-86af145af27e','篮球战术',0,'06085b63-5878-4a08-b054-59bbde260010.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','2222','2023-09-08 17:03:20','2023-09-08 17:03:20'),(8,'2180bb43-4a86-463e-8a64-86af145af27e','足球训练',0,'06085b63-5878-4a08-b054-59bbde260011.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','12333','2023-09-08 17:02:14','2023-09-08 17:02:14'),(9,'po80bb43-4a86-463e-8a64-86af145af212','羽毛球训练',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程9','2023-09-08 17:02:14','2023-09-08 17:02:14'),(10,'po80bb43-4a86-463e-8a64-86af145af212','游泳技巧',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程10','2023-09-08 17:02:14','2023-09-08 17:02:14'),(11,'po80bb43-4a86-463e-8a64-86af145af212','篮球战术',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程11','2023-09-08 17:02:14','2023-09-08 17:02:14'),(12,'po80bb43-4a86-463e-8a64-86af145af212','足球训练',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程12','2023-09-08 17:02:14','2023-09-08 17:02:14'),(13,'po80bb43-4a86-463e-8a64-86af145af212','网球基础',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程13','2023-09-08 17:02:14','2023-09-08 17:02:14'),(14,'po80bb43-4a86-463e-8a64-86af145af212','健身操课程',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程14','2023-09-08 17:02:14','2023-09-08 17:02:14'),(15,'po80bb43-4a86-463e-8a64-86af145af212','瑜伽练习',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程15','2023-09-08 17:02:14','2023-09-08 17:02:14'),(16,'po80bb43-4a86-463e-8a64-86af145af212','棒球技巧',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程16','2023-09-08 17:02:14','2023-09-08 17:02:14'),(17,'po80bb43-4a86-463e-8a64-86af145af212','排球战术',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程17','2023-09-08 17:02:14','2023-09-08 17:02:14'),(18,'po80bb43-4a86-463e-8a64-86af145af212','滑板运动',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程18','2023-09-08 17:02:14','2023-09-08 17:02:14'),(19,'po80bb43-4a86-463e-8a64-86af145af212','游泳训练营',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程19','2023-09-08 17:02:14','2023-09-08 17:02:14'),(20,'po80bb43-4a86-463e-8a64-86af145af212','篮球基本功',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程20','2023-09-08 17:02:14','2023-09-08 17:02:14'),(21,'po80bb43-4a86-463e-8a64-86af145af212','田径技巧培训',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程21','2023-09-08 17:02:14','2023-09-08 17:02:14'),(22,'po80bb43-4a86-463e-8a64-86af145af212','网球战术训练',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程22','2023-09-08 17:02:14','2023-09-08 17:02:14'),(23,'po80bb43-4a86-463e-8a64-86af145af212','瑜伽冥想课程',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程23','2023-09-08 17:02:14','2023-09-08 17:02:14'),(24,'po80bb43-4a86-463e-8a64-86af145af212','健身有氧操',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程24','2023-09-08 17:02:14','2023-09-08 17:02:14'),(25,'po80bb43-4a86-463e-8a64-86af145af212','棒垒球训练班',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程25','2023-09-08 17:02:14','2023-09-08 17:02:14'),(26,'po80bb43-4a86-463e-8a64-86af145af212','足球进攻战术',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程26','2023-09-08 17:02:14','2023-09-08 17:02:14'),(27,'po80bb43-4a86-463e-8a64-86af145af212','排球拦网技巧',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程27','2023-09-08 17:02:14','2023-09-08 17:02:14'),(28,'po80bb43-4a86-463e-8a64-86af145af212','自行车骑行探险',0,'06085b63-5878-4a08-b054-59bbde2600d9.jpg','06085b63-5878-4a08-b054-59bbde2600d9.jpg','课程28','2023-09-08 17:02:14','2023-09-08 17:02:14');

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) NOT NULL COMMENT '考试名称',
                        `link_code` varchar(36) DEFAULT NULL COMMENT '小程序链接码',
                        `description` varchar(500) DEFAULT NULL COMMENT '考试描述',
                        `start_time` datetime NOT NULL COMMENT '开始时间',
                        `end_time` datetime NOT NULL COMMENT '结束时间',
                        `file` varchar(255) DEFAULT NULL COMMENT '资源ID',
                        `exam_count` int DEFAULT NULL COMMENT '考试次数',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` VALUES (4,'123','po80bb43-4a86-463e-8a64-86af145af212','123','2023-09-07 21:58:30','2023-09-07 21:58:30','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',2,'2023-09-07 21:58:30'),(5,'123','2180bb43-4a86-463e-8a64-86af145af27e','123','2023-09-07 21:58:30','2023-09-07 21:58:30','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',2,'2023-09-07 21:58:30'),(6,'期末考试','2180bb43-4a86-463e-8a64-86af145af27e','描述','2023-09-08 16:19:47','2023-09-08 16:19:47','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',2,'2023-09-08 16:19:47'),(9,'测试考试','po80bb43-4a86-463e-8a64-86af145af212','这是一个考试','2023-09-13 21:33:47','2023-09-13 21:33:47','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',3,'2023-09-13 21:33:47'),(10,'王子健-考试','po80bb43-4a86-463e-8a64-86af145af212','王子健考试描述','2023-09-13 21:47:15','2023-09-13 21:47:15','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',5,'2023-09-13 21:47:15'),(11,'田径考试','po80bb43-4a86-463e-8a64-86af145af212','这是田径考试的描述','2023-09-18 16:20:50','2023-09-18 16:20:50','ab631499-1b49-4f2a-b088-5410a3877431.xlsx',3,'2023-09-18 16:20:50');

--
-- Table structure for table `exam_record`
--

DROP TABLE IF EXISTS `exam_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_record` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '考试记录ID',
                               `link_code` varchar(255) DEFAULT NULL COMMENT '小程序链接码',
                               `user_id` int DEFAULT NULL COMMENT '用户ID',
                               `exam_id` int DEFAULT NULL COMMENT '考试ID',
                               `count` int DEFAULT NULL COMMENT '考试次数',
                               `score` int DEFAULT NULL COMMENT '分数',
                               `time` datetime DEFAULT NULL COMMENT '考试时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_record`
--

INSERT INTO `exam_record` VALUES (3,'po80bb43-4a86-463e-8a64-86af145af212',2,1,4,999,'2023-09-13 15:31:35'),(6,'po80bb43-4a86-463e-8a64-86af145af212',3,10,2,67,'2023-09-13 21:48:53'),(12,'po80bb43-4a86-463e-8a64-86af145af212',1,11,3,56,'2023-09-18 16:37:17'),(13,'po80bb43-4a86-463e-8a64-86af145af212',4,NULL,1,40,NULL),(14,'po80bb43-4a86-463e-8a64-86af145af212',4,0,1,40,NULL),(15,'po80bb43-4a86-463e-8a64-86af145af212',4,4,2,100,NULL),(16,'po80bb43-4a86-463e-8a64-86af145af212',4,9,1,80,NULL),(17,'po80bb43-4a86-463e-8a64-86af145af212',5,4,2,40,NULL);

--
-- Table structure for table `link_code_and_appid_map`
--

DROP TABLE IF EXISTS `link_code_and_appid_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_code_and_appid_map` (
                                           `link_code` varchar(36) DEFAULT NULL COMMENT '链接代码',
                                           `app_id` varchar(255) DEFAULT NULL COMMENT '小程序ID',
                                           `app_secret` varchar(255) DEFAULT NULL COMMENT '微信小程序秘钥',
                                           `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='链接码和应用Id的映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_code_and_appid_map`
--

INSERT INTO `link_code_and_appid_map` VALUES ('po80bb43-4a86-463e-8a64-86af145af212','wxc62afc144417346e','2387d60cc0a34703ae8ec0acedd2a635','2023-09-25 09:46:40'),('4180bb43-4a86-463e-8a64-86af145af2op','wxc62afc1444173555','b5a7ea8dcee900d1f1663076ac8dba3b','2023-09-25 09:46:40'),('8180bb43-4a86-463e-8a64-86af145af2gf','wxc62afc1444173999','b5a7ea8dcee900d1f1663076ac8dba3b','2023-09-25 09:46:40'),('3180bb43-4a86-463e-8a64-86af145af245','wxc62afc1444173888','b5a7ea8dcee900d1f1663076ac8dba3b','2023-09-25 09:46:40'),('2180bb43-4a86-463e-8a64-86af145af27e','wxc62afc1444173776','1111111111111111111111111111','2023-09-25 09:46:40'),('c843f0b1-38b6-4da6-b340-15814ed63aea','12345','123456','2023-09-25 09:46:40');

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `full_name` varchar(255) DEFAULT NULL,
                             `alias` varchar(255) DEFAULT NULL,
                             `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
                             `size` bigint DEFAULT NULL,
                             `create_time` datetime DEFAULT NULL,
                             `update_time` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` VALUES (113,'Snipaste_2023-09-04_10-22-46.jpg','74b310cb-22e0-4137-8901-162b1c14bbb7','jpg',4654,'2023-09-04 13:56:20','2023-09-04 13:56:20'),(114,'布布比?.jpg','5531030d-e568-4446-bc95-9d3a589450f0','jpg',76958,'2023-09-04 13:59:09','2023-09-04 13:59:09'),(115,'Snipaste_2023-09-04_10-22-46.jpg','b14c3c50-238f-47ff-91c9-18af9c385d5e','jpg',4654,'2023-09-04 14:09:00','2023-09-04 14:09:00'),(116,'demo视频.mp4','e7fc2dd4-0871-4f39-800f-b608ad9759d6','mp4',2501450,'2023-09-04 14:58:36','2023-09-04 14:58:36'),(117,'考试模板.xlsx','ed23e489-ddc3-4047-a9c9-acfba7feae93','xlsx',10761,'2023-09-08 14:35:18','2023-09-08 14:35:18'),(118,'考试模板.xlsx','9d36db1a-4fea-4284-98c1-b014b66cd3eb','xlsx',10769,'2023-09-13 14:11:44','2023-09-13 14:11:44'),(119,'8ed34f5fgy1h83rag4bbnj227t27tx6p.jpg','88075ad3-4ad1-475a-aee9-a49b2d80b592','jpg',2027475,'2023-09-13 20:18:03','2023-09-13 20:18:03'),(120,'8ed34f5fgy1h83rag4bbnj227t27tx6p.jpg','3a6c9dbc-b1f7-4707-8fff-bb8bd089c161','jpg',2027475,'2023-09-13 20:19:54','2023-09-13 20:19:54'),(121,'考试模板.xlsx','ab631499-1b49-4f2a-b088-5410a3877431','xlsx',10740,'2023-09-13 21:33:19','2023-09-13 21:33:19'),(122,'考试模板.xlsx','9391bfea-dca6-485a-8323-e91e28ed3401','xlsx',10740,'2023-09-13 21:46:56','2023-09-13 21:46:56'),(123,'考试模板.xlsx','45112f20-d43f-4fbd-8267-0eed87ff0fcf','xlsx',10769,'2023-09-18 15:17:20','2023-09-18 15:17:20'),(124,'考试模板.xlsx','816e33f7-797a-4854-9ab4-74c6eb5b1f63','xlsx',10769,'2023-09-18 16:20:25','2023-09-18 16:20:25'),(125,'QihW8VrnLH8nc24b5363b61d9515e20c2937defc82f1.png','e4f4ae15-63aa-4745-bfbf-a27876dae157','png',460,'2023-09-18 20:11:38','2023-09-18 20:11:38'),(126,'BRfL60ZqkxWObc0f65f786bd553295b7b86cd7a801bc.png','7dcdfbfd-3053-4f66-9f1b-8f847f9e86aa','png',459,'2023-09-18 20:12:30','2023-09-18 20:12:30'),(127,'XAHUaMvzF2rXbc0f65f786bd553295b7b86cd7a801bc.png','fcc78145-4b8f-42d6-8909-2f5e8a977c9b','png',459,'2023-09-18 20:12:55','2023-09-18 20:12:55'),(128,'cY51tVFVKsZHbc0f65f786bd553295b7b86cd7a801bc.png','74a92251-2306-499c-a8b7-10d9b4997d32','png',459,'2023-09-18 20:13:19','2023-09-18 20:13:19'),(129,'MgFbpmj9gFs2bc0f65f786bd553295b7b86cd7a801bc.png','c3041d51-36f3-495f-a859-3787ad301742','png',459,'2023-09-18 20:13:51','2023-09-18 20:13:51'),(130,'EOKC39MxCy7Ubc0f65f786bd553295b7b86cd7a801bc.png','b038ed6a-8874-47b5-bf77-e5738cca953e','png',459,'2023-09-18 22:37:26','2023-09-18 22:37:26'),(131,'Q6jRXcP8mN1obc0f65f786bd553295b7b86cd7a801bc.png','7c50bf8f-090d-444a-a94c-7d2dc01ff178','png',459,'2023-09-18 22:39:30','2023-09-18 22:39:30'),(132,'td0z3pmQCuUtbc0f65f786bd553295b7b86cd7a801bc.png','f88cb9fa-d683-4ab6-91a0-3c05082f8235','png',459,'2023-09-18 22:39:46','2023-09-18 22:39:46'),(133,'8eWnhQ18m852bc0f65f786bd553295b7b86cd7a801bc.png','ac6b69a7-b15b-44f5-89db-36870a5c60fc','png',459,'2023-09-18 22:40:59','2023-09-18 22:40:59'),(134,'HMwnmyHAonnvbc0f65f786bd553295b7b86cd7a801bc.png','39cee8d1-ac71-4fe7-bfab-dcfc64b718b3','png',459,'2023-09-18 22:42:24','2023-09-18 22:42:24'),(135,'l6bEd7w0PZKBbc0f65f786bd553295b7b86cd7a801bc.png','17dc9f5e-de50-4f86-a9b7-09fdbcd9eff9','png',459,'2023-09-18 22:45:21','2023-09-18 22:45:21'),(136,'qzYXXoWKfmnMbc0f65f786bd553295b7b86cd7a801bc.png','67d234e5-3415-49e1-872a-eb97fc12fe6e','png',459,'2023-09-18 22:47:10','2023-09-18 22:47:10'),(137,'RCsY3qc2f7JSa2ca354ab878eb7311c68e89c211ad08.png','befa12a8-c281-4666-bdcb-30ba6db9176c','png',1163,'2023-09-18 22:52:24','2023-09-18 22:52:24'),(138,'GCE1w9fOmozh460c3613829026960dc0c1959c7f8e1d.png','cd6a99aa-c0b9-485b-8c39-2ffd47635b8b','png',3340,'2023-09-18 22:53:24','2023-09-18 22:53:24'),(139,'ZOvVmLIhqwP6460c3613829026960dc0c1959c7f8e1d.png','3997dbfa-73fe-4583-9b0b-56a687bb9fd9','png',3340,'2023-09-18 22:57:15','2023-09-18 22:57:15'),(140,'考试模板.xlsx','a33209db-b38b-4814-a647-b34d6df782da','xlsx',10769,'2023-09-19 09:30:43','2023-09-19 09:30:43'),(141,'布布.jpg','06085b63-5878-4a08-b054-59bbde2600d9','jpg',76958,'2023-09-19 09:31:11','2023-09-19 09:31:11'),(142,'考试模板.xlsx','d823190b-a38b-4970-ba2d-55085d9674ad','xlsx',10769,'2023-09-19 09:32:31','2023-09-19 09:32:31'),(143,'考试模板.xlsx','780421d2-ad99-412d-b086-354d5827c256','xlsx',10769,'2023-09-19 09:42:21','2023-09-19 09:42:21'),(144,'3e8f14c3-aabc-4077-a550-42fca2fd2c64.jpg','07c59cf8-a7f6-4717-952f-72f90a8164f8','jpg',76958,'2023-09-21 21:59:43','2023-09-21 21:59:43'),(145,'wx_camera_1677498869780.mp4','d563d59d-c295-41a3-a6d9-c04a73030011','mp4',3514436,'2023-09-21 23:27:34','2023-09-21 23:27:34'),(146,'qq9monnSILD129f20c86590b172fdc26b00b5b581a9c.png','5197fa35-1a9a-45de-9155-133d5e28b912','png',20164,'2023-09-23 19:55:00','2023-09-23 19:55:00'),(147,'pGvGcpCG2LZj29f20c86590b172fdc26b00b5b581a9c.png','821a6b98-50fd-4e68-93d9-adf1117ef292','png',20164,'2023-09-23 21:02:23','2023-09-23 21:02:23'),(148,'vpm4KPGaPaHe29f20c86590b172fdc26b00b5b581a9c.png','77b55ed3-cefb-4817-a743-b613aded960c','png',20164,'2023-09-23 21:02:59','2023-09-23 21:02:59'),(149,'XfxzEyYc0y2P29f20c86590b172fdc26b00b5b581a9c.png','ec8516ed-aa4f-4501-bab8-69098d1a2c1b','png',20164,'2023-09-23 21:03:59','2023-09-23 21:03:59'),(150,'h62YWxV5NGFR29f20c86590b172fdc26b00b5b581a9c.png','c035f499-5987-4753-bae5-6782e799b9b3','png',20164,'2023-09-23 21:04:32','2023-09-23 21:04:32'),(151,'OukBNUCZ58qC29f20c86590b172fdc26b00b5b581a9c.png','0b441d32-0d19-4007-a859-3593239b01f4','png',20164,'2023-09-23 21:12:55','2023-09-23 21:12:55'),(152,'rijk1rQshd5D29f20c86590b172fdc26b00b5b581a9c.png','e8e8fec0-1837-4497-ab54-794a05a15759','png',20164,'2023-09-23 21:14:18','2023-09-23 21:14:18'),(153,'BpYSY4mayfXv3781a221044cad48d73151e5ecad79fb.mp4','5797ca1d-28ae-4c97-abdd-0ee6a60259e7','mp4',1059528,'2023-09-25 00:52:05','2023-09-25 00:52:05'),(154,'faDwlpNgmXwr3781a221044cad48d73151e5ecad79fb.mp4','96383a7f-58bb-433e-8d7e-515180573233','mp4',1059528,'2023-09-25 01:12:39','2023-09-25 01:12:39'),(155,'pEdFcAdxsfyY3781a221044cad48d73151e5ecad79fb.mp4','4eeca7f2-aa09-4b37-b99b-f28924b197db','mp4',1059528,'2023-09-25 01:36:40','2023-09-25 01:36:40');

--
-- Table structure for table `spot_history`
--

DROP TABLE IF EXISTS `spot_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spot_history` (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `link_code` varchar(36) DEFAULT NULL COMMENT '小程序链接码',
                                `user_id` int DEFAULT NULL COMMENT '用户ID',
                                `algorithm_id` int DEFAULT NULL COMMENT '算法ID',
                                `result` json DEFAULT NULL COMMENT '分析结果',
                                `file` varchar(255) DEFAULT NULL COMMENT '识别媒体文件',
                                `create_time` datetime DEFAULT NULL COMMENT '使用时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='识别历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot_history`
--

INSERT INTO `spot_history` VALUES (1,'po80bb43-4a86-463e-8a64-86af145af212',1,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','1','2023-09-13 17:51:45'),(2,'po80bb43-4a86-463e-8a64-86af145af212',1,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','1','2023-09-13 17:51:45'),(3,'po80bb43-4a86-463e-8a64-86af145af212',0,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','122','2023-09-13 22:05:33'),(4,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','139','2023-09-18 22:57:15'),(5,'po80bb43-4a86-463e-8a64-86af145af212',1,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','1234','2023-09-19 17:47:57'),(6,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','5197fa35-1a9a-45de-9155-133d5e28b912.png','2023-09-23 19:54:59'),(7,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','821a6b98-50fd-4e68-93d9-adf1117ef292.png','2023-09-23 21:02:22'),(8,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','77b55ed3-cefb-4817-a743-b613aded960c.png','2023-09-23 21:02:58'),(9,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','ec8516ed-aa4f-4501-bab8-69098d1a2c1b.png','2023-09-23 21:03:58'),(10,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','c035f499-5987-4753-bae5-6782e799b9b3.png','2023-09-23 21:04:31'),(11,'po80bb43-4a86-463e-8a64-86af145af212',4,1,'{\"score\": {\"球速评分\": 81, \"投篮时机评分\": 87, \"起跳姿势评分\": 89, \"手腕标准度评分\": 85, \"投篮命中率评分\": 82}, \"ability\": {\"动作准确\": 89, \"有氧能力\": 82, \"柔韧伸展\": 85, \"灵活敏捷\": 87, \"肌肉力量\": 81}}','e8e8fec0-1837-4497-ab54-794a05a15759.png','2023-09-23 21:14:17'),(12,'po80bb43-4a86-463e-8a64-86af145af212',5,1,'{\"score\": {\"球速评分\": 87, \"投篮时机评分\": 85, \"起跳姿势评分\": 90, \"手腕标准度评分\": 80, \"投篮命中率评分\": 89}, \"ability\": {\"动作准确\": 90, \"有氧能力\": 89, \"柔韧伸展\": 80, \"灵活敏捷\": 85, \"肌肉力量\": 87}}','5797ca1d-28ae-4c97-abdd-0ee6a60259e7.mp4','2023-09-25 00:52:04'),(13,'po80bb43-4a86-463e-8a64-86af145af212',5,21,'{\"score\": {\"球速评分\": 89, \"投篮时机评分\": 84, \"起跳姿势评分\": 87, \"手腕标准度评分\": 82, \"投篮命中率评分\": 81}, \"ability\": {\"动作准确\": 87, \"有氧能力\": 81, \"柔韧伸展\": 82, \"灵活敏捷\": 84, \"肌肉力量\": 89}}','96383a7f-58bb-433e-8d7e-515180573233.mp4','2023-09-25 01:12:38'),(14,'po80bb43-4a86-463e-8a64-86af145af212',5,24,'{\"score\": {\"球速评分\": 85, \"投篮时机评分\": 82, \"起跳姿势评分\": 86, \"手腕标准度评分\": 85, \"投篮命中率评分\": 80}, \"ability\": {\"动作准确\": 86, \"有氧能力\": 80, \"柔韧伸展\": 85, \"灵活敏捷\": 82, \"肌肉力量\": 85}}','4eeca7f2-aa09-4b37-b99b-f28924b197db.mp4','2023-09-25 01:36:39');

--
-- Table structure for table `study_data`
--

DROP TABLE IF EXISTS `study_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_data` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `user_id` int NOT NULL COMMENT '用户ID',
                              `course_id` int NOT NULL COMMENT '课程ID',
                              `duration` int NOT NULL DEFAULT '0' COMMENT '学习时长（秒）',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户学习数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_data`
--


--
-- Table structure for table `study_record`
--

DROP TABLE IF EXISTS `study_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_record` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `link_code` varchar(255) DEFAULT NULL COMMENT '小程序链接码',
                                `user_id` int NOT NULL COMMENT '用户ID',
                                `course_id` int NOT NULL COMMENT '课程ID',
                                `study_duration` int DEFAULT NULL COMMENT '学习时长',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户学习记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_record`
--

INSERT INTO `study_record` VALUES (1,'po80bb43-4a86-463e-8a64-86af145af212',1,1,4,'2023-09-03 10:13:13'),(2,'po80bb43-4a86-463e-8a64-86af145af212',1,2,5,'2023-09-13 10:13:13'),(3,'po80bb43-4a86-463e-8a64-86af145af212',1,3,6,'2023-09-12 10:13:13'),(4,'po80bb43-4a86-463e-8a64-86af145af212',1,7,6,'2023-09-18 10:13:13');

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                         `user_id` int DEFAULT NULL COMMENT '用户ID',
                         `token` varchar(255) DEFAULT NULL,
                         `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='token';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

INSERT INTO `token` VALUES (4,5,'6bf72cb5b1a4f58e01e5388173972d85','2023-09-26 00:50:19');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `link_code` varchar(36) DEFAULT NULL COMMENT '链接码',
                        `type` int DEFAULT NULL COMMENT '2:机构管理员 3: 学生',
                        `open_id` varchar(255) DEFAULT NULL COMMENT '微信小程序open_id',
                        `session_key` varchar(255) DEFAULT NULL COMMENT '微信小程序session_key',
                        `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                        `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        `update_time` datetime NOT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (5,'po80bb43-4a86-463e-8a64-86af145af212',3,'oFDqp5ebx3IpacqU36g0JOxMdSLI','YLvE0T8LMjy8fKVmJz8MwQ==','微信用户OtxLM',0,'2023-09-25 00:25:31','2023-09-25 00:25:31');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-25  9:46:50
