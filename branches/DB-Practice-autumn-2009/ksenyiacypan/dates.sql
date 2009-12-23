insert into TEACHER(name, subject_id) values ('Иванов Иван Иванович', 1);
insert into TEACHER(name, subject_id) values ('Степанов Степан Степанович', 2);
insert into TEACHER(name, subject_id) values ('Кириленко Яков Александрович', 3);
insert into TEACHER(name, subject_id) values ('Вояковская Наталья Николаевна', 4);
insert into TEACHER(name, subject_id) values ('Грищеко Ирина Николаевна', 5);
insert into TEACHER(name, subject_id) values ('Рура Елена Михайловна', 6);
insert into TEACHER(name, subject_id) values ('Перминов Алексей Анатольевич', 7);
insert into TEACHER(name, subject_id) values ('Скрипкина Ольга алекснадровна', 8);
insert into TEACHER(name, subject_id) values ('Косьянова Наталья Петровна', 9);

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Ксения Цыпан', '001', 1, 'женский');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('Петров Василий', '002', 1, 'мужской');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('Колянов Дмитрий', '003', 1, 'мужской');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Лапин Сергей', '004', 2, 'мужской');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Кузнецов Кирилл', '005', 2, 'мужской');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Нишневич Анастасия', '006', 2 'женский');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Солодкая Анастасия', '007', 3 'женский');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Мурашов Кирилл', '008', 3, 'мужской');

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Волкова Екатерина', '009', 3, 'женский');



insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (1, 'ksen@ya.ru');

insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (2, 'petr@ya.ru');

insert into STUDENT_DISTANT(STUDENT_DIST_ID, MAIL_ADRESS) values (3, 'kol@ya.ru');




insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (4, 'да');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (5, 'нет');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (6, 'нет');

insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (7, 'нет');


insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (8, 'да');

insert into STUDENT_OCHNIK(STUDENT_OSCH_ID, STEPENDIYA) values (9, 'нет');


insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (1,1);
insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (2,2);
insert into GROUPS(STAROSTA_ID, KURATOR_ID) values (3,3);


insert into SUBJECT(name) values ('Алгебра');
insert into SUBJECT(name) values ('Геометрия');
insert into SUBJECT(name) values ('Физика');
insert into SUBJECT(name) values ('Конструирование ПО');
insert into SUBJECT(name) values ('География');
insert into SUBJECT(name) values ('Биология');
insert into SUBJECT(name) values ('Литература');
insert into SUBJECT(name) values ('Русский язык');
insert into SUBJECT(name) values ('Программирование');


insert into student_exam (student_id,exam_id, mark) values (1,1,4);
insert into student_exam (student_id,exam_id, mark) values (2,2,5);
insert into student_exam (student_id,exam_id, mark) values (3,3,3);

insert into student_exam (student_id,exam_id, mark) values (4,4,5);
insert into student_exam (student_id,exam_id, mark) values (5,5,3);
insert into student_exam (student_id,exam_id, mark) values (6,6,3);

insert into student_exam (student_id,exam_id, mark) values (7,7,4);
insert into student_exam (student_id,exam_id, mark) values (8,8,4);
insert into student_exam (student_id,exam_id, mark) values (9,9,4);
