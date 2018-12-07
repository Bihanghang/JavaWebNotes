1.查看本机的docker images
```
docker images
```
2.以root的身份登录到docker容器

    * docker run - 运行一个容器
    * -t - 分配一个（伪） tty
    * -i - 开发输入(so we can interact with it)
    * ubuntu - 使用ubuntu基础镜像
    * /bin/bash - 运行bash shell

```
docker run -t -i ubuntu /bin/bash
```

3.退出docker容器

```
ctrl+d or type exit
```

4.列出在容器的文件系统更改的文件和目录。此命令将列出三类事件：

    * A – Add
    * D – Delete
    * C – Change

```
docker diff CONTAINER ID
```

5.查看所有容器(包括挂掉的)

```
docker ps -a
```

6.查看下容器的日志

```
docker logs CONTAINER ID
```

