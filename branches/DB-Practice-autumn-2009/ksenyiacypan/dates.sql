insert into TEACHER(name) values ('������ ���� ��������');
insert into TEACHER(name) values ('�������� ������ ����������');

insert into STUDENT(name, za4etka_number) values ('������ �����', '001');
insert into STUDENT(name, za4etka_number) values ('������ �������', '002');
insert into STUDENT(name, za4etka_number) values ('������� ������', '003');

insert into EXAM(name) values ('�������');
insert into EXAM(name) values ('���������');
insert into EXAM(name) values ('������');



insert into student_exam (student_id,exam_id, mark) values (1,1,4);
insert into student_exam (student_id,exam_id, mark) values (2,2,5);
insert into student_exam (student_id,exam_id, mark) values (3,3,3);

insert into student_exam (student_id,exam_id, mark) values (1,2,5);
insert into student_exam (student_id,exam_id, mark) values (2,1,3);
insert into student_exam (student_id,exam_id, mark) values (3,1,3);

insert into student_exam (student_id,exam_id, mark) values (1,3,4);
insert into student_exam (student_id,exam_id, mark) values (2,3,4);
insert into student_exam (student_id,exam_id, mark) values (3,2,4);
