insert into TEACHER(name) values ('Иванов Иван Иванович');
insert into TEACHER(name) values ('Степанов Степан Степанович');

insert into STUDENT(name, za4etka_number) values ('Ксения Цыпан', '001');
insert into STUDENT(name, za4etka_number) values ('Петров Василий', '002');
insert into STUDENT(name, za4etka_number) values ('Сидоров Сергей', '003');

insert into EXAM(name) values ('Алгебра');
insert into EXAM(name) values ('Геометрия');
insert into EXAM(name) values ('Физика');



insert into student_exam (student_id,exam_id, mark) values (1,1,4);
insert into student_exam (student_id,exam_id, mark) values (2,2,5);
insert into student_exam (student_id,exam_id, mark) values (3,3,3);

insert into student_exam (student_id,exam_id, mark) values (1,2,5);
insert into student_exam (student_id,exam_id, mark) values (2,1,3);
insert into student_exam (student_id,exam_id, mark) values (3,1,3);

insert into student_exam (student_id,exam_id, mark) values (1,3,4);
insert into student_exam (student_id,exam_id, mark) values (2,3,4);
insert into student_exam (student_id,exam_id, mark) values (3,2,4);
