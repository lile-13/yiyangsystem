-- ============================================
-- 东软颐养中心管理系统 - 数据库初始化脚本
-- 组员C: 健康管家 + 登录用户相关表
-- ============================================

CREATE DATABASE IF NOT EXISTS elderly_care DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE elderly_care;

-- ============================================
-- 1. 角色表 (role)
-- ============================================
DROP TABLE IF EXISTS role;
CREATE TABLE role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    create_time DATETIME NOT NULL,
    update_time DATETIME,
    update_by INT,
    is_deleted INT NOT NULL DEFAULT 0 COMMENT '逻辑删除(0显示/1隐藏)',
    name VARCHAR(50) NOT NULL COMMENT '系统角色名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 2. 用户表 (user)
-- ============================================
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    create_time DATETIME NOT NULL,
    create_by INT NOT NULL,
    update_time DATETIME,
    update_by INT,
    is_deleted INT NOT NULL DEFAULT 0 COMMENT '逻辑删除(0显示/1隐藏)',
    nickname VARCHAR(20) NOT NULL COMMENT '真实姓名',
    username VARCHAR(20) NOT NULL COMMENT '系统账号',
    password VARCHAR(20) NOT NULL COMMENT '密码',
    sex INT NOT NULL COMMENT '性别(0女/1男)',
    email VARCHAR(254),
    phone_number VARCHAR(20) NOT NULL COMMENT '电话号码',
    role_id INT NOT NULL COMMENT '角色编号(1管理员/2健康管家)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 3. 护理记录表 (nurserecord)
-- ============================================
DROP TABLE IF EXISTS nurserecord;
CREATE TABLE nurserecord (
    id INT PRIMARY KEY AUTO_INCREMENT,
    is_deleted INT NOT NULL DEFAULT 0 COMMENT '逻辑删除(0显示/1隐藏)',
    customer_id INT NOT NULL COMMENT '客户ID',
    item_id INT NOT NULL COMMENT '护理项目ID',
    nursing_time DATETIME NOT NULL COMMENT '护理时间',
    nursing_content VARCHAR(255) COMMENT '护理内容',
    nursing_count INT NOT NULL COMMENT '护理数量',
    user_id INT NOT NULL COMMENT '护理人员ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 初始数据
-- ============================================

-- 角色
INSERT INTO role(id, create_time, is_deleted, name) VALUES
(1, NOW(), 0, '管理员'),
(2, NOW(), 0, '健康管家');

-- 用户: 管理员(初始密码同账号)
INSERT INTO user(create_time, create_by, is_deleted, nickname, username, password, sex, phone_number, role_id) VALUES
(NOW(), 1, 0, '系统管理员1', 'admin', 'admin', 1, '13800000001', 1),
(NOW(), 1, 0, '系统管理员2', 'admin1', 'admin1', 1, '13800000002', 1),
(NOW(), 1, 0, '系统管理员3', 'admin2', 'admin2', 0, '13800000003', 1);

-- 健康管家测试账号
INSERT INTO user(create_time, create_by, is_deleted, nickname, username, password, sex, phone_number, role_id) VALUES
(NOW(), 1, 0, '健康管家张三', 'hgj01', '123456', 1, '13900000001', 2),
(NOW(), 1, 0, '健康管家李四', 'hgj02', '123456', 0, '13900000002', 2);
