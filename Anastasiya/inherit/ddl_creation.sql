/* Formatted on 2009/11/09 01:07 (Formatter Plus v4.8.5) */
-- Таблица, содержащая все приложения, существующие в системе
CREATE TABLE application
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR2(50) 
    NOT NULL 
	UNIQUE,
  description VARCHAR2(2000)
);

-- Таблица, содержащая приложения, доступные "извне" фирмы
CREATE TABLE outer_application
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES application(ID) 
	ON DELETE CASCADE
);

-- Таблица, содержащая бесплатные приложения (бесплатные приложения - часть приложений, 
-- доступных "извне")
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

-- Таблица, содержащая приложения, которые отдельный заказчик может "заказать" на установку.
-- Так же является частью приложений, доступных "извне"
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

-- Таблица, содержащая "внутренние" приложения, к которым может подкючиться заказчик
-- если выставлена соответствующая опция, но только что бы "посмотреть"
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

-- Таблица, содержащая список всех сотрудников. Наследование, которое здесь организуется, 
-- является множественным
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

-- Список программистов
CREATE TABLE programmer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE,
  experience NUMBER(2) 
    CHECK (experience >= 0)
);

-- Список тестеров
CREATE TABLE tester
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);

-- Список сопровождающих
CREATE TABLE maintancer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);
-- Список технических писателей
CREATE TABLE tech_writer
(
  ID NUMBER(12) 
    PRIMARY KEY 
	REFERENCES employee(ID) 
	ON DELETE CASCADE
);


-- Список заказчиков. На внешнем ключе нет опции on cascade delete, потому что при удалении
-- сопровождающего нелогично удалять заказчика
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

-- Список установленных приложений у заказчиков. 
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

-- Вот тут сожздается таблица с приложениями, разрабатываемыми "на заказ"
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

-- Таблица "кто над каким приложением работает"
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

-- Список языков технической документации
CREATE TABLE LANGUAGE
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR(20) 
    NOT NULL 
	CHECK (NAME <> ''),
  description VARCHAR2(255)
);

-- Кто из технических писателей на чем может писать
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


-- Список доступных языков разработки
CREATE TABLE prog_language
(
  ID NUMBER(12) 
    PRIMARY KEY,
  NAME VARCHAR(20) 
    NOT NULL 
	CHECK (NAME <> ''),
  description VARCHAR2(255)
);

-- Кто на чем из программистов умеет писать
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