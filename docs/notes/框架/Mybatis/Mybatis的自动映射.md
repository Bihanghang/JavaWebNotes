## autoMappingBehavior
在Mybatis的配置文件中添加settings属性的autoMappingBehavior
```
<settings>
    <setting name="autoMappingBehavior" value="NONE"/>
</settings>
```
autoMappingBehavior有三个属性(默认是PARTIAL)
* NONE：取消自动映射
* PARTIAL:只会自动映射，没有定义嵌套结果集映射的结果集
* FULL:会自动映射任意复杂的结果集（无论是否嵌套)

自动映射的时候sql语句的结果集字段是不区分大小写的，所以映射的pojo成员变量也不需要区分大小写，都可以映射到。
## mapUnderscoreToCamelCase
如果数据库符合命名规范，即每个单词之间用下划线连接，pojo类符合驼峰式命名，就可以设置mapUnderscoreToCamelCase为true，这样就可以自动映射。

不用再给select语句的结果集字段起别名来对应pojo类的成员。
