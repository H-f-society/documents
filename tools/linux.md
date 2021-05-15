# [Home](../README.md)
# [Linux]()

### openssl
> 加密(执行后获得加密码)

```shell
echo '明文' | openssl aes-128-cbc -k "key" -base64
```

>解密(加密码太长,用*表示了)##

```shell
echo "U2FsdGVkX1/QCJoMvMVE7aZCt5/RworlmYhaonZV9f4=" | openssl aes-128-cbc -d -k "key" -base64
```