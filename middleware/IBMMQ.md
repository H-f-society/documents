# [Home](../README.md)
[toc]

# 下载网址
- http://public.dhe.ibm.com/ibmdl/export/pub/software/websphere/messaging/mqadv/
- https://www-01.ibm.com/marketing/iwm/iwm/web/download.do?S_PKG=CRZC5ML&source=ESD-WSMQ-EVAL&transactionid=448604060&pageType=urx  
试用版：https://www.ibm.com/support/knowledgecenter/en/SSFKSJ/com.ibm.mq.helphome.doc/mq_downloads_admins.htm



# liunx下安装
参考资料：https://blog.csdn.net/dataiyangu/article/details/82808450
          http://www.desenpast.com/middleware-4481.html

官网资料：https://www.ibm.com/developerworks/cn/linux/linux-speed-start/l-ss-mq/#code9

## 解压
```
tar –xzvf IBM_MQ_8.0.0-4_LINUX_X86-64_TRIAL.tar.gz
```

## 进入MQServer文件夹中
```
cd MQServer
```
## 运行MQ许可证程序
```
./mqlicense.sh
```
出现的界面，输入1回车同意协议
```

Licensed Materials - Property of IBM

 5724-H72

 (C) Copyright IBM Corporation 1993, 2018

US Government Users Restricted Rights - Use, duplication or disclosure
restricted by GSA ADP Schedule Contract with IBM Corp.




 
WARNING: Unable to determine distribution and release for this system. 
         Check that it is supported before continuing with installation.
 

声明

本文档在下面包含针对多个程序的许可信息文档。每份许可信息文档都针对
其适用的程序。只有被许可方已获取权力的程序的那些许可信息文档适用。



==============================================


重要信息：请仔细阅读

下面提供了两个许可协议。


按 Enter 键继续查看本许可协议或按 "1" 接受本协议，按 
"2" 拒绝本协议，按 "3" 打印本协议，按 "4" 阅读非 
IBM 条款，按 "5" 用英语查看，或按 "99" 回到上一屏
幕
```
## 安装WebSphere MQ for Linux 服务器
```
rpm -U MQSeriesRuntime-9.1.0-0.x86_64.rpm
rpm -U MQSeriesSDK-9.1.0-0.x86_64.rpm
rpm -U MQSeriesServer-9.1.0-0.x86_64.rpm
```

## 安装 WebSphere MQ for Linux 客户机 
```
rpm -U MQSeriesClient-9.1.0-0.x86_64.rpm 
```

## 安装 WebSphere MQ 样本程序:
```
rpm -U MQSeriesSamples-9.1.0-0.x86_64.rpm
```

## 修改mqm密码
安装过程创建了一个名为 mqm 的用户和一个同样名为 mqm 的组。此时，新用户是被锁定的，您必须设置一个密码来解锁
```
passwd mqm
```
## 新建用户管理mqm
这一步非必须
```
#新增admin用户
useradd admin
#修改密码
passwd amdin
#查看mqm用户组
id mqm
#修改admin用户组，1000为mqm的用户组
usermod -g 1000 admin
#刷新mq安全缓存
su mqm
runmqsc QMNMAME
# mqsc 中执行命令
refresh security(*)
```

# ubuntu下安装
## 解压
```
tar –xzvf IBM_MQ_9.1.0.4_UBUNTU_X86-64.tar.gz
```

## 进入MQServer文件夹中
```
cd MQServer
```
## 运行MQ许可证程序
```
./mqlicense.sh -text_only
```

## 新增apt源并开始安装
```
cd /etc/apt/source.list.d
# ibmmq-install.list可以自定义
vi ibmmq-install.list
```
添加内容
```
# /usr/local/software/MQServer 为解压之后的路径
deb [trusted=yes] file:/usr/local/software/MQServer ./
```

## 更新apt源
```
apt update
```
## 安装ibmmq
```
apt install "ibmmq-*"
```
> 自动创建mqm用户和mqm组

## 配置mqm用户配置
```
vi /etc/passwd
```
修改后的内容为：
```
mqm:x:998:1001::/var/mqm:/bin/bash
```

