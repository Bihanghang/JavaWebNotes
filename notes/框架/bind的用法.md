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

