-- оличество студентов и студенток
SELECT SEX, COUNT(*) FROM STUDENT GROUP BY SEX;

--Ќомера зачеток студентов со средним баллом большим, чем 4.5.
SELECT zachetka, AVG(mark) 
FROM students, student_exam 
GROUP BY zachetka
HAVING AVG(mark) > 4.5;

--»мена отличников
((STUDENT JOIN STUDENT_EXAM) WHERE MARK = '5'){NAME}


