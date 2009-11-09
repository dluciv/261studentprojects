/* Formatted on 2009/11/09 01:07 (Formatter Plus v4.8.5) */
-- �������, ���������� ��� ����������, ������������ � �������
CREATE TABLE application
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR2(50) 
    NOT NULL 
	UNIQUE,
  description VARCHAR2(2000)
);

-- �������, ���������� ����������, ��������� "�����" �����
CREATE TABLE outer_application
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES application(ID) 
	ON DELETE CASCADE
);

-- �������, ���������� ���������� ���������� (���������� ���������� - ����� ����������, 
-- ��������� "�����")
CREATE TABLE free_application
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES outer_application(ID) 
	ON DELETE CASCADE,
  required NUMBER(1) 
    DEFAULT 0 
	CHECK (required IN (0, 1))
);

-- �������, ���������� ����������, ������� ��������� �������� ����� "��������" �� ���������.
-- ��� �� �������� ������ ����������, ��������� "�����"
CREATE TABLE optional_application
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES outer_application(ID) 
	ON DELETE CASCADE,
  price NUMBER(10) 
    NOT NULL,
  CONSTRAINT price_positive 
    CHECK (price > 0)
);

-- �������, ���������� "����������" ����������, � ������� ����� ����������� ��������
-- ���� ���������� ��������������� �����, �� ������ ��� �� "����������"
CREATE TABLE inner_application
(
  ID NUMBER 
    PRIMARY KEY 
	REFERENCES application(ID) 
	ON DELETE CASCADE,
  outer_access NUMBER(1) 
    DEFAULT 0,
  CONSTRAINT bool_val_access 
    CHECK (outer_access IN (0, 1))
);

-- �������, ���������� ������ ���� �����������. ������������, ������� ����� ������������, 
-- �������� �������������
CREATE TABLE employee
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR2(255) 
    NOT NULL,
  phone VARCHAR(15),
  icq VARCHAR(15),
  email VARCHAR(25),
  jabber VARCHAR(25)
);

-- ������ �������������
CREATE TABLE programmer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE,
  experience NUMBER(2) 
    CHECK (experience >= 0)
);

-- ������ ��������
CREATE TABLE tester
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);

-- ������ ��������������
CREATE TABLE maintancer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);
-- ������ ����������� ���������
CREATE TABLE tech_writer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);


-- ������ ����������. �� ������� ����� ��� ����� on cascade delete, ������ ��� ��� ��������
-- ��������������� ��������� ������� ���������
CREATE TABLE customer
(
  ID NUMBER 
    PRIMARY KEY,
  NAME VARCHAR2(100) 
    NOT NULL 
	UNIQUE,
  depart NUMBER(2) 
    DEFAULT 1 
	CHECK (depart > 0),
  phone VARCHAR2(15),
  maintance 
    REFERENCES maintancer(ID)
);

-- ������ ������������� ���������� � ����������. 
CREATE TABLE installed
(
 applicationid 
   REFERENCES outer_application(ID) 
   ON DELETE CASCADE 
   NOT NULL,
 customerid 
   REFERENCES customer(ID) 
   ON DELETE CASCADE 
   NOT NULL,
 CONSTRAINT uniq_pair_ap_cu 
   UNIQUE (applicationid, customerid)
);

-- ��� ��� ���������� ������� � ������������, ���������������� "�� �����"
CREATE TABLE individual_application
(
 ID 
   REFERENCES application(ID) 
   ON DELETE CASCADE 
   NOT NULL
	UNIQUE,
 customerid 
   REFERENCES customer(ID) 
   ON DELETE CASCADE 
   NOT NULL,
 price NUMBER(10) 
   CHECK (price > 0 )
);

-- ������� "��� ��� ����� ����������� ��������"
CREATE TABLE development
(
  applicationid 
    REFERENCES application(ID) 
	ON DELETE CASCADE 
	NOT NULL,
  employeeid 
    REFERENCES employee(ID) 
	ON DELETE CASCADE 
	NOT NULL,
 CONSTRAINT uniq_pair_ap_em 
   UNIQUE (applicationid, employeeid)
);

-- ������ ������ ����������� ������������
CREATE TABLE LANGUAGE
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR(20) 
    NOT NULL 
	CHECK (NAME <> ''),
  description VARCHAR2(255)
);

-- ��� �� ����������� ��������� �� ��� ����� ������
CREATE TABLE write_lang
(
  writerid 
    REFERENCES tech_writer(ID) 
	ON DELETE CASCADE 
	NOT NULL,
  langid 
    REFERENCES LANGUAGE(ID) 
	ON DELETE CASCADE 
	NOT NULL,
 CONSTRAINT uniq_pair_wr_la 
   UNIQUE (writerid, langid)
);


-- ������ ��������� ������ ����������
CREATE TABLE prog_language
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR(20) 
    NOT NULL 
	CHECK (NAME <> ''),
  description VARCHAR2(255)
);

-- ��� �� ��� �� ������������� ����� ������
CREATE TABLE devel_lang
(
  progid 
    REFERENCES programmer(ID) 
	ON DELETE CASCADE
	NOT NULL,
  langid 
    REFERENCES LANGUAGE(ID) 
	ON DELETE CASCADE
	NOT NULL,
 CONSTRAINT uniq_pair_pr_la 
   UNIQUE (progid, langid)
);


CREATE SEQUENCE applic_id MINVALUE 1 MAXVALUE 999999999999999999999999999 CYCLE;
CREATE SEQUENCE employee_id MINVALUE 1 MAXVALUE 999999999999999999999999999 CYCLE;
CREATE SEQUENCE customer_id MINVALUE 1 MAXVALUE 999999999999999999999999999 CYCLE;
CREATE SEQUENCE la MINVALUE 1 MAXVALUE 999999999999999999999999999 CYCLE;
CREATE SEQUENCE pr_la MINVALUE 1 MAXVALUE 999999999999999999999999999 CYCLE;

COMMIT ;