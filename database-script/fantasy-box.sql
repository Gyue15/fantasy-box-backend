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

 Date: 06/04/2020 17:13:18
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
  PRIMARY KEY (`id`),
  KEY `p_user_id` (`user_id`),
  CONSTRAINT `p_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, 'p1', 1, 'localhost:8080/file/img.jpg', 'djhf', NULL, NULL, NULL, '2020-02-11 18:10:08', '2020-02-11 18:10:12');
INSERT INTO `product` VALUES (2, 'pro1', 2, 'fu1', 'des1', 'test01', 'au2', NULL, '2020-02-11 19:42:13', '2020-02-11 19:42:13');
INSERT INTO `product` VALUES (4, 'pro2', 2, 'fu2', 'des2', 'test01', 'au2', NULL, '2020-02-11 19:48:07', '2020-02-11 19:48:07');
INSERT INTO `product` VALUES (5, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:52:13', '2020-02-11 19:52:13');
INSERT INTO `product` VALUES (6, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:53:19', '2020-02-11 19:53:19');
INSERT INTO `product` VALUES (7, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 19:53:49', '2020-02-11 19:53:49');
INSERT INTO `product` VALUES (9, 'pro3', 2, 'fu3', 'des3', 'test01', 'au2', NULL, '2020-02-11 20:00:19', '2020-02-11 20:00:19');
INSERT INTO `product` VALUES (10, 't1', 1, 'http://localhost:8080/file/1581427562797Head First设计模式.fdf', 'akgkfgaga', 'test00', 'au1', NULL, '2020-02-11 21:26:02', '2020-02-11 21:26:02');
INSERT INTO `product` VALUES (11, 't1', 21, '1581763852752_最好的工具和最锐利的武器_晚年恩格斯眼中的唯物辩证法_郝立新.pdf', 'akgkfgaga', 'gyue', '15817696736231512651510758头像.png', NULL, '2020-02-15 18:50:52', '2020-02-15 20:27:53');
INSERT INTO `product` VALUES (12, 't1', 21, '158176459728810.1.1.727.5538.pdf', 'akgkfgaga', 'gyue', '15817696736231512651510758头像.png', NULL, '2020-02-15 19:03:17', '2020-02-15 20:27:53');
INSERT INTO `product` VALUES (14, '光环5:守护者', 21, '1586161769796u=1496033420,824293380&fm=26&gp=0.jpg', 'akgkfgaga', 'gyue', '15817696736231512651510758头像.png', '1586161769796u=1496033420,824293380&fm=26&gp=0.jpg', '2020-04-06 16:29:29', '2020-04-06 16:29:29');
INSERT INTO `product` VALUES (15, '刺客信条：启示录', 21, '1586162064201刺客信条.jpg', '刺客信条', 'gyue', '15817696736231512651510758头像.png', '1586162064201刺客信条.jpg', '2020-04-06 16:34:24', '2020-04-06 16:34:24');
INSERT INTO `product` VALUES (16, '天龙八部', 21, '1586162170918天龙八部.jpg', '天龙八部，段誉篇', 'gyue', '15817696736231512651510758头像.png', '1586162170918天龙八部.jpg', '2020-04-06 16:36:10', '2020-04-06 16:36:10');
INSERT INTO `product` VALUES (17, '实况足球', 21, '1586162248202实况足球.jpg', '实况足球', 'gyue', '15817696736231512651510758头像.png', '1586162248202实况足球.jpg', '2020-04-06 16:37:28', '2020-04-06 16:37:28');
INSERT INTO `product` VALUES (18, '龙珠z：终极之战', 21, '1586162310926龙珠.jpg', '七龙珠的游戏', 'gyue', '15817696736231512651510758头像.png', '1586162310926龙珠.jpg', '2020-04-06 16:38:30', '2020-04-06 16:38:30');
INSERT INTO `product` VALUES (19, '只狼：影逝二度', 21, '1586162435908只狼.jpg', '只狼', 'gyue', '15817696736231512651510758头像.png', '1586162435908只狼.jpg', '2020-04-06 16:40:35', '2020-04-06 16:40:35');
INSERT INTO `product` VALUES (20, '稻草', 21, '1586162514304151299011837613.jpg', '自然图片', 'gyue', '15817696736231512651510758头像.png', '1586162514304151299011837613.jpg', '2020-04-06 16:41:54', '2020-04-06 16:41:54');
INSERT INTO `product` VALUES (21, '多啦A梦', 21, '15861625613771512961614113002.jpg', '动画片', 'gyue', '15817696736231512651510758头像.png', '15861625613771512961614113002.jpg', '2020-04-06 16:42:41', '2020-04-06 16:42:41');
INSERT INTO `product` VALUES (22, '狗狗', 21, '15861625882161512988358298018.jpg', '可爱的小狗狗', 'gyue', '15817696736231512651510758头像.png', '15861625882161512988358298018.jpg', '2020-04-06 16:43:08', '2020-04-06 16:43:08');
INSERT INTO `product` VALUES (23, '猫猫', 21, '15861626208061512988408544012.jpg', '可爱的小猫猫', 'gyue', '15817696736231512651510758头像.png', '15861626208061512988408544012.jpg', '2020-04-06 16:43:40', '2020-04-06 16:43:40');
INSERT INTO `product` VALUES (24, '紫萝兰永恒花园', 21, '1586163582964u=843151289,2305239344&fm=15&gp=0.jpg', '紫萝兰永恒花园，动画', 'gyue', '15817696736231512651510758头像.png', '1586163582964u=843151289,2305239344&fm=15&gp=0.jpg', '2020-04-06 16:59:42', '2020-04-06 16:59:42');
INSERT INTO `product` VALUES (25, '这个杀手不太冷', 21, '1586163681529杀手.jpg', '这个杀手不太冷', 'gyue', '15817696736231512651510758头像.png', '1586163681529杀手.jpg', '2020-04-06 17:01:21', '2020-04-06 17:01:21');
INSERT INTO `product` VALUES (26, '卓别林摩登时代', 21, '1586163946731卓别林.jpg', '《摩登时代》（Modern Times），是查理·卓别林（Charles Chaplin）导演并主演的一部经典喜剧电影，于1936年2月25日上映。\n本片故事发生在美国20世纪30年代经济萧条时期，工人查理（卓别林饰）在工厂干活、发疯、进入精神病院，这一切都是与当时的经济危机给人们带来的生存危机有着密切的联系。而在艰难的生活中，查理和孤女相濡以沫，场面温馨感人焕发着人性的光辉。\n这部《摩登时代》被认为是美国电影史上最伟大的电影之一，也是查理·卓别林最著名的作品之一。', 'gyue', '15817696736231512651510758头像.png', '1586163946731卓别林.jpg', '2020-04-06 17:05:46', '2020-04-06 17:05:46');
INSERT INTO `product` VALUES (27, '加里波利', 21, 'file-1586164129105ggg.jpg', '每年的4月25日，澳大利亚和新西兰都会共享一个特别重要的公众假日：ANZAC DAY，即澳新军团日（也译作澳纽军团日）。\n在这个节日里，学校会纷纷举办各种主题学习，商场里徽章红花出售。而遍布于各城市的战争纪念馆，则是举办隆重的节日纪念仪式，对市民进行日常爱国教育的最重要基地。', 'gyue', '15817696736231512651510758头像.png', 'cover-1586164129105ggg.jpg', '2020-04-06 17:08:49', '2020-04-06 17:08:49');
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
INSERT INTO `SPRING_SESSION` VALUES ('c6e4bcff-8b7f-46de-8495-a390fd19068a', '3fa65ad8-1b5a-4743-96cc-420d9d636991', 1586163510178, 1586164129026, 1800, 1586165929026, NULL);
INSERT INTO `SPRING_SESSION` VALUES ('c9b3fc0e-0eea-4335-b724-68a26f507f11', 'ab9563bf-f7f7-48e7-8300-b43b8d1d1f73', 1586161317999, 1586163306173, 1800, 1586165106173, NULL);
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
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('c6e4bcff-8b7f-46de-8495-a390fd19068a', 'isLogin', 0xACED0005737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('c6e4bcff-8b7f-46de-8495-a390fd19068a', 'userId', 0xACED00057372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000015);
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('c9b3fc0e-0eea-4335-b724-68a26f507f11', 'isLogin', 0xACED0005737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('c9b3fc0e-0eea-4335-b724-68a26f507f11', 'userId', 0xACED00057372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000015);
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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

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
INSERT INTO `tag` VALUES (87, '图片', 20);
INSERT INTO `tag` VALUES (88, '图片', 21);
INSERT INTO `tag` VALUES (89, '图片', 22);
INSERT INTO `tag` VALUES (90, '图片', 23);
INSERT INTO `tag` VALUES (91, '好玩的', 19);
INSERT INTO `tag` VALUES (81, '游戏', 14);
INSERT INTO `tag` VALUES (82, '游戏', 15);
INSERT INTO `tag` VALUES (83, '游戏', 16);
INSERT INTO `tag` VALUES (84, '游戏', 17);
INSERT INTO `tag` VALUES (85, '游戏', 18);
INSERT INTO `tag` VALUES (86, '游戏', 19);
INSERT INTO `tag` VALUES (92, '视频', 24);
INSERT INTO `tag` VALUES (93, '视频', 25);
INSERT INTO `tag` VALUES (94, '视频', 26);
INSERT INTO `tag` VALUES (95, '视频', 27);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'test00', 'asdfghj', 'test@123.com', 'qru1', 'au1', NULL, NULL);
INSERT INTO `user` VALUES (2, 'test01', '123456', 'test@456.com', 'qru2', 'au2', NULL, NULL);
INSERT INTO `user` VALUES (20, 'wxy', 'dW2rKu+At4+jVNmTKxmhQGhfqlco1LPkM8nWL8YZqlr7SijSR3yeZNVeOW99Ml4F0X6YVRDSNItHmsvxTslh1D0yJwt6ZNFsUV6eonEqNCAK/nlZ/V/bbyLyBt11Xep0uGjmt3Lj/uoKo13KUFPeQMhXID6Qg5JVDorfqHRK1KU=', 'shea_wong@163.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NjQ4NTAzNywiZXhwIjoxNTgxODQyODg1MDM3fQ.zvxijJ8oefAvnlTfKO_og3O-nuidOK-6bgFBfU5gBlQ', b'1');
INSERT INTO `user` VALUES (21, 'gyue', 'cnemCoT73JXZrNhI1lkSxY165p+gVjZzws0mEVAPka0UhRZ+ROcoMi/Dil3N1tw3vpdMXb/9lNJndfc70MvNrw0WpHL1eaynYD1HzhWA8NUBalqZch/VeqpigkwrRoXSzPspfrtejbIFswkPiRWQTdrGp2dr6m/DRLqgfVC8bzQ=', '786330297@qq.com', '1581771034187151298848818108.jpg', '15817696736231512651510758头像.png', 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NzAyNzgwNiwiZXhwIjoxNTgxODQzNDI3ODA2fQ.D8aUtBJ9bJOpDS7v91v9eHPO1kENF53SVZBENwd3H14', b'1');
INSERT INTO `user` VALUES (22, 'test000', 'gbNjhKDHRredz+M02yXieYQpNibsDJ4eGm4bcrp2sF5jOanu5m44IuGadNPrM5cSBfME97Tu17C5orTx/mX8fYKfWwg/iVdjgIPKFYOAyJpbQl7MQ2T9UNj8/wYVstPGqJXroDh0IyQUSAdNcfke50doxt24pGD32jO/CbAR3/M=', 'testtest@123.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4NjA1ODg4NDkwOSwiZXhwIjoxNTg2MTQ1Mjg0OTA5fQ.TtHMvIOEX2j8_zCIPXw3GBpnchI0zfrhcCrWSfmMbd8', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
