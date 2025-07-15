drop database if exists tmpdatabase;
create database if not exists tmpdatabase
    character set utf8mb4
    collate utf8mb4_0900_ai_ci
    default encryption = 'N';

use tmpdatabase;
create table if not exists tb_user
(
    `id`          bigint       not null auto_increment primary key comment '用户ID',
    `username`    varchar(255) not null,
    `password`    varchar(255) not null,
    `email`       varchar(255),
    `phone`       varchar(255),
    `nickname`    varchar(255),
    `avatar`      varchar(255),
    `gender`      varchar(255),
    `birthday`    varchar(255),
    `enable`      varchar(255),
    `locked`      varchar(255),
    `expired`     varchar(255),
    `last_login`  varchar(255),
    `create_time` varchar(255),
    `update_time` varchar(255),
    `create_by`   varchar(255)
) engine = innodb;
