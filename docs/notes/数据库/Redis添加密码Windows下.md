在redis的安装目录下找到 redis.windows-service.conf 文件。用文本编辑器打开,
找到requirepass所在行,回车另起一行,输入requirepass 你的密码,
```
# requirepass foobared
requirepass yourpassword  //此处注意，行前不能有空格
```
这样就设置了redis的密码,设置好保存后,若要使设置起作用,需要重启redis服务。

由于没有找到windows cmd窗口下重启redis服务的命令,所以使用的是windows服务来重启。

找到redis所在的目录，将redis注册为服务
```
redis-server.exe --service-install redis.windows.conf 
```

windows+R打开运行，在运行中输入
```
services.msc
```
打开windows下的服务

重启redis服务，这时才能生效。

这里还有个坑
```
redis-cli.exe -h 127.0.0.1 -p 6379 -a 密码
```
无论密码正确与否，这个命令都是可以进入redis的，但是如果密码错误，命令是不会执行的。

像这样
```
127.0.0.1:6379> keys *
(error) NOAUTH Authentication required.
127.0.0.1:6379>
```
只有密码正确，指令才能执行。
