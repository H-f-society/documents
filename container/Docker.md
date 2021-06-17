# [Home](../README.md)
## 查看docker网络类型
```shell
	docker network ls

	docker network create
	docker network connect
	docker network rm
	docker network disconnect
	docker network inspect

	bridge:网络桥接
	默认情况下启动、创建容器都是用该模式，所以每次docker容器重启时会按照顺序获取对应ip地址，这就导致容器每次重启，ip都发生变化
	none：无指定网络
	启动容器时，可以通过–network=none,docker容器不会分配局域网ip
	host：主机网络
	docker容器的网络会附属在主机上，两者是互通的。
```
## 创建容器
```shell
	docker run -it ubuntu /bin/bash
```
## 进入容器
```shell
	docker attach 44fc0f0582d9
```
## 创建自定义网络类型，并指定网段
```shell
	docker network create --subnet=192.168.0.0/16 statinet
```
## 使用新的网络类型创建并启动容器
```shell
	docker run -it --name dockerName --net statinet --ip 192.168.0.2 ubuntu /bin/bash
```
## 查看所有docker容器：停止、启动、重启、删除
```shell
	docker ps -a
	docker stop $(docker ps -aq)
	docker start $(docker ps -aq)
	docker restart $(docker ps -aq)
	docker rm $(docker ps -aq)


	docker network create --subnet=192.168.1.0/24 statinet
	docker run -it --name demo2 --net statinet --ip 192.168.1.2 ubuntu /bin/bash
	docker run -it --name demo3 --net statinet --ip 192.168.1.3 ubuntu /bin/bash
	docker run -it --name demo4 --net statinet --ip 192.168.1.4 ubuntu /bin/bash

	docker run -it --name demo5 --net statinet --ip 192.168.1.5 zookeeper /bin/bash
	docker run -it --name demo6 --net statinet --ip 192.168.1.6 zookeeper /bin/bash
	docker run -it --name demo7 --net statinet --ip 192.168.1.7 zookeeper /bin/bash
```
