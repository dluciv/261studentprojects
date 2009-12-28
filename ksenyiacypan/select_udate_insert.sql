--Выдать всех студентов из первой группы
SELECT * FROM STUDENT WHERE GROUP_ID = '1';

--Все сведенья о студентах

SELECT * FROM STUDENT;


--Количество студентов
SELECT COUNT(*) FROM STUDENT;

--Фамилии студентов и оценки
SELECT NAME, MARK 
FROM STUDENT, STUDENT_EXAM
WHERE STUDENT.STUDENT_ID = STUDENT_EXAM.STUDENT_ID;

--Поменять номер группы студента
UPDATE STUDENT 
SET GROUP_ID = '3'
WHERE NAME = 'Цыпан Ксения' ;

insert into STUDENT(name, za4etka_number, group_id, sex) values ('Хисмутова Анастасия', '010', 1, 'женский');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('Максимова Анастсия', '011', 2, 'женский');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('Савва Олег', '012', 3, 'мужской');



UPDATE SESSION 
SET DATE = 12.01.10
WHERE SUBJECT_ID = 'Математический анализ' ;


insert into TEACHER(name, subject_id) values ('Касьянова Нататья Петровна', 5);
insert into TEACHER(name, subject_id) values ('Ефименко Георгий Романович', 2);