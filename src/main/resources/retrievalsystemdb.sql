/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : retrievalsystemdb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-15 21:40:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for context
-- ----------------------------
DROP TABLE IF EXISTS `context`;
CREATE TABLE `context` (
  `ArticleId` int(4) NOT NULL AUTO_INCREMENT,
  `Title` char(255) DEFAULT NULL,
  `Time` varchar(255) DEFAULT NULL,
  `Source` varchar(255) DEFAULT NULL,
  `Context` longtext,
  `ContextNoCode` longtext,
  `Url` char(255) DEFAULT NULL,
  `StopTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spider_selectword
-- ----------------------------
DROP TABLE IF EXISTS `spider_selectword`;
CREATE TABLE `spider_selectword` (
  `selectwordId` int(11) NOT NULL AUTO_INCREMENT,
  `selectword` longtext,
  PRIMARY KEY (`selectwordId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spider_stopword
-- ----------------------------
DROP TABLE IF EXISTS `spider_stopword`;
CREATE TABLE `spider_stopword` (
  `stopwordId` int(11) NOT NULL AUTO_INCREMENT,
  `stopword` longtext COMMENT '停用词，即禁用词',
  PRIMARY KEY (`stopwordId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spider_taskinfo
-- ----------------------------
DROP TABLE IF EXISTS `spider_taskinfo`;
CREATE TABLE `spider_taskinfo` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `cssSeletor` longtext,
  `xpath` longtext,
  `titleCSS` longtext,
  `titleXpath` longtext,
  `contentCSS` longtext,
  `contentXpath` longtext,
  `starttimeCSS` longtext,
  `starttimeXpath` longtext,
  `nextTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinformation
-- ----------------------------
DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `workspace` varchar(255) DEFAULT NULL,
  `ArticleId` int(11) DEFAULT NULL,
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '用户邮箱激活状态\r\n0为未激活\r\n1为已激活',
  `info` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
