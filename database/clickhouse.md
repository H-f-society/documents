# [Home](../README.md)
# [clickhouse](https://clickhouse.tech/docs/zh/)
![clickhouse](../images/clickhouse1.webp)
![clickhouse](../images/clickhouse.webp)
## 配置host
```shell
	vim /etc/hosts
	192.168.56.* clickhouse1
	192.168.56.* clickhouse2
```
## 安装
```shell
	rpm -ivh *.rpm
```
## 配置
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
## 启动、停止、重启
```shell
	/etc/init.d/clickhouse-server start/stop/restart
```

# clickhouse常用函数
	rand() 生产随机数
	toString() 转字符串
	toInt8(), toInt16(), toInt32(), toInt64() 转整数
	substring() 字符串截取
