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
INSERT INTO cities(name) values('СПб')
INSERT INTO cities(name) values('Москва')

UPDATE publishers SET publishers.city_id = 1 

INSERT INTO publishers(title, city_id) VALUES('Азбука', 2)
INSERT INTO publishers(title, city_id) VALUES('Эксмо', 2)

UPDATE edition SET publisher_id = 5 WHERE book_id >= 9


INSERT INTO cathegories(name) values('Американская проза XX в.')
INSERT INTO cathegories(name) values('Детектив')
INSERT INTO cathegories(name) values('Фэнтези')
INSERT INTO cathegories(name) values('Научная фантастика')

ALTER TABLE books ADD cathegory_id int;

UPDATE books SET books.cathegory_id = 4 
WHERE EXISTS( SELECT * FROM authorship WHERE authorship.book_id = books.id AND
EXISTS(SELECT * FROM authors WHERE authors.id = authorship.author_id AND 
(authors.surname = 'Стругацкий' OR authors.surname = 'Брэдбери'))) ;
 
UPDATE books SET books.cathegory_id = 1 
WHERE EXISTS( SELECT * FROM authorship WHERE authorship.book_id = books.id AND
EXISTS(SELECT * FROM authors WHERE authors.id = authorship.author_id AND authors.surname = 'Кизи')) ; 

update books set books.cathegory_id = 2 where books.title = 'Расследование';
update books set books.cathegory_id = 4 where books.title = 'Солярис';
update books set books.cathegory_id = 3 where books.title = 'Лабиринт';

ALTER TABLE edition ADD price NUMERIC(6,2) DEFAULT 500.00
ALTER TABLE edition ADD printing int DEFAULT 200000 

UPDATE edition SET price = 450.00 WHERE book_id >= 4
UPDATE edition SET price = 300.00 WHERE publisher_id < 4
UPDATE edition SET printing = 200000 
UPDATE edition SET printing = 300000 WHERE publisher_id BETWEEN 3 and 4
UPDATE edition SET printing = 150000 where publisher_id <= 2