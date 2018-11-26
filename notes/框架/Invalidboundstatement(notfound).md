
```
org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.dao.RoleDao.getRole
```
在Spring整合Mybatis中爆出这个错。

在mybatis配置文件中原来写法为：
```
<mappers>
	<package name="com.mapping" />
</mappers>
```
将其改成
```
<mappers>
    <mapper resource="com\mapping\roleMapper.xml"></mapper>
    <mapper resource="com\mapping\userMapper.xml"></mapper> 
</mappers>
```
就可以运行了，按照道理来说两种方式应该都是可以运行的，莫非Spring只支持resource这一种配法？
