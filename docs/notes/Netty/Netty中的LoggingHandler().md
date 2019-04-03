当添加`.addLast("logging", new LoggingHandler(LogLevel.INFO))`这行代码时<br>
Netty就会以给定的日志级别打印出`LoggingHandler`中的日志。<br>
可以对入站\出站事件进行日志记录，从而方便我们进行问题排查。<br>
假如现在添加这行代码访问http://127.0.0.1:8007/Action?name=1234510
```java
19:10:52.089 [nioEventLoopGroup-2-6] INFO io.netty.handler.logging.LoggingHandler - [id: 0x4a9db561, L:/127.0.0.1:8007 - R:/127.0.0.1:53151] REGISTERED
19:10:52.089 [nioEventLoopGroup-2-6] INFO io.netty.handler.logging.LoggingHandler - [id: 0x4a9db561, L:/127.0.0.1:8007 - R:/127.0.0.1:53151] ACTIVE
19:10:52.090 [nioEventLoopGroup-2-6] DEBUG com.bihang.seaya.server.handler.SeayaHandler - io.netty.handler.codec.http.DefaultHttpRequest
19:10:52.090 [nioEventLoopGroup-2-6] DEBUG com.bihang.seaya.server.handler.SeayaHandler - uri/Action?name=1234510
19:10:52.090 [nioEventLoopGroup-2-6] INFO io.netty.handler.logging.LoggingHandler - [id: 0x4a9db561, L:/127.0.0.1:8007 - R:/127.0.0.1:53151] CLOSE
19:10:52.090 [nioEventLoopGroup-2-6] INFO io.netty.handler.logging.LoggingHandler - [id: 0x4a9db561, L:/127.0.0.1:8007 ! R:/127.0.0.1:53151] INACTIVE
19:10:52.090 [nioEventLoopGroup-2-6] INFO io.netty.handler.logging.LoggingHandler - [id: 0x4a9db561, L:/127.0.0.1:8007 ! R:/127.0.0.1:53151] UNREGISTERED
```
如果没有这行代码的打印信息
```java
19:15:02.292 [nioEventLoopGroup-2-2] DEBUG com.bihang.seaya.server.handler.SeayaHandler - io.netty.handler.codec.http.DefaultHttpRequest
19:15:02.292 [nioEventLoopGroup-2-2] DEBUG com.bihang.seaya.server.handler.SeayaHandler - uri/Action?name=1234510
```