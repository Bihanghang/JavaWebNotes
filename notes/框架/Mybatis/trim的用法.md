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


