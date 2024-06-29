INSERT INTO faculty("name",color) values ('ggg','ggggg')

select s.name,s.age,f.student_id
from student s
join faculty f on f.id=s.faculty_id

select * from student s
join avatar a on a.id=s.id