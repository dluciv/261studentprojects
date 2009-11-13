CREATE TABLE authors (
id int IDENTITY (1,1),
"name" varchar (20),
fathersname varchar(30),
surname varchar(30)

PRIMARY KEY (id) 
) 
CREATE TABLE books(
id int IDENTITY(1,1),
title varchar(50)

PRIMARY KEY (id)
)
CREATE TABLE authorship(
id int IDENTITY(1,1),
book_id int,
author_id int

PRIMARY KEY (id),
FOREIGN KEY(book_id) REFERENCES books(id),
FOREIGN KEY(author_id) REFERENCES authors(id)
)
CREATE TABLE stores (
id int IDENTITY (1,1),
title varchar(50),
address varchar(50),
website varchar(20),
tel varchar(20)

PRIMARY KEY (id)
)
CREATE TABLE publishers(
id int IDENTITY(1,1),
title varchar(20),

PRIMARY KEY(id)
)

CREATE TABLE edition(
ISBN int IDENTITY(1003249860, 3),
book_id int,
year int,
publisher_id int

PRIMARY KEY (ISBN),
FOREIGN KEY (book_id) REFERENCES books(id),
FOREIGN KEY (publisher_id) REFERENCES publishers(id)
)

CREATE TABLE orders(
id int IDENTITY(1,1),
ISBN  int,
store_id int

PRIMARY KEY (id),
FOREIGN KEY (ISBN) REFERENCES edition(ISBN),
FOREIGN KEY (store_id) REFERENCES stores(id)
)