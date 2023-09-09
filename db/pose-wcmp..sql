-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pose-wcmp
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE=''+00:00'' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=''NO_AUTO_VALUE_ON_ZERO'' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `algorithm_configuration`
--

DROP TABLE IF EXISTS `algorithm_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm_configuration`
(
    `app_id`         varchar(255) NOT NULL COMMENT '' 小程序ID '',
    `algorithm_id`   int          NOT NULL COMMENT '' 算法唯一ID '',
    `name`           varchar(50)  NOT NULL COMMENT '' 算法名称 '',
    `sport_category` varchar(255) DEFAULT NULL COMMENT '' 体育种类 '',
    `enable`         tinyint(1) DEFAULT NULL COMMENT '' 是否可用1：启用 0：禁用 '',
    `content`        json         DEFAULT NULL COMMENT '' 模板 '',
    `update_time`    datetime     DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`app_id`, `algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''机构算法同步表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm_configuration`
--

INSERT INTO `algorithm_configuration`
VALUES (''wxc62afc1444173555'', 1, ''足球射门姿势算法'', ''田径'', 0, ''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173555'',14,''足球专用犯规识别算法'',''篮球'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173555'',16,''乒乓球角度识别算法'',''乒乓球'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173666'',1,''足球射门姿势算法'',''田径'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173777'',1,''足球射门姿势算法'',''田径'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173777'',3,''游泳姿势优化算法'',''游泳'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173888'',1,''足球射门姿势算法'',''田径'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00''),(''wxc62afc1444173888'',3,''游泳姿势优化算法'',''游泳'',0,''{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}'',''2023-09-09 08:31:00'');

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `link_code`   varchar(36)  DEFAULT NULL COMMENT '' 小程序链接码 '',
    `name`        varchar(50) NOT NULL COMMENT '' 课程名称 '',
    `type`        int          DEFAULT NULL COMMENT '' 0:文字 1：图片 2： 视频 '',
    `cover_uuid`  varchar(36)  DEFAULT NULL COMMENT '' 课程封面 '',
    `media_uuid`  varchar(36)  DEFAULT NULL COMMENT '' 媒体uuid '',
    `uuid`        varchar(255) DEFAULT NULL COMMENT '' uuid '',
    `description` varchar(500) DEFAULT NULL COMMENT '' 课程描述 '',
    `create_time` datetime    NOT NULL COMMENT '' 创建时间 '',
    `update_time` datetime     DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''学习表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

INSERT INTO `course`
VALUES (1, ''2180bb43-4a86-463e-8a64-86af145af27e'', ''课程1'', 0, ''1111 '', ''222 '', ''3333 '', ''课程1'',
        ''2023 - 09 - 07 21:50:36'', ''2023 - 09 - 07 21:50:36''),
       (2, ''3180bb43-4a86-463e-8a64-86af145af245'', ''555 '', 0, ''222 '', ''444 '', ''6666 '', ''333 '',
        ''2023 - 09 - 07 22:03:35'', ''2023 - 09 - 07 22:03:35''),
       (3, ''2180bb43-4a86-463e-8a64-86af145af27e'', ''22222 '', 0, ''2222 '', ''2222 '', ''2222 '', ''2222 '',
        ''2023 - 09 - 08 17:03:20'', ''2023 - 09 - 08 17:03:20''),
       (8, ''2180bb43-4a86-463e-8a64-86af145af27e'', ''124444 '', 0, ''123 '', ''123 '', ''124 '', ''12333 '',
        ''2023 - 09 - 08 17:02:14'', ''2023 - 09 - 08 17:02:14'');

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) NOT NULL COMMENT '' 考试名称 '',
    `link_code`   varchar(36)  DEFAULT NULL COMMENT '' 小程序链接码 '',
    `description` varchar(500) DEFAULT NULL COMMENT '' 考试描述 '',
    `start_time`  datetime    NOT NULL COMMENT '' 开始时间 '',
    `end_time`    datetime    NOT NULL COMMENT '' 结束时间 '',
    `uuid`        varchar(255) DEFAULT NULL COMMENT '' 资源ID '',
    `exam_count`  int          DEFAULT NULL COMMENT '' 考试次数 '',
    `create_time` datetime    NOT NULL COMMENT '' 创建时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''考试表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam`
VALUES (4, ''123 '', ''2180bb43-4a86-463e-8a64-86af145af27e'', ''123 '', ''2023 - 09 - 07 21:58:30'',
        ''2023 - 09 - 07 21:58:30'', ''11111 '', 2, ''2023 - 09 - 07 21:58:30''),
       (5, ''123 '', ''2180bb43-4a86-463e-8a64-86af145af27e'', ''123 '', ''2023 - 09 - 07 21:58:30'',
        ''2023 - 09 - 07 21:58:30'', ''11111 '', 2, ''2023 - 09 - 07 21:58:30''),
       (6, ''期末考试'', ''2180bb43-4a86-463e-8a64-86af145af27e'', ''描述'', ''2023 - 09 - 08 16:19:47'',
        ''2023 - 09 - 08 16:19:47'', ''11111 '', 2, ''2023 - 09 - 08 16:19:47'');

--
-- Table structure for table `link_code_and_appid_map`
--

DROP TABLE IF EXISTS `link_code_and_appid_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_code_and_appid_map`
(
    `link_code`   varchar(36)  DEFAULT NULL COMMENT '' 链接代码 '',
    `app_id`      varchar(255) DEFAULT NULL COMMENT '' 小程序ID '',
    `update_time` datetime     DEFAULT NULL COMMENT '' 更新时间 ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''链接码和应用Id的映射表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_code_and_appid_map`
