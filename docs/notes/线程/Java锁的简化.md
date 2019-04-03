## java使用<font color=red>单独的锁对象</font>的代码展示
```java
private Lock bankLock = new ReentrantLock();
//因为sufficientFunds是锁创建的条件所以称其为条件对象也叫条件变量。
private Condition sufficientFunds = bankLock.newCondition();

public void transfer(int from, int to, double amount) throws InterruptedException
{
    bankLock.lock();
    try
    {
        while (accounts[from] < amount)
        sufficientFunds.await();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        sufficientFunds.signalAll();
    }
    finally
    {
        bankLock.unlock();
    }
}
```
假如线程1已经走到`bankLock.lock()`,就获得了这把锁，别的线程再来就会被阻塞。

然后假如线程1满足`while (accounts[from] < amount)`条件就会进入这把锁的等待集，此时线程1的状态就是<font color=red>等待状态</font>，并且释放这把锁。

如果此时别的线程获得这把锁并且走到了`sufficientFunds.signalAll();`,那么处于等待状态的线程将全部转为<font color=red>阻塞状态</font>,而不是在原来的位置接着执行。也就是说将重新争夺这把锁。

## 使用内部锁
内部对象锁只有<font color=red>一个相关条件。</font> 

`wait`方法添加一个线程到等待集中,`notifyAll`,`notify`方法解除等待线程的阻塞状态(也就是从锁的等待集释放).

换句话说,调用 wait 或 notityAll等价于
* `intrinsicCondition.await();`
* `intrinsicCondition.signalAll();`

此时代码简化为:
```java
public synchronized void transfer(int from, int to, double amount) throws InterruptedException
{
    while (accounts[from] < amount)
        wait();
    System.out.print(Thread.currentThread());
    accounts[from] -= amount;
    System.out.printf(" %10.2f from %d to %d", amount, from, to);
    accounts[to] += amount;
    System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    notifyAll();
}
```