## 启动jar包

$0：当前Shell程序的文件名
dirname $0，获取当前Shell程序的路径
cd `dirname $0`，进入当前Shell程序的目录
[ -f FILE ] 如果 FILE 存在且是一个普通文件则为真。
```shell
cd `dirname $0`/..
if [[ -f $SERVICE_NAME".jar" ]]; then
  echo "lakjsdflkjdsf"
  chmod a+x $SERVICE_NAME".jar"
  ./$SERVICE_NAME".jar" stop
fi
```

## 从nginx.pid文件读取出nginx的PID，然后关闭nginx。

```shell
PIDFILE="$PWD/nginx-tools/logs/nginx.pid"  # PID文件路径
 
if [ -f $PIDFILE ]; then
    echo "nginx file exists...." 
    PID=$(cat $PIDFILE)           # 将PID从文件中读取，并作为一个变量等价于 read pid <$pidfile
    sudo kill -QUIT $PID
fi
```

## shell 中 [-eq] [-ne] [-gt] [-lt] [ge] [le]

-eq           //等于

-ne           //不等于

-gt            //大于 （greater ）

-lt            //小于  （less）

-ge            //大于等于

-le            //小于等于

curl -X GET --silent --connect-timeout 1 --max-time 2 --head http://localhost:8088 | grep "HTTP"

## linux In命令
ln指令用在连接文件或目录，如同时指定两个以上的文件或目录，且最后的目的地是一个已经存在的目录，则会把前面指定的所有文件或目录复制到该目录中。若同时指定多个文件或目录，且最后的目的地并非是一个已存在的目录，则会出现错误信息。

## 运行jar包
```shell
./pack-web.jar start
```
只要运行此命令，便会自动生成pid文件,文件里面存放具体的pid值
```shell
$ ls -t **/pack-web*.pid
pack-web_epackpack-webtargetpack-web-0.0.1-SNAPSHOT/pack-web_epackpack-webtargetpack-web-0.0.1-SNAPSHOT.pid
```