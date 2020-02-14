/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : fantasy-box

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 02/12/2020 17:35:03 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `SPRING_SESSION`
-- ----------------------------
DROP TABLE IF EXISTS `SPRING_SESSION`;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `SPRING_SESSION_ATTRIBUTES`
-- ----------------------------
DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `pay`
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` bigint(20) NOT NULL,
  `qr_cpde_url` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `qr_user_id` (`user_id`),
  CONSTRAINT `qr_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `file_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `select_tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_user_id` (`user_id`),
  CONSTRAINT `p_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `product`
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES ('1', 'p1', '1', 'localhost:8080/file/img.jpg', 'djhf', null, null, null, '2020-02-11 18:10:08', '2020-02-11 18:10:12', null), ('2', 'pro1', '2', 'fu1', 'des1', 'test01', 'au2', null, '2020-02-11 19:42:13', '2020-02-11 19:42:13', null), ('4', 'pro2', '2', 'fu2', 'des2', 'test01', 'au2', null, '2020-02-11 19:48:07', '2020-02-11 19:48:07', null), ('5', 'pro3', '2', 'fu3', 'des3', 'test01', 'au2', null, '2020-02-11 19:52:13', '2020-02-11 19:52:13', null), ('6', 'pro3', '2', 'fu3', 'des3', 'test01', 'au2', null, '2020-02-11 19:53:19', '2020-02-11 19:53:19', null), ('7', 'pro3', '2', 'fu3', 'des3', 'test01', 'au2', null, '2020-02-11 19:53:49', '2020-02-11 19:53:49', null), ('9', 'pro3', '2', 'fu3', 'des3', 'test01', 'au2', null, '2020-02-11 20:00:19', '2020-02-11 20:00:19', null), ('10', 't1', '1', 'http://localhost:8080/file/1581427562797Head First设计模式.fdf', 'akgkfgaga', 'test00', 'au1', null, '2020-02-11 21:26:02', '2020-02-11 21:26:02', null);
COMMIT;

-- ----------------------------
--  Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_name` (`tag_name`) USING BTREE,
  KEY `tag_product_id` (`product_id`),
  CONSTRAINT `tag_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tag`
-- ----------------------------
BEGIN;
INSERT INTO `tag` VALUES ('6', 'tag1', '1'), ('7', 'tag2', '1'), ('8', 'tag3', '1'), ('9', 'tag4', '1'), ('10', 'tag5', '1'), ('41', 'tag6', '1'), ('42', 'tag7', '1'), ('43', 'tag8', '1'), ('44', 'tag9', '1'), ('45', 'tag10', '1'), ('46', 'kdah', '10'), ('47', 'akh', '10'), ('48', 'akgf', '10');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `qr_code_url` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'test00', '123456', 'test@123.com', 'qru1', 'au1'), ('2', 'test01', '123456', 'test@456.com', 'qru2', 'au2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
