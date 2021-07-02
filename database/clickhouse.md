# [Home](../README.md)
# [clickhouse](https://clickhouse.tech/docs/zh/)
![clickhouse](../images/clickhouse1.webp)
![clickhouse](../images/clickhouse.webp)
## 部署clickhouse
### 配置host
```shell
vim /etc/hosts
192.168.56.* clickhouse1
192.168.56.* clickhouse2
```
### 安装
```shell
rpm -ivh *.rpm
```
### 配置
```shell
passwd clichouse
usermod -s /bin/bash clickhouse
mkdir -p /home/clickhouse
chown clickhouse:clickhouse /home/clickhouse
id clickhouse
usermod -d /home/clickhouse
mkdir -p /home/clickhouse/data
mkdir -p /home/clickhouse/log/clickhouse-server
chown -R clickhouse:clickhouse /home/clickhouse
chmod 755 /home/clickhouse
```
```shell
vim /etc/clickhouse-server/config.xml
```
```xml
<cluster_myclickhouse>
    <shard>
        <replica>
            <host>clickhouse1</host>
            <port>9000</port>
        </replica>
    </shard>
    <shard>
        <replica>
            <host>clickhouse2</host>
            <port>9000</port>
        </replica>
    </shard>
</cluster_myclickhouse>
```
### 启动、停止、重启
```shell
/etc/init.d/clickhouse-server start/stop/restart
```
## 基本应用
### CRUD
```sql
select * from dbName.tbName;

alter table dbName.tbName_local update field1='a' where b = 1;

alter table dbName.tbName_local delete where a=1;

insert into dbName.tbName() values();

truncate table dbName.tbName_local;

drop table if exists dbName.tbName_local;
drop table if exists dbName.tbName;
```

### clickhouse-client
```shell
# 导入csv文件数据
clickhouse-client --host ${ip} --port 9000 --format_csv_delimiter="${csvFileName}" \
--query="insert into dbName.tbName (${insertLine}) FORMAT CSVWithNames"

cat file.csv | clickhouse-client --database=test --query="INSERT INTO test FORMAT CSV"

--host, -h 			# 服务端的host名称, 默认是localhost。您可以选择使用host名称或者IPv4或IPv6地址。
--port 				# 连接的端口，默认值：9000。注意HTTP接口以及TCP原生接口使用的是不同端口。
--user, -u 			# 用户名。 默认值：default。
--password 			# 密码。 默认值：空字符串。
--query, -q 		# 使用非交互模式查询。
--database, -d 		# 默认当前操作的数据库. 默认值：服务端默认的配置（默认是default）。
--multiline, -m 	# 如果指定，允许多行语句查询（Enter仅代表换行，不代表查询语句完结）。
--multiquery, -n 	# 如果指定, 允许处理用;号分隔的多个查询，只在非交互模式下生效。
--format, -f 		# 使用指定的默认格式输出结果。
--vertical, -E 		# 如果指定，默认情况下使用垂直格式输出结果。这与–format=Vertical相同。在这种格式中，每个值都在单独的行上打印，这种方式对显示宽表很有帮助。
--time, -t 			# 如果指定，非交互模式下会打印查询执行的时间到stderr中。
--stacktrace 		# 如果指定，如果出现异常，会打印堆栈跟踪信息。
--config-file 		# 配置文件的名称。
--secure 			# 如果指定，将通过安全连接连接到服务器。
--history_file 		# 存放命令历史的文件的路径。
--param_${name} 	# 查询参数配置查询参数.
```
### 建表
```sql
CREATE TABLE IF NOT EXISTS dbName.tbName_local (
	field1	String	comment '',
	field2	String	comment ''
)ENGINE = MERGETREE()
ORDER BY (field1) SETINGS index_granularity = 8192;

-- 建分布式表
CREATE TABLE IF NOT EXISTS dbName.tbName_all as dbName.tbName_local
ENGINE = Distributed(ClusterName, dbName, dbName_local, hiveHash(field1));
```
### 数据类型
- 整型
	- 有符号整型（-2n-1~2n-1-1）：
		- Int8 - [-128 : 127]
		- Int16 - [-32768 : 32767]
		- Int32 - [-2147483648 : 2147483647]
		- Int64 - [-9223372036854775808 : 9223372036854775807]
	- 无符号整型范围（0~2n-1）：
		- UInt8 - [0 : 255]
		- UInt16 - [0 : 65535]
		- UInt32 - [0 : 4294967295]
		- UInt64 - [0 : 18446744073709551615]

- 浮点型
	- Float32 - float
	- Float64 – double

- 布尔型
	- 没有单独的类型来存储布尔值。可以使用 UInt8 类型，取值限制为 0 或 1。

- 字符串
	- 变长字符串 String
	- 定长字符串 FixedString(N)

- 枚举类型
	- Enum8 用 'String'= Int8 对描述。
	- Enum16 用 'String'= Int16 对描述。

- 数据组 Array(T)

- 元组 Tuple(T1, T2, ...)

- 日期  Date

- 时间戳 DateTime

### clickhouse常用函数
```
rand() 生产随机数
toString() 转字符串
toInt8(), toInt16(), toInt32(), toInt64() 转整数
substring() 字符串截取
```