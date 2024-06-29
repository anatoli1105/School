alter table student
ADD CONSTRAINT age_check CHECK (age > 1);





alter table student add CONSTRAINT name_unique unique (name);
alter table Student alter colump name set not null;
select * from  faculty
alter  table faculty
ADD constraint faculty_name_color_uniq unique(name,color);
alter table student alter column age set default 20
