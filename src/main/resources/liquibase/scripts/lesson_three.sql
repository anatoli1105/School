-- liquibase formatted sql

-- changeset user:1
create index student_index on student (name);

-- changeset user:2
create index faculty_name_color on faculty (name, color);