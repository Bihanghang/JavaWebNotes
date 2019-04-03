```oracle
/*学生表*/
create table student
(
    sid varchar2(10) primary key not null,
    sname varchar2(20),
    sage number(2),
    ssex varchar2(5)
)
 
/*教师表*/
create table teacher 
(
   tid varchar2(10) primary key,
   tname varchar2(20)
) 
 
/*课程表*/
create table course
(
    cid varchar2(10),
    cname varchar2(20),
    tid varchar2(20),
    constraint pk_course primary key (cid,tid)
) 
 
/*成绩表*/
create table sc 
(
    sid varchar2(10),
    cid varchar2(10),
    score number(4,2),
    constraint pk_sc primary key (sid,cid)
)
```
```oracle
/*初始化学生表  student*/
insert into student values ('1001','张三',23,'男');
insert into student values ('1002','李四',23,'男');
insert into student values ('1003','吴鹏',25,'男');
insert into student values ('1004','琴沁',20,'女');
insert into student values ('1005','王丽',20,'女');
insert into student values ('1006','李波',21,'男');
insert into student values ('1007','刘玉',21,'男');
insert into student values ('1008','萧蓉',21,'女');
insert into student values ('1009','陈萧晓',23,'女');
insert into student values ('1010','陈美',22,'女');
 
/*初始化教师表  teacher */
insert into teacher values ('t001','刘阳');
insert into teacher values ('t002','谌燕');
insert into teacher values ('t003','胡明星');
 
 
/*初始化课程表  course*/
insert into course values ('001','J2SE','t002'); 
insert into course values ('002','Java Web','t002');
insert into course values ('003','SSH','t001');
insert into course values ('004','Oracle','t001');
insert into course values ('005','SQL SERVER 2005','t003');
insert into course values ('006','C#','t003');
insert into course values ('007','JavaScript','t002');
insert into course values ('008','DIV+CSS','t001');
insert into course values ('009','PHP','t003');
insert into course values ('010','EJB3.0','t002');
 
 
/*初始化成绩表  sc*/
insert into sc values ('1001','001',78.9);
insert into sc values ('1002','001',80.9);
insert into sc values ('1003','001',81.9);
insert into sc values ('1004','001',60.9);
insert into sc values ('1001','002',82.9);
insert into sc values ('1002','002',72.9); 
 
insert into sc values ('1005','003',78.9);
insert into sc values ('1006','004',50.9);
insert into sc values ('1007','005',81.9);
insert into sc values ('1008','006',50.9);
insert into sc values ('1005','007',42.9);
insert into sc values ('1006','008',72.9); 
insert into sc values ('1005','009',52.9);
insert into sc values ('1006','010',92.9);
```
```oracle
查询所有同学的学号、姓名、选课数、总成绩
第一种假定所有同学都选了课
select s.sid,s.sname,s2.sum_score as 总成绩,s2.sum_cid as 总课程数 from student s,
    (select sid,sum(score) sum_score,count(cid) sum_cid
    from sc
    group by sid) s2
    where s.sid = s2.sid;

select s.sid,s.sname,count_cid as 选课数, 
    sum_score  as 总成绩
    from student s
    left join 
    (select sid,count(cid) as count_cid,sum(score) as sum_score 
    from sc group by sid )sc
    on s.sid = sc.sid;
```
```oracle
查询“001”课程比“002”课程成绩高的所有学生的学号
select a.sid from
(select sid,score from sc where cid='001') a,
(select sid,score from sc where cid='002') b
where a.sid=b.sid and a.score>b.score;
```
```oracle
查询平均成绩大于60分的同学的学号和平均成绩
select sid,avg(score) from sc
group by sid
having avg(score)>60;
```
```oracle
查询姓‘李’的老师的个数
select count(*) from teacher t
where t.tname='李%';
```
```oracle
查询没有学过“刘阳”老师课的同学的学号、姓名
第一种假定了刘阳老师只教了一门课
select s.sid,s.sname 
from student s
where s.sid not in(
    select sc.sid 
    from sc,
(select cid from course,teacher
 where course.tid = teacher.tid and tname='刘阳') sc2
 where sc.cid=sc2.cid);

select s.sid,s.sname 
from student s 
where s.sid not in (
    select DISTINCT sid 
    from sc 
    where sc.cid in (
        select cid 
        from course c 
        left join teacher t on c.tid = t.tid 
        where t.tname = '刘阳')
);
```
查询有两门以上课程不及格（60）的同学的学号以及平均成绩
```sql
select sname,avg(grade)
from s,sc 
where s.s#=sc.s# and grade<60
group by sname
having count(grade)>=2
```
查询学过“011”并且学过编号“002”课程的同学的学号，姓名
```sql
SELECT 
tblstudent.StuId,tblstudent.StuName
FROM
(
SELECT t11.t1sid t33id
FROM 
(SELECT t1.StuId t1sid FROM tblscore t1 WHERE t1.CourseId='001')t11,-- 001的学生的id
(SELECT t1.StuId  t2sid FROM tblscore t1 WHERE t1.CourseId='002')t22-- 002的学生的id
WHERE 
t11.t1sid=t22.t2sid
)t33-- 查出来的是学过“001”并且也学过编号“002”课程的同学的学号
,
tblstudent
WHERE tblstudent.StuId=t33.t33id
```


