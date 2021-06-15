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
```

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
