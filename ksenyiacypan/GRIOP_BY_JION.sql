--���������� ��������� � ���������
SELECT SEX, COUNT(*) FROM STUDENT GROUP BY SEX;

--������ ������� ��������� �� ������� ������ �������, ��� 4.5.
SELECT zachetka, AVG(mark) 
FROM students, student_exam 
GROUP BY zachetka
HAVING AVG(mark) > 4.5;

--����� ����������
((STUDENT JOIN STUDENT_EXAM) WHERE MARK = '5'){NAME}

--����� ����������
SELECT STUDENT.NAME FROM (STUDENT JOIN STUDENT_EXAM) WHERE MARK = '5';


--���� ���������� ��������� �������������� � id=3;
SELECT SESSION.DATE FROM (SUBJECT JOIN SESSION) WHERE TEACHER_ID = '3';

