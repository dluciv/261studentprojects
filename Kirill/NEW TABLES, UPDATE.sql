CREATE TABLE cathegories (
id int PRIMARy key IDENTITY(1,1),
name varchar(40) 
)
CREATE TABLE cities(
id int Primary key identity(1,1),
name varchar(20)
)
ALTER TABLE publishers
ADD city_id int 
INSERT INTO cities(name) values('���')
INSERT INTO cities(name) values('������')

UPDATE publishers SET publishers.city_id = 1 

INSERT INTO publishers(title, city_id) VALUES('������', 2)
INSERT INTO publishers(title, city_id) VALUES('�����', 2)

UPDATE edition SET publisher_id = 5 WHERE book_id >= 9


INSERT INTO cathegories(name) values('������������ ����� XX �.')
INSERT INTO cathegories(name) values('��������')
INSERT INTO cathegories(name) values('�������')
INSERT INTO cathegories(name) values('������� ����������')

ALTER TABLE books ADD cathegory_id int;

UPDATE books SET books.cathegory_id = 4 
WHERE EXISTS( SELECT * FROM authorship WHERE authorship.book_id = books.id AND
EXISTS(SELECT * FROM authors WHERE authors.id = authorship.author_id AND 
(authors.surname = '����������' OR authors.surname = '��������'))) ;
 
UPDATE books SET books.cathegory_id = 1 
WHERE EXISTS( SELECT * FROM authorship WHERE authorship.book_id = books.id AND
EXISTS(SELECT * FROM authors WHERE authors.id = authorship.author_id AND authors.surname = '����')) ; 

update books set books.cathegory_id = 2 where books.title = '�������������';
update books set books.cathegory_id = 4 where books.title = '�������';
update books set books.cathegory_id = 3 where books.title = '��������';