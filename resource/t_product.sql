drop table if exists t_product;

/*==============================================================*/
/* Table: t_product                                             */
/*==============================================================*/
create table t_product
(
   id                   bigint not null,
   name                 varchar(255),
   price                varchar(10),
   create_time          date,
   primary key (id)
);

alter table t_product comment '����һ�Ŵ洢��Ʒ��Ϣ�ı�';
