# [Home](../README.md)

### CRUD
```sql
  select * from tbName;
  update tbName set a='b' where c = 'd';
  delete from tbName where a='b';
  drop table tbName;
  truncate table tbName;
```

### MERGE INTO
> update 和 insert 共存，若存在就更新，不存在就插入

```sql
MERGE INTO tbname a
USING (
	select 
		'${b.c}' as c,
		'${b.d}' as d
	from dual
) b
on (a.c = b.c)
WHEN MATCHED THEN
	update set 
		a.d = b.d
WHEN NOT MATCHED THEN
	insert (a.c, a.d) 
	values (b.c, b.d)
```

### 存储过程
```sql
create [or replace] procedure 存储过程名（param1 in type，param2 out type）
as
变量1 类型（值范围）;
变量2 类型（值范围）;
Begin
    Select count(*) into 变量1 from 表A where列名=param1;

    If (判断条件) then
       Select 列名 into 变量2 from 表A where列名=param1;
       Dbms_output。Put_line(‘打印信息’);
    Elsif (判断条件) then
       Dbms_output。Put_line(‘打印信息’);
    Else
       Raise 异常名（NO_DATA_FOUND）;
    End if;

Exception
    When others then
       Rollback;
End;
```

### 存储函数
