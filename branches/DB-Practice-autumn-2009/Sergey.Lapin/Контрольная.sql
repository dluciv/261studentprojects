create table tab1(i integer not null);
alter table tab1 add primary key(i);
 
create table tab2(j integer not null);
alter table tab2 add primary key(j);
 
alter table tab1 add constraint tab1fk foreign key(i) references tab2(j);
alter table tab2 add constraint tab2fk foreign key(j) references tab1(i);

alter table tab2 drop constraint tab2fk
alter table tab1 drop constraint tab1fk

drop table tab1;
drop table tab2;

create table nums(i integer not null);
alter table nums add primary key(i);
insert into nums(i) values(1);
insert into nums(i)
select nums.i + (select max(i) from nums) from nums

SELECT p1.i FROM nums p1 
WHERE EXISTS  
(SELECT p2.i FROM nums p2 
WHERE p2.i < p1.i AND p2.i > 1 AND p1.i % p2.i = 0) or p1.i = 1;

select * from nums;

select nums.i from nums where nums.i % (select i from nums) = 0 
select * from nums;

drop table nums;

create table person(name varchar(160) not null);
alter table person add primary key(name);
 
insert into person(name) values('Иванов');
insert into person(name) values('Петров');
insert into person(name) values('Сидоров');
insert into person(name) values('Федоров');
insert into person(name) values('Акакий');

SELECT count(p1.name) as N, p2.name as name FROM person p1, person p2 where p1.name <= p2.name group by p2.name order by p2.name

create table ocupation(name varchar(160) not null, ocupied varchar(160) not null);
alter table ocupation add primary key(name, ocupied);
alter table ocupation add foreign key (name) references person(name);
insert into ocupation(name, ocupied) values('Иванов', 'Раскапывает');
insert into ocupation(name, ocupied) values('Федоров', 'Закапывает');

select name from ocupation 
union
select ocupied from ocupation 

SELECT person.name, ocupation.ocupied 
	FROM person, ocupation 
	WHERE person.name = ocupation.name

	UNION 

    SELECT person.name, '(null)'
	FROM person 
	WHERE NOT person.name = ANY 
	   ( SELECT ocupation.name 
		FROM ocupation ) 

	ORDER BY 1; 
