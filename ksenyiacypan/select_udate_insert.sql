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

insert into STUDENT(name, za4etka_number, group_id, sex) values ('��������� ���������', '010', 1, '�������');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('��������� ��������', '011', 2, '�������');
insert into STUDENT(name, za4etka_number, group_id, sex) values ('����� ����', '012', 3, '�������');



UPDATE SESSION 
SET DATE = 12.01.10
WHERE SUBJECT_ID = '�������������� ������' ;


insert into TEACHER(name, subject_id) values ('��������� ������� ��������', 5);
insert into TEACHER(name, subject_id) values ('�������� ������� ���������', 2);