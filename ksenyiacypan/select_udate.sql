--������ ���� ��������� �� ������ ������
SELECT * FROM STUDENT WHERE GROUP_ID = '1';

--��� �������� � ���������

SELECT * FROM STUDENT;


--���������� ���������
SELECT COUNT(*) FROM STUDENT;

--������� ��������� � ������
SELECT NAME, MARK 
FROM STUDENT, STUDENT_EXAM
WHERE STUDENT.STUDENT_ID = STUDENT_EXAM.STUDENT_ID;

--�������� ����� ������ ��������
UPDATE STUDENT 
SET GROUP_ID = '3'
WHERE NAME = '����� ������' ;
