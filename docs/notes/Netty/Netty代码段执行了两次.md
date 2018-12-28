# Netty接收到一个请求但是代码段执行了两次
这是因为`HttpRequestDecoder`把请求拆分成`HttpRequest`和`HttpContent`两部分,<br>
所以在建立连接的时候建立了两次。