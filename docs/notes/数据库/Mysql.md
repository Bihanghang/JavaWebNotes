## Mysql的下载安装
[跳转一下]](https://blog.csdn.net/qq_36868342/article/details/78816740)<br>
老版本的MySql不需要Visual C++的支持,新版本的需要，可以选择老版本简单一些。

## Mysql修改密码
**1**.找到`C:\Windows\System32`目录下的**cmd.exe**右击以管理员身份打开，输入`net stop mysql`来关闭服务;
**2**.找到MySql的目录输入`mysqld --skip-grant-tables`以跳过验证。
**3**.开另外一个命令行窗口，一样进入那个目录
```sql
mysql -u root -p
update mysql.user set password=PASSWORD("新密码") where User="root";
flush privileges;
```
**4**.同第一步,输入`net start mysql`

## 安装Mysql报错**此用户已存在!**
我是安装了两个`installer`下载器，将其中一个删除运行另一个就会报这个错。<br>
解决办法:将删除的那个`installer`从回收站还原。

## limit与offset用法比较

```
SELECT  
    keyword  
FROM  
    keyword_rank  
WHERE  
    advertiserid='59'  
order by  
    keyword  
LIMIT 2 OFFSET 1; 
        该用法，limit后面跟的是2条数据，offset后面是从第1条开始读取
******************************************
SELECT  
    keyword  
FROM  
    keyword_rank  
WHERE  
    advertiserid='59'  
ORDER BY  
    keyword  
LIMIT 2 ,1;  
        该用法，limit后面是从第2条开始读，读取1条信息。

注意：Mysql中计数也是从0开始读数的。

select * from table limit 5; --返回前5行

select * from table limit 0,5; --同上，返回前5行
```

## 优化Limit的用法

当一个查询语句偏移量offset很大的时候，如select * from table limit 10000,10 , 最好不要直接使用limit，而是先获取到offset的id后，再直接使用limit size来获取数据。效果会好很多。

```
SELECT
    *
FROM
    customers
WHERE
    customer_id >= (
        SELECT
            customer_id
        FROM
            customers
        ORDER BY
            customer_id
        LIMIT 10000,
        1
    )
LIMIT 10;
```
## Mysql5.6以下版本不允许同时设置两个字段为CURRENT_TIMESTAMP
非要用的话使用触发器