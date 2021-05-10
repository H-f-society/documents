# [Home](../README.md)

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