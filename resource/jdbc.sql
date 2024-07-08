drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(255) not null comment '用户名',
   password             varchar(255) not null comment '密码',
   realname             varchar(255) comment '真实姓名',
   gender               char(1) comment '性别',
   tel                  char(11) comment '电话',
   primary key (id)
);

alter table t_user comment '这是一张存储用户信息的表';
