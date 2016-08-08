/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : base4j

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2016-07-30 16:46:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id` int(11) NOT NULL auto_increment,
  `index_id` int(20) default NULL,
  `code` varchar(50) default NULL,
  `code_text` varchar(100) default NULL,
  `enable` int(1) NOT NULL default '1',
  `editable_` int(1) NOT NULL default '1',
  `remark_` varchar(500) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `field_id_code` (`index_id`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dic_index
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_index`;
CREATE TABLE `sys_dic_index` (
  `id` int(11) NOT NULL auto_increment,
  `catalog_id` int(20) NOT NULL default '0',
  `key` varchar(50) default NULL,
  `name` varchar(200) default NULL,
  `remark` varchar(1000) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_performance
-- ----------------------------
DROP TABLE IF EXISTS `sys_performance`;
CREATE TABLE `sys_performance` (
  `id` int(11) NOT NULL,
  `method_name` varchar(126) NOT NULL COMMENT '方法名',
  `method_param` text COMMENT '方法参数',
  `class_name` varchar(126) NOT NULL COMMENT '类名',
  `time_cost` mediumtext NOT NULL COMMENT '执行时间',
  `create_time` datetime default NULL COMMENT '添加时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `a` char(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(20) NOT NULL auto_increment,
  `dept_name` varchar(50) default NULL COMMENT '部门名称',
  `parent_id` int(20) default NULL COMMENT '父节点',
  `enable` int(1) default NULL COMMENT '是否可用',
  `leaf` int(1) default NULL COMMENT '是否是叶子节点',
  `create_user_id` int(11) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_user_id` int(11) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='²¿ÃÅ';

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL,
  `permission_url` varchar(256) NOT NULL COMMENT '权限URL',
  `permission_name` varchar(256) NOT NULL COMMENT '权限名称',
  `create_user_id` int(11) default NULL COMMENT '创建人ID',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_user_id` int(11) default NULL COMMENT '修改人ID',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL auto_increment,
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `role_type` int(3) NOT NULL COMMENT '角色类型',
  `create_user_id` int(11) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_user_id` int(11) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL COMMENT '角色',
  `permission_id` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `account` varchar(256) NOT NULL COMMENT '账户',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `dept_id` int(11) default NULL COMMENT '部门ID',
  `sex` int(1) default NULL COMMENT '性别 0：男 1：女',
  `phone` varchar(11) default NULL COMMENT '电话号码',
  `mail` varchar(128) default NULL COMMENT '邮箱',
  `user_type` int(3) default NULL COMMENT '用户类型',
  `usable` tinyint(1) NOT NULL default '1' COMMENT '是否可用 0：不可用 1：可用',
  `locked` tinyint(1) NOT NULL default '0' COMMENT '是否锁定 0：否 1：是',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
