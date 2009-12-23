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
