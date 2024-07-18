drop table if exists t_act;

create table t_act(
 actno char(7) primary key,
 balance decimal(10, 2)
);

insert into t_act(actno, balance) values('act-001', 50000.0);
insert into t_act(actno, balance) values('act-002', 0.0);