## 修改mqm密码
安装过程创建了一个名为 mqm 的用户和一个同样名为 mqm 的组。此时，新用户是被锁定的，您必须设置一个密码来解锁
```
passwd mqm
```

# 验证安装
```
查看配置
su - mqm
/opt/mqm/bin/mqconfig
```

# 安装正式版License
```
-bash-4.2$ /opt/mqm/bin/setmqprd ~/licenses/amqpcert.lic
```

# 队列管理器
## 创建队列管理器
```
crtmqm -q QM1
```

## 开启队列管理器
```
strmqm QM1
```
## 查询队列管理器名
```
dspmq
dspmq -m MQCHNMSCS01
```

## 进入mq命令行
```
# 进入缺省的队列管理器
runmqsc
# 指定队列管理器的应用管理程序
runmqsc QM_ZJJC_A1000146000208_01
```

## 停止队列管理器
```
endmqm QM1
```

## 删除队列管理器
```
dltmqm QM1
```

# 队列
## 创建死信队列
参考资料：https://blog.csdn.net/qq_31072669/article/details/88101315  

为什么要定义死信队列。
例如当一条消息到达目的队列管理器之后却发现目的队列并不存在。或者目的队列出现不能接收信消息的情况，比如目的队列已经满了，或者它被设置成不允许再加入新的消息。此时需要把消息放入死信队列。

如果没有MQ队列管理器没有定义死信队列，报文无法放入死信队列，则会导致发送方通道无法启动，错误消息滞留在传输队列中。

死信（未传递的消息）队列是存储无法发送到其正确目的地的消息的队列。有时候会出现队列管理器不能把消息发送到目的地的情况，此时消息将被发送到某个死信队列中。死信队列中的消息常常暗示了系统可能出现的问题。例如当一条消息到达目的队列管理器之后却发现目的队列并不存在。或者目的队列出现不能接收信消息的情况，比如目的队列已经满了，或者它被设置成不允许再加入新的消息。并不是所有的放消息操作的失败都导致消息被放入死信队列，例如，由于本地队列出现错误造成应用程序不能“放”消息，此时MQI调用直接把错误码返回给应用程序。

有些错误只能由死信队列报告，例如，一条消息穿越网络之后到达目的队列管理器，却发现目的队列已满。发现错误的机器不同于最初“放”消息应用程序所在的机器，甚至可能放消息的应用程序已不在运行状态。此时目的队列管理器把这条消息发往它所拥有的死信队列，而不是简单地扔掉该条消息。这样使得这次错误是可见的，也给应用程序提供了一个改正错误的机会。

死信队列是WebSphere MQ面对远端系统错误时的一种解决方案。应用程序可以利用WebSphere MQ提供的其他一些工具来监视并确保消息的可靠传送和接收。

在队列管理器创建时，系统会缺省创建一个死信队列，队列名是SYSTEM.DEAD.LETTER.QUEUE。建议在生产系统上，需要独立创建一个死信队列，而不使用系统缺省的死信队列。

```
#定义死信队列，DEFPSIST用于设置队列中消息持久性默认值，NO:队列管理重启时丢失，YES:队列管理器重启时保存了下来。
DEFINE QLOCAL(DEAD.QUEUE) DEFPSIST(YES) REPLACE
设置死信队列
ALTER QMGR DEADQ(DEAD.QUEUE)
```
>关于消息在队列中的保存时间：消息在队列的保存时间与个设置关：队列defpsist属性、消息Persistence持久性属性和消息Expiry消息到期时间属性，其中队列defpsist 属性是在创建队列时设置，消息Persistence和Expiry属性是应用程序往队列放入消息时指定。消息本身的Persistence值优先于队列defpsist值。Expiry指消息到期 时间，即经过指定的时间后，消息如果还没被取走，此消息将过期（无效。消息过期后，可能会自动从队列中删除（取决于不同操作系统的MQ实现。对于非持久性消息， 即使Expiry设为永不过期，重启队列管理器时，消息也将丢失