--

INSERT INTO `link_code_and_appid_map`
VALUES (''po80bb43-4a86-463e-8a64-86af145af212'', ''wxc62afc1444173666'', ''2023 - 09 - 09 08:31:00''),
       (''4180bb43-4a86-463e-8a64-86af145af2op'', ''wxc62afc1444173555'', ''2023 - 09 - 09 08:31:00''),
       (''8180bb43-4a86-463e-8a64-86af145af2gf'', ''wxc62afc1444173999'', ''2023 - 09 - 09 08:31:00''),
       (''3180bb43-4a86-463e-8a64-86af145af245'', ''wxc62afc1444173888'', ''2023 - 09 - 09 08:31:00''),
       (''2180bb43-4a86-463e-8a64-86af145af27e'', ''wxc62afc1444173777'', ''2023 - 09 - 09 08:31:00'');

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `user_id`      int      NOT NULL COMMENT '' 用户ID '',
    `algorithm_id` int      NOT NULL COMMENT '' 算法ID '',
    `result`       json     DEFAULT NULL COMMENT '' 识别结果 '',
    `create_time`  datetime NOT NULL COMMENT '' 创建时间 '',
    `update_time`  datetime DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''识别报告'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--


--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `full_name`   varchar(255) DEFAULT NULL,
    `alias`       varchar(255) DEFAULT NULL,
    `suffix`      varchar(255) DEFAULT NULL COMMENT '' 后缀 '',
    `size`        bigint       DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''资源表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources`
VALUES (113, ''Snipaste_2023-09-04_10-22-46.jpg'', ''74b310cb-22e0-4137-8901-162b1c14bbb7'', ''jpg'', 4654,
        ''2023 - 09 - 04 13:56:20'', ''2023 - 09 - 04 13:56:20''),
       (114, ''布布比✌.jpg'', ''5531030d-e568-4446-bc95-9d3a589450f0'', ''jpg'', 76958, ''2023 - 09 - 04 13:59:09'',
        ''2023 - 09 - 04 13:59:09''),
       (115, ''Snipaste_2023-09-04_10-22-46.jpg'', ''b14c3c50-238f-47ff-91c9-18af9c385d5e'', ''jpg'', 4654,
        ''2023 - 09 - 04 14:09:00'', ''2023 - 09 - 04 14:09:00''),
       (116, ''demo视频.mp4'', ''e7fc2dd4-0871-4f39-800f-b608ad9759d6'', ''mp4'', 2501450, ''2023 - 09 - 04 14:58:36'',
        ''2023 - 09 - 04 14:58:36''),
       (117, ''考试模板.xlsx'', ''ed23e489-ddc3-4047-a9c9-acfba7feae93'', ''xlsx'', 10761, ''2023 - 09 - 08 14:35:18'',
        ''2023 - 09 - 08 14:35:18'');

--
-- Table structure for table `sports_ability`
--

DROP TABLE IF EXISTS `sports_ability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports_ability`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `user_id`      int      NOT NULL COMMENT '' 用户ID '',
    `ability_type` tinyint(1) NOT NULL COMMENT '' 0有氧能力,
    1肌肉力量,
    2柔韧伸展,
    3灵活敏捷,
    4动作准确 '',
    `value`        int      NOT NULL DEFAULT ''0 '' COMMENT '' 能力值 '',
    `create_time`  datetime NOT NULL COMMENT '' 创建时间 '',
    `update_time`  datetime          DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''用户体育能力表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_ability`
--


--
-- Table structure for table `study_data`
--

DROP TABLE IF EXISTS `study_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_data`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `user_id`     int      NOT NULL COMMENT '' 用户ID '',
    `course_id`   int      NOT NULL COMMENT '' 课程ID '',
    `duration`    int      NOT NULL DEFAULT ''0 '' COMMENT '' 学习时长（秒）'',
    `create_time` datetime NOT NULL COMMENT '' 创建时间 '',
    `update_time` datetime          DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''用户学习数据表'';
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
CREATE TABLE `study_record`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `user_id`     int      NOT NULL COMMENT '' 用户ID '',
    `course_id`   int      NOT NULL COMMENT '' 课程ID '',
    `lesson_id`   int      NOT NULL COMMENT '' 课时ID '',
    `status`      tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 学习状态，0-未学习，1-学习中，2-已学完 '',
    `start_time`  datetime DEFAULT NULL COMMENT '' 开始学习时间 '',
    `end_time`    datetime DEFAULT NULL COMMENT '' 结束学习时间 '',
    `create_time` datetime NOT NULL COMMENT '' 创建时间 '',
    `update_time` datetime DEFAULT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''用户学习记录表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_record`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`              int         NOT NULL AUTO_INCREMENT,
    `type`            int         DEFAULT NULL COMMENT '' 0:管理员 1: 学生 '',
    `username`        varchar(50) NOT NULL COMMENT '' 用户名 '',
    `nickname`        varchar(50) NOT NULL COMMENT '' 昵称 '',
    `gender`          tinyint(1) DEFAULT NULL COMMENT '' 性别 '',
    `phone`           varchar(20) DEFAULT NULL COMMENT '' 手机号 '',
    `duration_of_use` mediumtext COMMENT '' 使用时长 '',
    `clocking_days`   int         DEFAULT NULL COMMENT '' 打卡天数 '',
    `create_time`     datetime    NOT NULL COMMENT '' 创建时间 '',
    `update_time`     datetime    DEFAULT NULL COMMENT '' 更新时间 '',
    `attr`            json        NOT NULL COMMENT '' attr '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''用户表'';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-09  8:31:17
