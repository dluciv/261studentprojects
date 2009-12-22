Create Table volumes (
id int primary key identity(1,1),
book_id int references books(id),
volume_title varchar(50),
number int
)
alter table volumes 
add constraint book_id_inn check (book_id IS NOT NULL)
drop table volumes
insert into volumes(book_id, volume_title, number) values(7,'Марсианские хрноики', 1);
insert into volumes(book_id, volume_title, number) values(7,'Были они смуглые и золотоглазые...',2);
insert into volumes(book_id, volume_title, number) values(7,'Разговор по льготному тарифу', 3);
insert into volumes(book_id, number) values(1, 1);
insert into volumes(book_id, number) values(1, 2);
insert into volumes(book_id, number) values(2, 1);
insert into volumes(book_id, number) values(2, 2);
insert into volumes(book_id, number) values(2, 3);
insert into books(title) values ('Властелин колец' )
update books set cathegory_id = (SELECT id FROM cathegories WHERE name = 'фэнтези') where books.title = 'Властелин колец'
insert into authors(name, surname) values ('Джон', 'Толкиен')
insert into authorship(book_id, author_id) 
select books.id, authors.id from books, authors where books.title = 'Властелин колец' and authors.surname = 'Толкиен'
insert into volumes(book_id, volume_title, number) values(11,'Братсво колца', 1);
insert into volumes(book_id, volume_title,number) values(11,'Две башни', 2);
insert into volumes(book_id, volume_title,number) values(11,'Возвращение короля', 3); 
  