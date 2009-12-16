--сколько книг у каждого автора
SELECT authors.name, authors.surname, count(*) FROM authors,books Where exists(
SELECT * from authorship where authorship.author_id = authors.id and authorship.book_id = books.id)
GROUP BY authors.name, authors.surname
--средний тираж книги для издательств
SELECT publishers.title, AVG(edition.printing) AS avg_printing FROM publishers JOIN edition ON publishers.id = edition.publisher_id
GROUP BY publishers.title 
--магазины с наибольшим разнообразием книг
CREATE VIEW assort AS 
SELECT a.store_id, COUNT(*)  as assortment FROM stores, (SELECT bc.ISBN, store_id  FROM 
(SELECT book_id,ISBN FROM edition RIGHT JOIN books ON edition.book_id = books.id ) as bc
JOIN orders ON orders.ISBN = bc.ISBN) as a
WHERE exists(SELECT * FROM orders WHERE orders.ISBN = a.ISBN and orders.store_id = stores.id)
GROUP BY a.store_id
SELECT stores.title, assort.assortment FROM stores, assort WHERE assort.store_id = stores.id 
AND assort.assortment = (SELECT MAX(assort.assortment) FROM assort)

