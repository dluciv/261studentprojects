--Количество студентов и студенток
SELECT SEX, COUNT(*) FROM STUDENT GROUP BY SEX;

--Номера зачеток студентов со средним баллом большим, чем 4.5.
SELECT zachetka, AVG(mark) 
FROM students, student_exam 
GROUP BY zachetka
HAVING AVG(mark) > 4.5;

--Имена отличников
((STUDENT JOIN STUDENT_EXAM) WHERE MARK = '5'){NAME}

--Имена отличников
SELECT STUDENT.NAME FROM (STUDENT JOIN STUDENT_EXAM) WHERE MARK = '5';


--Даты проведения экзаменов преподавателся с id=3;
SELECT SESSION.DATE FROM (SUBJECT JOIN SESSION) WHERE TEACHER_ID = '3';

