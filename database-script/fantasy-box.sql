/*
 Navicat Premium Data Transfer

 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : fantasy-box

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 15/02/2020 19:27:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` bigint NOT NULL,
  `qr_cpde_url` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `qr_user_id` (`user_id`),
  CONSTRAINT `qr_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, 'p1', 1, 'localhost:8080/file/img.jpg', 'djhf', NULL, NULL, NULL, '2020-02-11 18:10:08', '2020-02-11 18:10:12', NULL);
INSERT INTO `product` VALUES (2, 'pro1', 2, 'fu1', 'des1', 'test01', 'au2', NULL, '2020-02-11 19:42:13', '2020-02-11 19:42:13', NULL);
INSERT INTO `product` VALUES (4, 'pro2', 2, 'fu2', 'des2', 'test01', 'au2', NULL, '2020-02-11 19:48:07', '2020-02-11 19:48:07', NULL);
INSERT INTO `product` VALUES (5, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:52:13', '2020-02-11 19:52:13', NULL);
INSERT INTO `product` VALUES (6, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:53:19', '2020-02-11 19:53:19', NULL);
INSERT INTO `product` VALUES (7, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:53:49', '2020-02-11 19:53:49', NULL);
INSERT INTO `product` VALUES (9, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 20:00:19', '2020-02-11 20:00:19', NULL);
INSERT INTO `product` VALUES (10, 't1', 1, 'http://localhost:8080/file/1581427562797Head First设计模式.fdf', 'akgkfgaga', 'test00', 'au1', NULL, '2020-02-11 21:26:02', '2020-02-11 21:26:02', NULL);
INSERT INTO `product` VALUES (11, 't1', 21, '1581763852752_最好的工具和最锐利的武器_晚年恩格斯眼中的唯物辩证法_郝立新.pdf', 'akgkfgaga', 'gyue', NULL, NULL, '2020-02-15 18:50:52', '2020-02-15 18:50:52', NULL);
INSERT INTO `product` VALUES (12, 't1', 21, '158176459728810.1.1.727.5538.pdf', 'akgkfgaga', 'gyue', NULL, NULL, '2020-02-15 19:03:17', '2020-02-15 19:03:17', NULL);
COMMIT;

-- ----------------------------
-- Table structure for SPRING_SESSION
-- ----------------------------
DROP TABLE IF EXISTS `SPRING_SESSION`;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of SPRING_SESSION
-- ----------------------------
BEGIN;
INSERT INTO `SPRING_SESSION` VALUES ('918f412f-8821-47d2-b6aa-21ed36c609bb', 'b3743e1d-446e-4831-aacb-aa016bdfca34', 1581763452913, 1581764597256, 1800, 1581766397256, NULL);
COMMIT;

-- ----------------------------
-- Table structure for SPRING_SESSION_ATTRIBUTES
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
-- Records of SPRING_SESSION_ATTRIBUTES
-- ----------------------------
BEGIN;
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('918f412f-8821-47d2-b6aa-21ed36c609bb', 'isLogin', 0xACED0005737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('918f412f-8821-47d2-b6aa-21ed36c609bb', 'userId', 0xACED00057372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000015);
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_content` (`tag_name`,`product_id`),
  KEY `tag_product_id` (`product_id`),
  CONSTRAINT `tag_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` VALUES (48, 'akgf', 10);
INSERT INTO `tag` VALUES (78, 'akgf', 12);
INSERT INTO `tag` VALUES (47, 'akh', 10);
INSERT INTO `tag` VALUES (77, 'akh', 12);
INSERT INTO `tag` VALUES (46, 'kdah', 10);
INSERT INTO `tag` VALUES (76, 'kdah', 12);
INSERT INTO `tag` VALUES (6, 'tag1', 1);
INSERT INTO `tag` VALUES (45, 'tag10', 1);
INSERT INTO `tag` VALUES (7, 'tag2', 1);
INSERT INTO `tag` VALUES (8, 'tag3', 1);
INSERT INTO `tag` VALUES (9, 'tag4', 1);
INSERT INTO `tag` VALUES (10, 'tag5', 1);
INSERT INTO `tag` VALUES (41, 'tag6', 1);
INSERT INTO `tag` VALUES (42, 'tag7', 1);
INSERT INTO `tag` VALUES (43, 'tag8', 1);
INSERT INTO `tag` VALUES (44, 'tag9', 1);
INSERT INTO `tag` VALUES (57, '呃呃呃', 12);
INSERT INTO `tag` VALUES (55, '啊', 12);
INSERT INTO `tag` VALUES (56, '嗷嗷', 12);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'test00', '123456', 'test@123.com', 'qru1', 'au1', NULL, NULL);
INSERT INTO `user` VALUES (2, 'test01', '123456', 'test@456.com', 'qru2', 'au2', NULL, NULL);
INSERT INTO `user` VALUES (20, 'wxy', 'dW2rKu+At4+jVNmTKxmhQGhfqlco1LPkM8nWL8YZqlr7SijSR3yeZNVeOW99Ml4F0X6YVRDSNItHmsvxTslh1D0yJwt6ZNFsUV6eonEqNCAK/nlZ/V/bbyLyBt11Xep0uGjmt3Lj/uoKo13KUFPeQMhXID6Qg5JVDorfqHRK1KU=', 'shea_wong@163.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NjQ4NTAzNywiZXhwIjoxNTgxODQyODg1MDM3fQ.zvxijJ8oefAvnlTfKO_og3O-nuidOK-6bgFBfU5gBlQ', b'1');
INSERT INTO `user` VALUES (21, 'gyue', 'b+mU19syzmQGsXKHLFFK3id4ZFqvUODGPoXVSz8EDeoCeOgwh8GZnxFDYqYyzuqQssyoL5xwCjLroYZ7FOGInXTlWoEb3iLFzR3aWbYBObC9ZAlMBPC00a/onyDMk7X4oHut4oQS9WZPxHLk8WyEYjROvS4ODxWcWhhtwmKDiEE=', '786330297@qq.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NzAyNzgwNiwiZXhwIjoxNTgxODQzNDI3ODA2fQ.D8aUtBJ9bJOpDS7v91v9eHPO1kENF53SVZBENwd3H14', b'1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
