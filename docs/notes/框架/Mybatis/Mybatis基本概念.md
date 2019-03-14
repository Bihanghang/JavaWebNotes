# bind的用法
Mybatis使用bind元素进行模糊查询，不用在乎数据库是mysql还是oracle从而提高可移植性

## 使用bind元素传递多个参数
```sql
public List<Student> findStudents(@Param("studentName")String studentName,@Param("note")String note)

<select id="getStudent" resultMap="studentMap" >
    <bind name="pattern_studentName" value="'%' + studentName + '%'" />
    <bind name="pattern_note" value="'%' + note + '%'" />
    select id, cnname, sex, selfcard_no, note
    from t_student
    where student_name like #{pattern_studentName}
    and note like #{note}
</select>
```
# 参数传递
## 一个参数
传递一个参数时，接口方法中参数名称与#{}中名称可以不同，但是类型要一致。
# 延迟加载
```
<settings>
    <setting name="lazyLoadingEnabled" value="true" />
</settings>
```
这样就能实现延迟加载但是不够彻底，因为Mybatis默认加载同一层级的所有成员。

这句话什么意思呢?
```
<resultMap id="studentMap" type="mytatis.pojo.Student" >
    <id column="id" property="id" jdbcType="INTEGER" />
    <association property="studentSelfcard" column="id" 
    	select="mytatis.mapper.StudentSelfcardMapper.findStudentSelfcardByStudentId" />
  	<collection property="studentLecturesList" column="id" 
  	    select="mytatis.mapper.StudentLectureMapper.findStudentLectureByStuId" />
  </resultMap>
```
假如studentSelfcard这个成员被调的时候，studentLecturesList这个成员也会被调用，因为他们两是同一层级的，但是假如studentLecturesList下面还有&lt;association&gt;,这个&lt;association&gt;不会被加载，因为它属于下一个层级。
```
<settings>
    <setting name="lazyLoadingEnabled" value="true" />
    <setting name="aggressiveLazyLoading" value="false" />
</settings>
```
这样配置就可以只加载调用的成员。

# 枚举处理器
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
# 自动映射
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

# trim的用法
```sql
select * from t_user
<trim prefix="WHERE" prefixOverrides="and">
　　<if test="roleName != null and roleName != ''"> AND role_name=#{roleName}</if>
</trim>
```
如果条件成立并且没有trim标签，那么sql语句就为

select * from t_user <font size=4 color=red>AND role_name=#{roleName}</font>

现在有了trim标签，prefix表示在红色代码前面添加where,prefixoverride表示把最前面的and删除.那么代码就变为

select * from t_user <font size=4 color=red>WHERE role_name=#{roleName}</font>

```sql
update t_role
<trim prefix="SET" suffixOverrides=",">
    <if test="roleName != null and roleName != ''"> role_name=#{roleName},</if>
    <if test="note != null and note != ''"> role_name=#{roleName},</if>
</trim>
where role_no = #{roleNo}
```
如果条件成立并且没有trim标签，那么sql语句就为

update t_role <font size=4 color=red> role_name=#{roleName},role_name=#{roleName},</font> where role_no=#{roleNo}

现在有了trim标签，prefix表示在红色代码前面添加set,suffixOverrides表示把末尾的','删除.那么代码就变为

update t_role <font size=4 color=red> SET role_name=#{roleName},role_name=#{roleName}</font> where role_no=#{roleNo}
# 级联
## 一对一
假如有student表(学生表)和student_card表(学生证表)。

student表中有一个字段self_card用来查student_card，student_card表中有一个student_id用来查student。

在Student的pojo类中，成员self_card被替换成StudentCard的pojo类，通过查询student_id将Student中的StudentCard补全。

student表如果没有self_card这个字段对于pojo类来说是无关紧要的，因为pojo类可以重新增加其对应的成员再对其赋值。

这种情况在Mybatis中可以在StudentMapper的&lt;resultMap&gt;中添加
```
<association property="self_card" column="id" 
    	select="mytatis.mapper.StudentSelfcardMapper.findStudentSelfcardByStudentId" />
```
就可以获取Student的所有信息。
## 一对多
如果一个学生对应多门课程，这时候称为一对多，而每门课程对其具体的课程信息就是一对一。
一对多的级联就需要用&lt;collection&gt;
```
<collection property="studentLecturesList" column="id" 
  	    select="mytatis.mapper.StudentLectureMapper.findStudentLectureByStuId" />
```
一对一还是用上面的&lt;association&gt;
具体代码请点击[源码](https://github.com/Bihanghang/Mybatis1)

## 批量插入
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ceair.car.order.repository.mapper.coustom.TrainOrderRefundticketSelfMapper">
    <insert id="insertForeach" parameterType="java.util.List" useGeneratedKeys="true">
    insert into train_order_refundticket (order_id, trade_id, ticket_id,
      passenger_name, passenger_idno, sales_price,
      ticket_price, seat_type_name, tax,
      ticket_no, status, status_name,
      refund_remark, is_all_refund, apply_user_id,
      apply_user_name, create_time, change_time,
      ticks_timestamp)
    values
    <foreach collection="list" item="item" index="index" separator=",">
        (#{item.orderId,jdbcType=BIGINT}, #{item.tradeId,jdbcType=VARCHAR}, #{item.ticketId,jdbcType=VARCHAR},
        #{item.passengerName,jdbcType=VARCHAR}, #{item.passengerIdno,jdbcType=VARCHAR}, #{item.salesPrice,jdbcType=DOUBLE},
        #{item.ticketPrice,jdbcType=DOUBLE}, #{item.seatTypeName,jdbcType=VARCHAR}, #{item.tax,jdbcType=DOUBLE},
        #{item.ticketNo,jdbcType=VARCHAR}, #{item.status,jdbcType=INTEGER}, #{item.statusName,jdbcType=VARCHAR},
        #{item.refundRemark,jdbcType=VARCHAR}, #{item.isAllRefund,jdbcType=BIT}, #{item.applyUserId,jdbcType=VARCHAR},
        #{item.applyUserName,jdbcType=VARCHAR}, #{item.createTime,jdbcType=TIMESTAMP}, #{item.changeTime,jdbcType=TIMESTAMP},
        #{item.ticksTimestamp,jdbcType=BIGINT})
    </foreach>
  </insert>
</mapper>
````
```java
public interface TrainOrderRefundticketSelfMapper {
    /**
     *  功能描述: 批量插入退款条数
     *  @param: [list]
     *  @return: int 批量条数
     *  @auther: BIHANG
     *  @date: 2019/3/5 14:52
     */
    int insertForeach(List<TrainOrderRefundticket> list);
}
```

