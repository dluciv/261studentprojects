insert into TEACHER(name, subject_id) values ('������ ���� ��������', 1);
insert into TEACHER(name, subject_id) values ('�������� ������ ����������', 2);
insert into TEACHER(name, subject_id) values ('��������� ���� �������������', 3);
insert into TEACHER(name, subject_id) values ('���������� ������� ����������', 4);
insert into TEACHER(name, subject_id) values ('������� ����� ����������', 5);
insert into TEACHER(name, subject_id) values ('���� ����� ����������', 6);
insert into TEACHER(name, subject_id) values ('�������� ������� �����������', 7);
insert into TEACHER(name, subject_id) values ('��������� ����� �������������', 8);
insert into TEACHER(name, subject_id) values ('��������� ������� ��������', 9);

insert into STUDENT(name, za4etka_number, group_id, sex) values ('������ �����', '001', 1, '�������');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('������ �������', '002', 1, '�������');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('������� �������', '003', 1, '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('����� ������', '004', 2, '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('�������� ������', '005', 2, '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('�������� ���������', '006', 2 '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('�������� ���������', '007', 3 '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('������� ������', '008', 3, '�������');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('������� ���������', '009', 3, '�������');



insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (1, 'ksen@ya.ru');

insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (2, 'petr@ya.ru');

insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (3, 'kol@ya.ru');




insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (4, '��');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (5, '���');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (6, '���');

insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (7, '���');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (8, '��');

insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (9, '���');


insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (1,1);
insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (2,2);
insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (3,3);


insert into SUBJECT(name) values ('�������');
insert into SUBJECT(name) values ('���������');
insert into SUBJECT(name) values ('������');
insert into SUBJECT(name) values ('��������������� ��');
insert into SUBJECT(name) values ('���������');
insert into SUBJECT(name) values ('��������');
insert into SUBJECT(name) values ('����������');
insert into SUBJECT(name) values ('������� ����');
insert into SUBJECT(name) values ('����������������');


insert into student_exam (student_id,exam_id, mark) values (1,1,4);
insert into student_exam (student_id,exam_id, mark) values (2,2,5);
insert into student_exam (student_id,exam_id, mark) values (3,3,3);

insert into student_exam (student_id,exam_id, mark) values (4,4,5);
insert into student_exam (student_id,exam_id, mark) values (5,5,3);
insert into student_exam (student_id,exam_id, mark) values (6,6,3);

insert into student_exam (student_id,exam_id, mark) values (7,7,4);
insert into student_exam (student_id,exam_id, mark) values (8,8,4);
insert into student_exam (student_id,exam_id, mark) values (9,9,4);
