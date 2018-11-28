
<font color=red size=4>Mybatis在预处理语句(PreparedStatement)中设置一个参数时，会用默认的typeHandler进行处理。</font>

这句话是什么意思呢，当你想用姓名查询一个人的信息时
```sql
<select id="getRole" parameterType="string" resultRole="role">
	select rolename,phonenumber from t_role where rolename = #{rolname}
</select>
```
Mybatis调用这个查询语句时会先生成预处理语句
```sql
select rolename,phonenumber from t_role where rolename = ?
```
这个‘？’也就是占位符里面应该填什么值呢？

这时候因为rolename是String类型的,rolename就会去找StringTypeHandler这个Mybatis自己已经实现的处理器
```java
public class StringTypeHandler implements TypeHandler<String> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter);
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}
}
```
我们现在只需要看第一个方法，rolename看到这个方法就会知道，这个‘？’占位符原来应该填我自己。

结果集和存储过程同理。

假如Mybatis找不到类型的转换器就不知道该干嘛了，像下面这句代码
```sql
<insert id="addPerson" parameterType="person">
	insert into people(id, name, phonenumber)
	values(#{id}, #{name},#{phonenumber})
</insert>
```
phonenumber这个类是自己创建的，长这样：
```java
private String countryCode;
	private String stateCode;
	private String number;
	public PhoneNumber() {
	}
	// 021-3359-3216
	public PhoneNumber(String str) {
		String[] number = str.split("[-]");
		this.countryCode = number[0];
		this.stateCode = number[1];
		this.number = number[2];
	}
	// 021,3359,3216
	public PhoneNumber(String countryCode, String stateCode, String number) {
		this.countryCode = countryCode;
		this.stateCode = stateCode;
		this.number = number;
	}
	public String getAsString() {
		return countryCode + "-" + stateCode + "-" + number;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
```
所以我们需要告诉Mybatis应该在预处理语句占位符中填什么，这时候就需要自己重新定义一个typeHandler.

```java
public class PhoneTypeHandler extends BaseTypeHandler<PhoneNumber> {
	//遇到PhoneNumber参数的时候应该如何在ps中设置值
	//ps.setXXX()
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PhoneNumber parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getAsString());
	}
	//查询中遇到PhoneNumber类型的应该如何封装(使用列名封装)
	//stuedent.setTelPhone(new PhoneNumber("021-3359-3216"))
	@Override
	public PhoneNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new PhoneNumber(rs.getString(columnName));
	}
	//查询中遇到PhoneNumber类型的应该如何封装(使用列的下标)
	@Override
	public PhoneNumber getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new PhoneNumber(rs.getString(columnIndex));
	}
	//CallableStatement使用中遇到了PhoneNumber类型的应该如何封装
	@Override
	public PhoneNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new PhoneNumber(cs.getString(columnIndex));
	}
}
```
想试试的话，这里有代码![源码下载](https://github.com/Bihanghang/Mybatis1)



