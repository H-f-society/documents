# [Home](../README.md)
## Ubuntu简单安装Kafka
## 安装jdk：
	sudo apt-get install openjdk-8-jdk
## 获取kafka安装包：
	wget http://mirror.bit.edu.cn/apache/kafka/2.3.1/kafka_2.11-2.3.1.tgz
## 解压安装包：
	tar -zxvf kafka_2.11-2.3.1.tgz
## 重命名：
	mv kafka_2.11-2.3.1 kafka
## 设置环境变量：
	vim /etc/profile
	export KAFKA_HOME=/opt/kafka
	export PATH=$PATH:$KAFKA_HOME/bin
## 使环境变量生效:
	source /etc/profile
## 先启动ZooKeeper服务:
	bin/zookeeper-server-start.sh config/zookeeper.properties
## 启动kafka服务:
	bin/kafka-server-start.sh config/server.properties
## 创建一个名为"topTest"的topic:
	bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topTest
## 查看所包含的topic:
	bin/kafka-topics.sh --list --zookeeper localhost:2181
## 打开Producer（生产者）服务
	bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topTest
## 打开Customer（消费者）服务
	bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topTest --from-beginning