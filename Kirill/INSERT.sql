INSERT INTO authors("name", fathersname, surname) 
VALUES ('�������','���������','����������');
INSERT INTO authors("name", fathersname, surname) 
VALUES ('�����','���������','����������');
INSERT INTO authors("name", surname) 
VALUES ('���','��������');
INSERT INTO authors("name",  surname) 
VALUES ('���','����');
INSERT INTO authors("name", surname) 
VALUES ('���������','���');
INSERT INTO authors("name", surname) 
VALUES ('����','����');
INSERT INTO books(title) VALUES
('��������')
INSERT INTO books(title) VALUES
('�������')
INSERT INTO books(title) VALUES
('�������������')
INSERT INTO books(title) VALUES
('�������� ��� ������ �������')
INSERT INTO books(title) VALUES
('����� ����� �������...')
INSERT INTO books(title) VALUES
('451 ������ �� ����������')
INSERT INTO books(title) VALUES
('����������� �������')
INSERT INTO books(title) VALUES
('��� � �����������')
INSERT INTO books(title) VALUES
('������ �� �������')
INSERT INTO books(title) VALUES
('������ ������')
INSERT INTO authorship(book_id, author_id) VALUES
(1,6)
INSERT INTO authorship(book_id, author_id) VALUES
(2,5)
INSERT INTO authorship(book_id, author_id) VALUES
(3,5)
INSERT INTO authorship(book_id, author_id) VALUES
(4,4)
INSERT INTO authorship(book_id, author_id) VALUES
(5,4)
INSERT INTO authorship(book_id, author_id) VALUES
(6,3)
INSERT INTO authorship(book_id, author_id) VALUES
(7,3)
INSERT INTO authorship(book_id, author_id) VALUES
(8,1)
INSERT INTO authorship(book_id, author_id) VALUES
(9,1)
INSERT INTO authorship(book_id, author_id) VALUES
(10,1)
INSERT INTO authorship(book_id, author_id) VALUES
(8,2)
INSERT INTO authorship(book_id, author_id) VALUES
(9,2)
INSERT INTO authorship(book_id, author_id) VALUES
(10,2)
INSERT INTO publishers( title) VALUES 
('������')
INSERT INTO publishers( title) VALUES 
('������')
INSERT INTO publishers( title) VALUES 
('�����')
INSERT INTO publishers( title) VALUES 
('�����')
INSERT INTO stores(title, address, website, tel) VALUES
('�������','�����-���������, ���������� �����., 35', 'www.bookvoed.ru', '310-22-44')
INSERT INTO stores(title, address, website, tel) VALUES
('������� ���','�����-���������, 
��. ����� ����������, 5','www.bookhouse.ru','(812) 380-73-00')
INSERT INTO stores(title, address, website, tel) VALUES
('������� ������� � �� ��������','��. ���������� �������, �. 105(�."������������")','www.krupaspb.ru','412-34-78')
INSERT INTO stores(title, address, website, tel) VALUES
('�����','������� �����., 87/2
�. ������� ��������','www.azbooka.ru','(812) 273 77 34')
INSERT INTO stores(title, address, website, tel) VALUES
('��� �����','191186 �����-���������, ������� ��������, ��� 28. ','spbdk.ru','(812) 448-23-55')
INSERT INTO edition(book_id, year, publisher_id) VALUES
(1,2000,2)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(2,2002,1)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(3,2003,2)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(4,2005,3)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(5,2005,3)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(6,2007,4)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(7,2007,1)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(8,2006,4)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(9,2006,4)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(10,2005,4)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(1,2009,1)
INSERT INTO edition(book_id, year, publisher_id) VALUES
(3,1999,2)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249860,1)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249860,3)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249863,5)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249866,4)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249869,4)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249872,3)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249875,2)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249875,1)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249878,2)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249881,5)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249884,3)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249887,4)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249887,2)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249890,1)
INSERT INTO orders(ISBN, store_id) VALUES
(1003249893,5)