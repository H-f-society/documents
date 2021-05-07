# [Home](../README.md)
### 查看日志是否开启
```sql
SHOW VARIABLES LIKE 'general%';
```
### 开始日志
```sql
set GLOBAL general_log='ON';
```
### --
```sql
show variables like "%pro%"; 
```

### 修改数据库字符集
```sql
alter database db_name character set utf8;
```

### 查看并修改mysql字符集
```sql
show variables like 'character%';
set character_set_*** = utf8;
```

### 基本操作
```sql
show databases;
show tables;
show create table tbName;

create datanase dbName;
create table tbName();

select * from tbName;
delete from tbName;
update tbName set a=1 where b=2;
insert into tbName(a, b, c) vlaues(1, 2, 3);
```