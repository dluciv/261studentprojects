/* Formatted on 2009/11/09 01:12 (Formatter Plus v4.8.5) */
-- ������ ��� �� ������� ���������� (�� ���� ������ ��������� ���� ����� ������-��-������)

DELETE FROM development;

-- ������ ������ �� ������� ����������. ��������� ��������, ��� ����� ������� � �� �������� �������

DELETE FROM application
      WHERE LOWER (NAME) = 'laboratory';

-- ������ �� ���������� ����, ��� ���� ��� ���������������

DELETE FROM customer
      WHERE maintance IS NULL;

-- ������� ������ � ������� 

UPDATE employee
   SET phone = '9602612630'
 WHERE NAME = 'Anastasiya';