INSERT INTO authors("name", fathersname, surname) 
VALUES ('Аркадий','Натанович','Стругацкий');
INSERT INTO authors("name", fathersname, surname) 
VALUES ('Борис','Натанович','Стругацкий');
INSERT INTO authors("name", surname) 
VALUES ('Рэй','Брэдбери');
INSERT INTO authors("name",  surname) 
VALUES ('Кен','Кизи');
INSERT INTO authors("name", surname) 
VALUES ('Станислав','Лем');
INSERT INTO authors("name", surname) 
VALUES ('Макс','Фрай');
INSERT INTO books(title) VALUES
('Лабиринт')
INSERT INTO books(title) VALUES
('Солярис')
INSERT INTO books(title) VALUES
('Расследование')
INSERT INTO books(title) VALUES
('Пролетая над гездом кукушки')
INSERT INTO books(title) VALUES
('Порою блажь великая...')
INSERT INTO books(title) VALUES
('451 градус по Фаренгейту')
INSERT INTO books(title) VALUES
('Марсианские хроники')
INSERT INTO books(title) VALUES
('Жук в муравейнике')
INSERT INTO books(title) VALUES
('Пикник на обочине')
INSERT INTO books(title) VALUES
('Гадкие лебеди')
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
('Амфора')
INSERT INTO publishers( title) VALUES 
('Азбука')
INSERT INTO publishers( title) VALUES 
('Эксмо')
INSERT INTO publishers( title) VALUES 
('Питер')
INSERT INTO stores(title, address, website, tel) VALUES
('Буквоед','Санкт-Петербург, Загородный просп., 35', 'www.bookvoed.ru', '310-22-44')
INSERT INTO stores(title, address, website, tel) VALUES
('Книжный дом','Санкт-Петербург, 
ул. Малая Конюшенная, 5','www.bookhouse.ru','(812) 380-73-00')
INSERT INTO stores(title, address, website, tel) VALUES
('Книжная ярмарка в ДК Крупской','пр. Обуховской Обороны, д. 105(м."Елизаровская")','www.krupaspb.ru','412-34-78')
INSERT INTO stores(title, address, website, tel) VALUES
('Снарк','Невский просп., 87/2
м. Невский проспект','www.azbooka.ru','(812) 273 77 34')
INSERT INTO stores(title, address, website, tel) VALUES
('Дом книги','191186 Санкт-Петербург, Невский проспект, дом 28. ','spbdk.ru','(812) 448-23-55')
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