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
## mysql datetime 数据的自动更新(update_time)和初始化(create_time)
```sql
--创建测试表
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 
--检测默认值，插入测试数据
INSERT INTO timestampTest (name) VALUES ('11'),('22'),('33');
 
--检测自动更新，更新某条数据
UPDATE timestampTest SET name = '12' WHERE id = 1;
```