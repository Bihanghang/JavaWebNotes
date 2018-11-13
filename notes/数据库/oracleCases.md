```oracle
查询表中每一个人的最近一次提取时间。
select 姓名,max(提取时间) 提取时间 from 表名 group by 姓名
//查每个人的所有字段
select * from 表名 a,(select 姓名,max(提取时间) from 表名 group by 姓名) b where a.姓名=b.姓名 and a.提取时间=b.提取时间
```