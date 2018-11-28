Mybatis有两个默认枚举处理器
* EnumOrdinalTypeHandler
* EnumTypeHandler
* [自定义枚举](#自定义枚举)

## EnumOrdinalTypeHandler
这个处理器负责将pojo里面的枚举类型转化为枚举的下标值存入数据库

假如pojo类有Sex这个枚举类的实例
```java
public enum Sex {
    MALE,FEMALE;
}
```
插入Sex.MALE时，数据库插入值为MALE的枚举下标值0，插入Sex.FEMALE时，插入值为FEMALE的枚举下标值1.

<font color=red size=4>使用这个处理器的步骤</font>

1.在Mybatis做如下配置
```
<typeHandlers>
    <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" 
        javaType="mytatis.enum2.Sex"
    />
</typeHandlers>
```
这样Mybatis遇到这个枚举就能对它做处理了。

2.pojo类里面类型为枚举类型，对应的数据库表字段为int类型

3.在mapper.xml中的&lt;insert /&gt;语句枚举类型写成这样
```
#{sex, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}
```
resultmap写成这样
```
<result column="sex" property="sex" typeHandler=
		"org.apache.ibatis.type.OrdinalTypeHandler"/>
```
## EnumTypeHandler
同上，不同的是将数据库的int类型换成varchar类型，插入数据库的是枚举类成员的名称。
## 自定义枚举
1.将自定义枚举处理器加入到&lt;typeHandlers /&gt;

2.实现TypeHandler接口重新实现如何处理映射关系。



