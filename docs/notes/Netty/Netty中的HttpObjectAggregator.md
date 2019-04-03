## Http的Get，POST
Get请求包括两个部分：
* request line(包括method，request uri，protocol version))
* header

基本样式:
```
GET /?name=XXG&age=23 HTTP/1.1       -----> request line
------------------------------------------------------------------
Host: 127.0.0.1:8007
Connection: keep-alive              
Cache-Control: max-age=0             -----> header
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
```
POST请求包括三个部分
* request line(包括method，request uri，protocol version))
* header
* message body

基本样式
```
GET / HTTP/1.1                       -----> request line
------------------------------------------------------------------
Host: 127.0.0.1:8007
Connection: keep-alive  
Content-Length: 15            
Cache-Control: max-age=0             -----> header
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
------------------------------------------------------------------
name=XXG&age=23                     ------>message body
```
## HttpObjectAggregator
从上可以看出，当我们用POST方式请求服务器的时候，对应的参数信息是保存在`message body`中的,如果只是单纯的用`HttpServerCodec`是无法完全的解析Http POST请求的，因为`HttpServerCodec`只能获取uri中参数，所以需要加上`HttpObjectAggregator`.