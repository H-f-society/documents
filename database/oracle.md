# [Home](../README.md)

### centos7 install oracle
```shell
# 关闭防火墙
# 操作用户 root
systemctl stop firewalld.service
systemctl disable firewalld.service

# 安装依赖
yum install -y automake autotools-dev binutils bzip2 elfutils expat \
   gawk gcc gcc-multilib g++-multilib lib32ncurses5 lib32z1 \
   ksh less lib32z1 libaio1 libaio-dev libc6-dev libc6-dev-i386 \
   libc6-i386 libelf-dev libltdl-dev libodbcinstq4-1 libodbcinstq4-1:i386 \
   libpth-dev libpthread-stubs0-dev libstdc++5 make openssh-server rlwrap \
   rpm sysstat unixodbc unixodbc-dev unzip x11-utils zlibc unzip cifs-utils \
   libXext.x86_64  glibc.i686
#或者
yum -y install xz wget gcc-c++ ncurses ncurses-devel \
   cmake make perl openssl openssl-devel gcc* libxml2 \
   libxml2-devel curl-devel libjpeg* libpng* freetype* \
   make gcc-c++ cmake bison perl perl-devel  perl perl-devel \
   glibc-devel.i686 glibc-devel libaio readline-devel \
   zlib.x86_64 zlib-devel.x86_64 libcurl-* net-tool*  \
   sysstat lrzsz dos2unix telnet.x86_64 iotop unzip \
   ftp.x86_64 xfs* expect vim psmisc openssh-client* \
   libaio bzip2  epel-release automake binutils bzip2 \
   elfutils expat gawk gcc  ksh less make openssh-server \
   rpm sysstat unzip unzip cifs-utils libXext.x86_64  \
   glibc.i686 binutils compat-libstdc++-33 \
   elfutils-libelf elfutils-libelf-devel \
   expat gcc gcc-c++ glibc glibc-common \
   glibc-devel glibc-headers libaio \
   libaio-devel libgcc libstdc++ libstdc++-devel \
   make sysstat unixODBC unixODBC-devel libnsl

# 创建oracle用户
groupadd -g 502 oinstall
groupadd -g 503 dba
groupadd -g 504 oper
groupadd -g 505 asmadmin
useradd -u 502 -g oinstall -G oinstall,dba,asmadmin,oper -s /bin/bash -m oracle
passwd oracle

# 解压安装包
unzip linux.x64_11gR2_database_1of2.zip
unzip linux.x64_11gR2_database_2of2.zip

# 修改操作系统配置，末尾添加
oracle          soft      nproc   2047
oracle          hard      nproc   16384
oracle          soft      nofile  1024
oracle          hard      nofile  65536
oracle          soft      stack   10240

## 启动oracle
# 1. 切换用户 
    su - oracle #使用户环境变量生效
# 2. 检查监听
    lsnrctl  status # 查看状态
    lsnrctl  start   #启动监听
# 3. 登录SqlPlus
    # sqlplus / as sysdba
    sqlplus /nolog
# 4. 登录数据库
    conn  / as sysdba
# 5. 启动数据库
    startup

## oracle sys密码的重置：
alter user sys identified by change_on_install;
alter user sys account unlock;

## oracle有三个默认的用户名和密码~
- 用户名: sys 密码: change_on_install
- 用户名: system 密码: manager
- 用户名: scott 密码: tiger
```

### CRUD
```sql
select * from tbName;

update tbName set a='b' where c = 'd';

delete from tbName where a='b';

drop table tbName;

truncate table tbName;

create table tbname2 as select * from tbname1;

-- 插入多条数
-- insert all into并不表示一个表中插入多条记录，而是表示多表插入各一条记录
-- 而这多表可以是同一个表，就成了单表插入多条记录。根据后面子查询的结果，前面每条into语句执行一次，其中value都是“字面量”
-- 所以用select 1 from dual返回一条记录即可。
INSERT ALL 
INTO my_table(field_1,field_2) VALUES (value_1,value_2) 
INTO my_table(field_1,field_2) VALUES (value_3,value_4) 
INTO my_table(field_1,field_2) VALUES (value_5,value_6)
SELECT 1 FROM DUAL;

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
### 分页查询
> 取 800 到 1000 行的数

```sql
   select * from (
      select a.*, rownum as rno from tbName a where rownum <= 1000
   ) b
   where b.rno > 800
```
### 递归查询
> 通过根节点id向下递归所有子节点

```sql
   select * from dbName.tbName
   connect by prior id = parentId
   start with id = '000000'
```

### 存储过程
```sql
create [or replace] procedure 存储过程名（param1 in type，param2 out type）
as
变量1 类型 (值范围);
变量2 类型 (值范围);
begin
    select count(*) into 变量1 from 表a where列名 = param1;

    if (判断条件) then
       select 列名 into 变量2 from 表a where列名 = param1;
       dbms_output.put_line('打印信息');
    elsif (判断条件) then
       dbms_output.put_line('打印信息');
    else
       raise 异常名 (no_data_found);
    end if;

exception
    when others then
       rollback;
end;
```

### 存储函数

### declare
```sql
declare
    type ArrayList is varray(10) of varchar2(255);
    v_index number := 0;
    v_array ArrayList := ArrayList('a', 'b');
begin
    for rst in (
        select a, b, c from db.table
    ) loop
        if rst.a = 'A' then
            insert into ...;
        elsif rst.b = 'B' then
            insert into ...;
        else 
            insert into ...;
            for i in 1..v_array.count loop
                insert into ...;
            end loop;
        end if;
        dbms_output.put_line('hello world');
    end loop;
end;
/
```

### 常用函数
```sql
   chr(9)  -- 制表符 
   chr(10) -- 换行符
   chr(13) -- 回车符
   ceil(0.1) -- 向上取整
   floor(0.1) -- 向下取整
   trunc(-1.002) -- 截取
   round(-1.001) --四舍五入取整
```