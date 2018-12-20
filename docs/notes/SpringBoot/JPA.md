## 主键自增
```java
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Integer categoryId;
```
auto:        当数据库中  不存在 这张表的时候可以用它建表的时候, 制定自增的方式,  存在的时候插入数据还用它就会出错了<br>
identity:     使用SQL Server 和 MySQL 的自增字段

JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.<br>
TABLE：使用一个特定的数据库表格来保存主键。<br>
SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。<br>
IDENTITY：主键由数据库自动生成（主要是自动增长型）<br>
AUTO：主键由程序控制。<br>
在指定主键时，如果不指定主键生成策略，默认为AUTO。<br>
@Id<br>
相当于<br>
@Id<br>
@GeneratedValue(strategy = GenerationType.AUTO)<br>
 
identity:<br>
使用SQL Server 和 MySQL 的自增字段，这个方法不能放到 Oracle 中，Oracle 不支持自增字段，要设定sequence（MySQL 和 SQL Server 中很常用）。<br>
Oracle就要采用sequence了.
 