drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null auto_increment comment '����',
   name                 varchar(255) not null comment '�û���',
   password             varchar(255) not null comment '����',
   realname             varchar(255) comment '��ʵ����',
   gender               char(1) comment '�Ա�',
   tel                  char(11) comment '�绰',
   primary key (id)
);

alter table t_user comment '����һ�Ŵ洢�û���Ϣ�ı�';
