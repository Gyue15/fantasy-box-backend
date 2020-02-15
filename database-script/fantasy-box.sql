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

 Date: 02/15/2020 16:54:23 PM
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
--  Records of `SPRING_SESSION`
-- ----------------------------
BEGIN;
INSERT INTO `SPRING_SESSION` VALUES ('c145c095-f724-48b2-b6fa-88bc2e4fc046', '4e8e59c7-f650-4b40-8699-066da8671897', '1581756659436', '1581756659437', '1800', '1581758459437', null);
COMMIT;

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
--  Records of `SPRING_SESSION_ATTRIBUTES`
-- ----------------------------
BEGIN;
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('c145c095-f724-48b2-b6fa-88bc2e4fc046', 'isLogin', 0xaced0005737200116a6176612e6c616e672e426f6f6c65616ecd207280d59cfaee0200015a000576616c7565787001), ('c145c095-f724-48b2-b6fa-88bc2e4fc046', 'userId', 0xaced00057372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000014);
COMMIT;

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
  `token` varchar(255) DEFAULT NULL,
  `activated` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'test00', '123456', 'test@123.com', 'qru1', 'au1', null, null), ('2', 'test01', '123456', 'test@456.com', 'qru2', 'au2', null, null), ('20', 'wxy', 'dW2rKu+At4+jVNmTKxmhQGhfqlco1LPkM8nWL8YZqlr7SijSR3yeZNVeOW99Ml4F0X6YVRDSNItHmsvxTslh1D0yJwt6ZNFsUV6eonEqNCAK/nlZ/V/bbyLyBt11Xep0uGjmt3Lj/uoKo13KUFPeQMhXID6Qg5JVDorfqHRK1KU=', 'shea_wong@163.com', null, null, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NjQ4NTAzNywiZXhwIjoxNTgxODQyODg1MDM3fQ.zvxijJ8oefAvnlTfKO_og3O-nuidOK-6bgFBfU5gBlQ', b'1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
