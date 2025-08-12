use spring_security;
-- auto-generated definition
create table emp
(
    id       bigint unsigned primary key auto_increment comment 'ID',
    dept_id  bigint unsigned              null comment '部门ID',
    username varchar(50)                  not null comment '用户名',
    password varchar(255)                 null comment '密码',
    name     varchar(50)                  not null comment '姓名',
    status   tinyint unsigned default '0' null comment '状态 0-正常 1-禁用',
    constraint username unique (username)
) comment '员工表' row_format = DYNAMIC;

-- auto-generated definition
create table menu
(
    id          bigint unsigned primary key auto_increment comment '菜单ID',
    parent_id   bigint unsigned            null comment '父菜单ID（支持多级菜单）',
    menu_name   varchar(50) default 'NULL' not null comment '菜单名称',
    path        varchar(255)               null comment '路由地址',
    component   varchar(255)               null comment '组件路径',
    visible     tinyint     default 0      null comment '菜单状态(0显示 1隐藏)',
    status      tinyint     default 0      null comment '菜单状态(0正常 1禁用)',
    perms       varchar(100)               null comment '权限标识( 如user:read )',
    icon        varchar(100)               null comment '菜单图标',
    order_num   tinyint                    null comment '显示顺序',
    type        char                       null comment '菜单类型（''M''-菜单 ''B''-按钮）',
    create_time datetime                   null comment '创建时间',
    create_user bigint                     null comment '创建者ID',
    update_time datetime                   null comment '更新时间',
    update_user bigint                     null comment '更新者ID'
) comment '菜单表' row_format = DYNAMIC;

-- auto-generated definition
create table role
(
    id          bigint unsigned primary key auto_increment comment '主键ID',
    name        varchar(50)       null comment '角色名称',
    role_key    varchar(50)       null comment '角色权限标识（如ADMIN）',
    description varchar(255)      null comment '角色描述',
    status      tinyint default 0 null comment '角色状态(0正常 1停用)',
    create_time datetime          null comment '创建时间',
    create_user bigint            null comment '创建者ID',
    update_time datetime          null comment '更新时间',
    update_user bigint            null comment '更新者ID'
) comment '角色表' row_format = DYNAMIC;

-- auto-generated definition
create table emp_role
(
    emp_id  bigint unsigned auto_increment comment '员工id',
    role_id bigint unsigned default '0' not null comment '角色id',
    primary key (emp_id, role_id)
) row_format = DYNAMIC;


-- auto-generated definition
create table role_menu
(
    role_id bigint unsigned auto_increment comment '角色ID',
    menu_id bigint unsigned default '0' not null comment '菜单id',
    primary key (role_id, menu_id)
) row_format = DYNAMIC;
