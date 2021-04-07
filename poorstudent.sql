/*
 Navicat Premium Data Transfer

 Source Server         : 3310
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3310
 Source Schema         : poorstudent

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 26/02/2021 13:04:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hard
-- ----------------------------
DROP TABLE IF EXISTS `hard`;
CREATE TABLE `hard`  (
  `hard_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `hard_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hard_result` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_id` int(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`hard_id`) USING BTREE,
  INDEX `hard_ibfk_1`(`student_id`) USING BTREE,
  CONSTRAINT `hard_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hard
-- ----------------------------
INSERT INTO `hard` VALUES (7, 'aaaa', '通过', 5);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `resource_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_time` datetime(0) NULL DEFAULT NULL,
  `resource_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (2, '院级奖学金', '2021-01-06 14:22:22', '简介', '院校');
INSERT INTO `resource` VALUES (3, '国家奖学金', '2021-01-07 16:23:42', '国家奖', '国家');
INSERT INTO `resource` VALUES (4, '社会援助', '2021-01-14 16:50:34', '社会援助', '社会');
INSERT INTO `resource` VALUES (5, '社会援助2', '2020-12-16 16:50:53', '社会116', '社会');
INSERT INTO `resource` VALUES (7, '我的援助', '2021-02-04 15:08:57', '我的援助,\r\n非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长非常长', '社会');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_cardid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_year` int(11) NULL DEFAULT NULL,
  `student_education` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_department` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_class` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_population` int(11) NULL DEFAULT NULL,
  `student_res` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `special_oad` bit(1) NULL DEFAULT NULL,
  `special_sp` bit(1) NULL DEFAULT NULL,
  `special_com` bit(1) NULL DEFAULT NULL,
  `special_fo` bit(1) NULL DEFAULT NULL,
  `special_sa` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `student_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (5, '小明', '男', '006', '420982165412453564', 2014, '专科', '马术学院', '（3）班', 4, '城市户口', 1, b'0', b'1', b'1', b'0', b'0');
INSERT INTO `student` VALUES (7, '测试1', '男', '011', '420982165412453561', 2011, '专科', '信息工程学院', '（4）班', 3, '城市户口', NULL, b'1', b'0', b'1', b'1', b'0');
INSERT INTO `student` VALUES (8, '测试2', '女', '012', '420982165412453562', 2011, '本科', '信息工程学院', '（2）班', 2, '城市户口', NULL, b'0', b'1', b'1', b'0', b'0');
INSERT INTO `student` VALUES (9, '测试3', '男', '013', '420982165412453563', 2011, '本科', '信息工程学院', '（1）班', 2, '农村户口', NULL, b'1', b'0', b'1', b'1', b'0');
INSERT INTO `student` VALUES (10, '测试4', '女', '014', '420982165412453565', 2011, '本科', '信息工程学院', '（1）班', 1, '农村户口', NULL, b'0', b'1', b'0', b'1', b'1');
INSERT INTO `student` VALUES (11, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (12, '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (14, '14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (15, '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for support
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support`  (
  `support_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `student_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `support_result` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`support_id`) USING BTREE,
  INDEX `support_ibfk_1`(`resource_id`) USING BTREE,
  INDEX `support_ibfk_2`(`student_id`) USING BTREE,
  CONSTRAINT `support_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `support_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of support
-- ----------------------------
INSERT INTO `support` VALUES (10, 3, 5, '未通过');
INSERT INTO `support` VALUES (21, 2, 5, '待认定');
INSERT INTO `support` VALUES (24, 7, 5, '通过');
INSERT INTO `support` VALUES (26, 4, 5, '待认定');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_role` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'student', '3086cbca51e6d93acd1df667580911f8', '15971253779', '1432721789@qq.com', '学生');
INSERT INTO `user` VALUES (3, 't', '3086cbca51e6d93acd1df667580911f8', '15971253779', '1432721789@qq.com', '资助管理员');
INSERT INTO `user` VALUES (5, 'admin2', '3086cbca51e6d93acd1df667580911f8', '15971253779', '1432721789@qq.com', '系统管理员');
INSERT INTO `user` VALUES (8, 'admin', '3086cbca51e6d93acd1df667580911f8', '17798256634', '1432721789@qq.com', '系统管理员');

SET FOREIGN_KEY_CHECKS = 1;
