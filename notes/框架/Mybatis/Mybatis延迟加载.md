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
