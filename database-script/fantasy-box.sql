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

 Date: 08/04/2020 11:35:27
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
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `username` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `p_user_id` (`user_id`),
  CONSTRAINT `p_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (20, '稻草', 21, '1586162514304151299011837613.jpg', '自然图片', 'gyue', '15817696736231512651510758.png', '1586162514304151299011837613.jpg', '2020-04-06 16:41:54', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (21, '多啦A梦', 21, '15861625613771512961614113002.jpg', '动画片', 'gyue', '15817696736231512651510758.png', '15861625613771512961614113002.jpg', '2020-04-06 16:42:41', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (22, '狗狗', 21, '15861625882161512988358298018.jpg', '可爱的小狗狗', 'gyue', '15817696736231512651510758.png', '15861625882161512988358298018.jpg', '2020-04-06 16:43:08', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (23, '猫猫', 21, '15861626208061512988408544012.jpg', '可爱的小猫猫', 'gyue', '15817696736231512651510758.png', '15861626208061512988408544012.jpg', '2020-04-06 16:43:40', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (24, '紫萝兰永恒花园', 21, '1586163582964u=843151289,2305239344&fm=15&gp=0.jpg', '紫萝兰永恒花园，动画', 'gyue', '15817696736231512651510758.png', '1586163582964u=843151289,2305239344&fm=15&gp=0.jpg', '2020-04-06 16:59:42', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (27, '加里波利', 21, 'file-1586164129105ggg.jpg', '每年的4月25日，澳大利亚和新西兰都会共享一个特别重要的公众假日：ANZAC DAY，即澳新军团日（也译作澳纽军团日）。\n在这个节日里，学校会纷纷举办各种主题学习，商场里徽章红花出售。而遍布于各城市的战争纪念馆，则是举办隆重的节日纪念仪式，对市民进行日常爱国教育的最重要基地。', 'gyue', '15817696736231512651510758.png', 'cover-1586164129105ggg.jpg', '2020-04-06 17:08:49', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (28, '兔子', 21, '50bbbbb3-860c-426f-980b-2ea6557382b2.jpg', '兔子门', 'gyue', '15817696736231512651510758.png', 'e71e847c-ea0a-457d-8ef2-4b3d5140d3ac.jpg', '2020-04-06 21:54:33', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (29, '刺客信条', 21, 'b8e0b332-ab66-42c9-a70f-1ca829585e9c.jpg', '《刺客信条》是由育碧蒙特利尔工作室研发的动作冒险类游戏系列，主要角色有阿泰尔、康纳、艾吉奥，于2007年发行第一部，游戏平台为PS3、PC和PSP。 \n该游戏系列是以超高的自由度和精美的画面作为最大卖点的动作类游戏。玩家将在每部游戏中控制一名刺客，通过在任务中巧妙穿插重要的历史人物及历史事件带给玩家深沉的代入感。', 'gyue', '15817696736231512651510758.png', 'a3e46fbb-fa0e-46e0-abaf-71c0c47f3737.jpg', '2020-04-06 22:49:40', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (30, '实况足球', 21, '9bb45c07-b18a-4004-b52b-ef77908cac10.jpg', '《实况足球》，由KONAMI制作开发的一款足球游戏，于1996年正式发行。\n《实况足球》是为了满足广大足球爱好者和球迷的需求，精心打造的一款3D游戏。游戏画面效果逼真，操作简单，深受广大足球爱好者和球迷的喜爱。\n2017年8月19日，英格兰传奇球星贝克汉姆成为《实况足球2018》宣传大使，成为实况传奇球员的一员。', 'gyue', '15817696736231512651510758.png', 'f70192c4-ade7-416e-949d-75413cbbc94f.jpg', '2020-04-06 22:51:02', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (32, '天龙八部', 21, '5cbb224d-15ad-438e-a679-28748b1d801a.jpg', '《天龙八部》是由搜狐畅游研发的一款武侠角色扮演网游，于2007年4月4日在中国发行。该游戏改编自金庸先生的同名小说，并得到了金庸先生的正式授权。\n《天龙八部》是由搜狐畅游公司投入巨额资金，历时三年精心自主研发的武侠网游大作，极尽精良的美术制作、人性化的功能设计、忠实原著的剧情副本、新奇丰富的技能系统、贴心的珍兽及帮派系统等诸多新颖多元的玩点，为玩家塑造出和谐完美的虚拟武侠社区世界，带来精彩的游戏体验。', 'gyue', '15817696736231512651510758.png', 'e9316555-4e27-46fe-9446-ad65447a617c.jpg', '2020-04-06 22:52:50', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (33, '只狼：影逝二度', 21, '608b65f1-85fb-4f89-b494-60334c1ce44c.jpg', '在《Sekiro: Shadows Die Twice》中，一个名誉不再、伤痕累累的忍者，一个从死亡边缘捡回一命的战士。你效忠守护继承古老血统的年轻御子，与危险的苇名一族以及众多凶恶之徒为敌。年轻的御子被抓走后，为挽回荣誉，你将不畏死亡，踏上危机四伏的征程。\n探索生死冲突不断的16世纪后期，感受残酷的日本战国时代，在黑暗、扭曲的世界，与威胁生命的敌人对峙。活用义手装备各种致命武器，大显忍者身手，在血腥对抗中潜行、上下穿梭，与敌人正面激烈交锋。\n复仇雪耻。夺回荣誉。巧妙杀敌。 ', 'gyue', '15817696736231512651510758.png', '3827a9d9-a4aa-41b9-aa8d-266e77b40854.jpg', '2020-04-06 22:54:40', '2020-04-06 22:56:52');
INSERT INTO `product` VALUES (34, '龙珠Z：终极之战', 21, '82e35fd0-a25e-4d1e-82d7-ff0e7f2d24b8.jpg', 'NBGI所推出的系列最新作，游戏的日版预计将于2014年1月23日发售，美版将于1月28日上市，而欧版的发售日则为1月24日，其对应平台为PS3、XBOX 360和PSV。\n开发商Namco Bandai连续几天公布了多批《龙珠Z：终极之战》截图，同时他们也透露了游戏中的4种战斗模式。在这4种模式中，全世界的玩家们可以进行对抗或者合作游戏。最多支持8人乱斗。', 'gyue', '15817696736231512651510758.png', '973a0fe1-cedf-4539-a82f-57a80767f405.jpg', '2020-04-06 23:03:39', '2020-04-06 23:03:39');
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
INSERT INTO `SPRING_SESSION` VALUES ('8571a153-4166-423d-9646-f866c4637bea', '3a5e9705-0e44-4957-bee0-129962d0b1a4', 1586316831569, 1586316896984, 1800, 1586318696984, NULL);
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
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('8571a153-4166-423d-9646-f866c4637bea', 'isLogin', 0xACED0005737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('8571a153-4166-423d-9646-f866c4637bea', 'userId', 0xACED00057372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000015);
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
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` VALUES (87, '图片', 20);
INSERT INTO `tag` VALUES (88, '图片', 21);
INSERT INTO `tag` VALUES (89, '图片', 22);
INSERT INTO `tag` VALUES (90, '图片', 23);
INSERT INTO `tag` VALUES (96, '图片', 28);
INSERT INTO `tag` VALUES (97, '游戏', 29);
INSERT INTO `tag` VALUES (98, '游戏', 30);
INSERT INTO `tag` VALUES (100, '游戏', 32);
INSERT INTO `tag` VALUES (101, '游戏', 33);
INSERT INTO `tag` VALUES (102, '游戏', 34);
INSERT INTO `tag` VALUES (92, '视频', 24);
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
INSERT INTO `user` VALUES (20, 'wxy', 'dW2rKu+At4+jVNmTKxmhQGhfqlco1LPkM8nWL8YZqlr7SijSR3yeZNVeOW99Ml4F0X6YVRDSNItHmsvxTslh1D0yJwt6ZNFsUV6eonEqNCAK/nlZ/V/bbyLyBt11Xep0uGjmt3Lj/uoKo13KUFPeQMhXID6Qg5JVDorfqHRK1KU=', 'shea_wong@163.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NjQ4NTAzNywiZXhwIjoxNTgxODQyODg1MDM3fQ.zvxijJ8oefAvnlTfKO_og3O-nuidOK-6bgFBfU5gBlQ', b'1');
INSERT INTO `user` VALUES (21, 'gyue', 'cnemCoT73JXZrNhI1lkSxY165p+gVjZzws0mEVAPka0UhRZ+ROcoMi/Dil3N1tw3vpdMXb/9lNJndfc70MvNrw0WpHL1eaynYD1HzhWA8NUBalqZch/VeqpigkwrRoXSzPspfrtejbIFswkPiRWQTdrGp2dr6m/DRLqgfVC8bzQ=', '786330297@qq.com', '1581771034187151298848818108.jpg', '15817696736231512651510758.png', 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc1NzAyNzgwNiwiZXhwIjoxNTgxODQzNDI3ODA2fQ.D8aUtBJ9bJOpDS7v91v9eHPO1kENF53SVZBENwd3H14', b'1');
INSERT INTO `user` VALUES (22, 'test000', 'gbNjhKDHRredz+M02yXieYQpNibsDJ4eGm4bcrp2sF5jOanu5m44IuGadNPrM5cSBfME97Tu17C5orTx/mX8fYKfWwg/iVdjgIPKFYOAyJpbQl7MQ2T9UNj8/wYVstPGqJXroDh0IyQUSAdNcfke50doxt24pGD32jO/CbAR3/M=', 'testtest@123.com', NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4NjA1ODg4NDkwOSwiZXhwIjoxNTg2MTQ1Mjg0OTA5fQ.TtHMvIOEX2j8_zCIPXw3GBpnchI0zfrhcCrWSfmMbd8', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