## 创建队列
参考资料：https://www.ibm.com/support/knowledgecenter/zh/SSFKSJ_8.0.0/com.ibm.mq.explorer.doc/e_properties_queues.htm
```
# 定义队列，maxdepth为队列深度，可以自定义，范围：0-999999999,descr:可以设置描述，最大长度为43。
def ql(Q1) maxdepth(10)
# 定义队列别名
def qalias(AQ1) targq(Q1)
# 定义与Q1一样参数的队列Q2
def ql(Q2) like(Q1)
# 查看Q1队列的详细信息，Q1可以改为*，表示查看全部队列
dis q(Q1)
# 查看Q1队列的最大深度
dis q(Q1) maxdepth
# 查看Q1队列的现在的深度
dis q(Q1) curdepth
```


# 常用命令
参考资料：https://www.cnblogs.com/siwei1988/p/5923038.html
## 查看版本
```
dspmqver
```

## 启动通道
```
#启动通道：PBC.C1010246000016为工行通道
start chl('PBC.C1010246000016') 
```

## 查看通道状态 
```
#如果是running 正常，如果是Channel Status not found.为未启动通道
dis chs('PBC.C1010246000016')
```

## 查看队列深度
```
dis QLOCAL ('PBC.C1010246000016.BATCH.IN') curdepth
```

## 消费消息 amqsget amqsgetc
```
# 消费队列所有消息，queue_name：队列名，queue_manager_name：队列管理器名
# amqsget 从server端将消息从队列中取出
# amqsgetc 从client端将消息从队列中取出
# amqsget和 amqsgetc可以将消息从队列中全部读出并显示，执行完后队列深度应该为0，如果强行中断该程序，比如用ctrl＋C 强行退出
amqsget queue_name queue_manager_name
amqsgetc queue_name queue_manager_name
```

## 发送消息 amqsput amqsputc
```
# 向队列中放入消息
# amqsput 从server端将消息放入队列
# amqsputc 从client 端将消息放入队列
# amqsget和 amqsgetc可以将消息从队列中全部读出并显示，执行完后队列深度应该为0，如果强行中断该程序，比如用ctrl＋C 强行退出
amqsput queue_name queue_manager_name
amqsputc queue_name queue_manager_name
```

## 查阅消息 amqsbcg amqsbcgc   
```
# 可以详细查阅队列中现有的消息属性及内容而不将其取出，执行完后队列深度不变

```

# 修改队列最大小通道

```
runmqsc  队列管理器名称
 
alter qmgr maxmsgl(10000000)
1 : alter qmgr maxmsgl(10000000)
AMQ8005: WebSphere MQ queue manager changed.
 
alter ql(client.queue.local) maxmsgl(10000000)
2 : alter ql(client.queue.local) maxmsgl(10000000)
AMQ8008: WebSphere MQ queue changed.
 
alter chl(client.sdr) maxmsgl(10000000)
3 : alter chl(client.sdr) chltype(sdr) maxmsgl(10000000)
AMQ8016: WebSphere MQ channel changed.
```

# IBM MQ Explorer添加远程队列管理器
## 启动监听
```
# 定义并开启监听,QM_B.LISTENER为监听名
DEFINE LISTENER(QM_B.LISTENER) TRPTYPE(TCP) CONTROL(QMGR) PORT(1414)
START LISTENER(QM_B.LISTENER)
```

## 定义服务器管理通道
参考网站：https://www.ibm.com/support/knowledgecenter/zh/SSWMAJ_2.0.0/com.ibm.ism.doc/Administering/ad00091_.html

```
DEFINE CHANNEL(SYSTEM.ADMIN.SVRCONN) CHLTYPE(SVRCONN) TRPTYPE(TCP)
```

## 设置权限
https://www.ibm.com/support/pages/node/196563
使用SYSTEM.ADMIN.SVRCONN服务器通道，需要做一下设置
```
SET CHLAUTH(*) TYPE(BLOCKUSER) USERLIST('nobody','*MQADMIN')
SET CHLAUTH(SYSTEM.ADMIN.*) TYPE(BLOCKUSER) USERLIST('nobody')
```
新建MY.ADMIN.SVRCONN服务器通道时，需要做一下设置
```
SET CHLAUTH(MY.ADMIN.SVRCONN) TYPE(ADDRESSMAP) ADDRESS(*) USERSRC(CHANNEL)
SET CHLAUTH(MY.ADMIN.SVRCONN) TYPE(BLOCKUSER) USERLIST('nobody')
```

