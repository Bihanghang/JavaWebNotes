# 异步
等待它的同时你也可以做点别的事情

# 阻塞I/O
只能同时处理一个连接，要管理多个并发客户端，需要为每个新的客户端Socket创建一个新的Thread

# 使用Selector的非阻塞I/O
`class java.nio.channels.Selector`是Java的非阻塞I/O实现的关键。它使用了**事件通知API**以确定在一组非阻塞套接字中有哪些已经就绪能够进行I/O相关的操作。
因为可以在任何的时间检查任意的读操作或者写操作的完成状态，一个单一的线程便可以处理多个并发的连接。

# Channel
Channel 是 Java NIO 的一个基本构造。
> 它代表一个到实体（如一个硬件设备、一个文件、一个网络套接字或者一个能够执行一个或者多个不同的I/O操作的程序组件）的开放连接，如读操作和写操作

目前，可以把 Channel 看作是传入（入站）或者传出（出站）数据的载体。因此，它可以v被打开或者被关闭，连接或者断开连接。

# Future
`Future`提供了另一种在操作完成时通知应用程序的方式。这个对象可以看作是一个异步操
作的结果的占位符；它将在未来的某个时刻完成，并提供对其结果的访问。<br>
`ChannelFuture`提供了几种额外的方法，这些方法使得我们能够注册一个或者多个
`ChannelFutureListener`实例。监听器的回调方法`operationComplete()`，将会在对应的
操作完成时被调用。<br>
简而言之，由`ChannelFutureListene`r提供的通知机制消除了手动检查对应的操作是否完成的必要。每个 Netty 的出站 I/O 操作都将返回一个 ChannelFuture；也就是说，它们都不会阻塞。正如我们前面所提到过的一样，Netty 完全是异步和事件驱动的。



# Channel的注冊
在 Netty 中, 每个 Channel 都会关联一个特定的 EventLoop, 并且这个 Channel 中的所有 IO 操作都是在这个 EventLoop 中执行的; 
Note that this design, in which the  I/O for a given  Channel is executed by the sameThread , virtually eliminates the need for synchronization.

# bossGroup
bossGroup 是用于服务端 的 accept 的, 即用于处理客户端的连接请求<br>
the creator of Netty says multiple boss threads are useful if we share NioEventLoopGroup between different server bootstraps,but I don't see the reason for it.也就是说bossGroup设置为1就可以了，设置多了也没用。

# workerGroup
workerGroup 负责客户端连接通道的 IO 操作

## pipeline
Each channel has its own pipeline and it is created automatically when a new channel is created.
具体流程是当Channel建立时，ChannelPipeline就自动建立在Channel之中了。

### ChannelPipeline's direction
Netty distinguishes implementations of  ChannelInboundHandler and  ChannelOutboundHandler and ensures that data is passed only between handlers of the same directional type.
Netty能够区别Channel进入和出去处理器的实现类，并且保证数据只有在处理器是在同一个方向时才会传递。

## 疑问
> ■ A  Channel is registered for its lifetime with a single  EventLoop .<br>
  ■ A single  EventLoop may be assigned to one or more  Channels.<br>

这两句话意思是一个Channel可以将自己注册给单独的一个EventLoop,一个EventLoop可以指派给多个Channel，也就是说，一个EventLoop可以管理多个Channel。然后下面又说
> Note that this design, in which the  I/O for a given  Channel is executed by the sameThread , virtually eliminates the need for synchronization.

这句话是说Chanel是在同一个线程中执行的，所以实质上消除了同步的需求。那么EventLoop是怎么做到控制多个Channel，并且让每一个Channel都在单独的线程之中的?莫非EventLoop是个线程池？但是书上又说
> ■ An  EventLoop is bound to a single  Thread for its lifetime.

EventLoop是单线程的。

