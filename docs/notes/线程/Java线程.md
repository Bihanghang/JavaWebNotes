# 线程中的中断
**interrupt方法**

当对一个线程调用`interrupt`方法时,线程被中断。<br> 
**islnterrupted方法**

调用静态的`Thread.currentThread`方法获得当前线程，然后调用`islnterrupted`方法判断当前线程是否中断.<br>
调用这个方法不会改变中断状态<br>
但是，如果线程被阻塞就无法检测中断状态.这是产生`Interrupted Exception`异常的地方.<br>
当在一个被阻塞的线程(调用`sleep`或`wait`)上调用`interrupt`方法时，<br> 
阻塞调用将会被`Interrupted Exception`异常中断 。<br>
**Interrupted方法**

`Interrupted`方法是一个静态方法，它检测当前的线程是否被中断.<br>
而且,调用`interrupted`方法会清除该线程的中断状态

# volatile
假设对共享变量除了赋值之外并不完成其他操作 ， 那么可以将这些**共享变量**声明为`volatile`.

# 线程的生命周期六个状态

* New(新创建)
* Runnable(可运行)
* Blocked(被阻塞)
* Waiting(等待)
* Timed Waiting(计时等待)
* Terminated(被终止)

**Runnable(可运行)**

记住，在任何给定时刻，二个可运行的线程可能正在运行也可能没有运行（这就是为什么将这个状态称为可运行而不是运行)

**Waiting(等待)**

当线程等待另一个线程通知调度器一个条件时
， 它自己进入等待状态 。 在调用 Object . wait 方法或 Thread . join 方法
， 或者是等待 java ,
util . concurrent 库中的 Lock 或 Condition 时 ， 就会出现这种情况
。

**Terminated(被终止)**

线程因如下两个原因之一而被终止 ：
  * 因为`run`方法正常退出而自然死亡 。
  * 因为一个没有捕获的异常终止了`run`方法而意外死亡 。

# 线程的3种创建方式

1.继承Thread类创建线程

2.实现Runnable接口创建线程
```java
new Thread(()->{
		System.out.println("hello");
	}).start();
```

3.使用Callable和Future创建线程

# 阻塞队列
当试图向队列添加元素而队列已满，或是想从队列移出元素而队列为空的时候，阻塞队
列(blocking queue)导致线程阻塞.此队列是线程安全的集合

# FutureTask
**FutureTask**包装器是一种非常便利的机制,可将**Callable**转换成**Future**和**Runnable**,它同时实现二者的接口.例如 ：
```java
Callable<Integer> myComputation = . . . ;
FutureTask<Integer> task = new FutureTask < Integer > ( myConiputation ) ;
Thread t = new Thread(task); // it ' s a Runnable
t.start();
Integer result = task.get() ； // it ' s a Future
```
# 线程池
ScheduledFuture<?> scheduleAtFixedRate ( Runnable task , long
initialDel ay , long period , TimeUnit unit )
预定在初始的延迟结束后 ， **周期性地**运行给定的任务 而不管任务是否完成，周期长度是period。

ScheduledFuture < ? > scheduleWithFixedDel ay ( Runnable task , long
initial Del ay , long delay , TimeUnit unit )
预定在初始的延迟结束后周期性地运行给定的任务 ， 在一次**调用完成**和下一次**调用开始**之间有长度为 delay 的延迟。