## 添加远程队列管理器
- 1、队列管理器：写需要连接的队列管理器
- 2、输入ip以及开启的监听器的port
- 3、启动用户标识，输入mqm/mqm

# 队列管理器间进行通信
## 架构图
```
QM_A(队列管理器)          -->           QM_B(队列管理器)  
Q1(远程队列)            -(C通道)->      Q2(本地队列)
QX(传输队列)
```

## 步骤
### 创建、启动 QM_A、QM_B
```
# 创建QM_A，-q指的是缺省队列管理器
crtmqm -q QM_A
strmqm QM_A

# 创建QM_B
crtmqm -q QM_B
strmqm QM_B
```

### 在QM_A创建远程队列、传输队列、通道
```
# 打开QM_A
runmqsc QM_A
# 创建远程队列
#QREMOTE：是在本地QM（QM_ZAVIER）创建的归属于远程QM（QM_ZAVIER2）远程队列定义；
#RNAME：是远程QM（QM_ZAVIER2）中的本地队列；
#RQMNAME：是远程QM名；
#XMITQ：本地队列管理器用来将消息发送至远程队列管理器的传输队列的名称。
#REPLACE：替换已存在的对象
DEFINE QREMOTE(Q1) RNAME(Q1) RQMNAME(QM_B) XMITQ(QX) REPLACE
#定义传输队列
DEFINE QLOCAL(QX) USAGE(XMITQ) REPLACE
#定义传输通道
#通道类型为SDR（sender），协议类型为TCP，连接名为‘127.0.0.1（1414）’IP+端口，采用本地QX队列作为传输队列
DEFINE CHANNEL(C) CHLTYPE(SDR) TRPTYPE(TCP) CONNAME('127.0.0.1(1414)') XMITQ(QX) REPLACE
```

### 在QM_B创建本地队列、通道、定义并开启监听
```
# 打开QM_B
runmqsc QM_B
# 创建本地队列
DEFINE QLOCAL(Q1) REPLACE
# 定义传输通道
# 通道类型为RCVR（receiver），协议TCP
# 其他通道类型：RCVR：Receiver（接受者）、SDR：Sender（发送者）、SVR：server（服务器）、SVRCONN：server-connection（服务器连接）：、CKYSSDR：cluster-sender（集群发送者）、CLUSRCVR：cluster-receiver（集群接受者）、QSTR 、 CLNTCONN、MQTT*
DEFINE CHANNEL(C) CHLTYPE(RCVR) TRPTYPE(TCP) REPLACE
# 定义并开启监听
DEFINE LISTENER(QM_B.LISTENER) TRPTYPE(TCP) CONTROL(QMGR) PORT(1414)
START LISTENER(QM_B.LISTENER)
```

### 在QM_A打开通道
```
runmqsc QM_A
#打开通道
START CHANNEL(C)
```

### 测试发送消息
发送
```
# 回车完成一次输入，结束输入：crtl + d
amqsput Q1 QM_A
```
接收
```
amqsget Q1 QM_B
```

# topic 发布订阅主题
## 架构图
```
QM_A(队列管理器)          -->           QM_B(队列管理器)  
Q1(远程队列)            -(C1通道)->      Q2(本地队列)
QX1(传输队列)

QM_A(队列管理器)          -->           QM_C(队列管理器)  
Q2(远程队列)            -(C2通道)->      Q2(本地队列)
QX2(传输队列)
```

