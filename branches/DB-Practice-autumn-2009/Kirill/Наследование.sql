Create Table volumes (
id int primary key identity(1,1),
book_id int references books(id),
volume_title varchar(50),
number int
)
alter table volumes 
add constraint book_id_inn check (book_id IS NOT NULL)
drop table volumes
insert into volumes(book_id, volume_title, number) values(7,'����������� �������', 1);
insert into volumes(book_id, volume_title, number) values(7,'���� ��� ������� � ������������...',2);
insert into volumes(book_id, volume_title, number) values(7,'�������� �� ��������� ������', 3);
insert into volumes(book_id, number) values(1, 1);
insert into volumes(book_id, number) values(1, 2);
insert into volumes(book_id, number) values(2, 1);
insert into volumes(book_id, number) values(2, 2);
insert into volumes(book_id, number) values(2, 3);
insert into books(title) values ('��������� �����' )
update books set cathegory_id = (SELECT id FROM cathegories WHERE name = '�������') where books.title = '��������� �����'
insert into authors(name, surname) values ('����', '�������')
insert into authorship(book_id, author_id) 
select books.id, authors.id from books, authors where books.title = '��������� �����' and authors.surname = '�������'
insert into volumes(book_id, volume_title, number) values(11,'������� �����', 1);
insert into volumes(book_id, volume_title,number) values(11,'��� �����', 2);
insert into volumes(book_id, volume_title,number) values(11,'����������� ������', 3); 
  