## 配置队列管理器、队列、通道
参照[队列管理器间进行通信](#队列管理器间进行通信)

## 配置主题、订阅
```
# 打开QM_A
runmqsc QM_A
# 定义主题T
DEFINE TOPIC(T) TOPICSTR(T)
# 定义订阅SUB1、SUB2，TOPICOBJ为主题，DEST为目标消息队列
DEFINE SUB(SUB1) TOPICOBJ(T) DEST(Q1)
DEFINE SUB(SUB1) TOPICOBJ(T) DEST(Q1)
```
> 问题一：如果本地队列满了，消息会丢失。
> 问题二：如果通道没有启动，消息也会丢失。
> 问题三：如果设置了死信队列，可以暂存一部分的消息，如果死信队列满了，消息一样会丢失

# 问题解决
1、
```
#队列管理器状态
QMNAME(QM_ZAVIER)                                         STATUS(Ended immediately)

#删除的报错
bash-4.2$ dltmqm  QM_ZAVIER  
AMQ8041S: The queue manager cannot be restarted or deleted because processes,
that were previously connected, are still running.
Process 1973 is still running.
Process 2409 is still running.
AMQ7018E: The queue manager operation cannot be completed.
```
解决方法：
进程正在运行，没法删除
```
kill -9 1973
kill -9 2409
```

2、
```
#队列管理器状态
QMNAME(QM_ZAVIER2)                                        STATUS(Quiescing)

#删除的报错
bash-4.2$ dltmqm  QM_ZAVIER2
IBM MQ queue manager 'QM_ZAVIER2' ending.
```
解决方法：
使用-i参数立即停止队列管理器
```
endmqm -i QM_ZAVIER2
```
之后按照1的解决方法执行

3、 explorer添加远程队列管理器报错
```
不允许访问。您无权执行此操作。(AMQ4036)
  不允许访问。您无权执行此操作。(AMQ4036)
  严重性：10 (警告)
  说明：队列管理器安全性机制表明与该请求关联的用户标识无权访问此对象。  
```
```
连接至队列管理器““'192.168.0.115(1418)'”上的“QM_ZAVIER2””时发生了错误。确定要在文件夹中显示此队列管理器吗？(AMQ4027)
  连接至队列管理器““'192.168.0.115(1418)'”上的“QM_ZAVIER2””时发生了错误。确定要在文件夹中显示此队列管理器吗？(AMQ4027)
  严重性：10 (警告)
  说明：无法连接至指定的远程队列管理器。
  响应：确保指定的队列管理器正在指定的主机和端口上运行，并具有与指定名称对应的通道。确保您有权连接至远程队列管理器，并确保网络正在运行。如果认为此问题可以在以后解决，那么选择“是”。如果要立刻纠正此问题并再试，请选择“否”。
```
解决方法：
https://www.ibm.com/support/pages/node/196563

4、 explorer添加远程队列管理器报错
```
无法建立与队列管理器的连接 - 原因为 2538。(AMQ4059)
  无法建立与队列管理器的连接 - 原因为 2538。(AMQ4059)
  严重性：10 (警告)
  说明：尝试连接至队列管理器失败。这可能是因为未正确配置此队列管理器以允许来自此系统的连接，或该连接已中断。
  响应：重试此操作。如果错误仍然存在，请检验问题确定信息以查看是否记录了信息。
```
解决方法
- 方法1、关闭防火墙：`systemctl stop firewalld.service`
- 方法2、配置防火墙:   
    centos6:
    ```
    vi /etc/sysconfig/iptables
    #在端口为22的那一行下面添加内容(1414为需要开启的端口):
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 1414 -j ACCEPT`
    #重启防火墙
    ```
    centos:
    ```
    #1414为需要开启的端口
    firwall-cmd --permanent --add-port=1414/tcp
    systemctl restart firewalld
    ```

5、MQJE001: 完成代码为“2”，原因为“2053”
原因：队列满了
解决方法：修改队列深度

6、MQJE001: MQException 出现:完成代码是 2,原因为 2195 MQJE020: 队列管理器不支持 CCSID。

java设置的ccsid会ibmmq的没有对应上

7、2080（0820）（RC2080）：MQRC_TRUNCATED_MSG_FAILED  

https://www.ibm.com/support/knowledgecenter/en/SSFKSJ_8.0.0/com.ibm.mq.tro.doc/q041460_.htm

8、2087 (0827) (RC2087): MQRC_UNKNOWN_REMOTE_Q_MGR  

https://www.ibm.com/support/knowledgecenter/en/SSFKSJ_9.0.0/com.ibm.mq.tro.doc/q041500_.htm

9、2033  
no message