CREATE TRIGGER price_check_t ON edition
FOR INSERT
AS 
BEGIN
DECLARE @new_price numeric(6,2)
SELECT @new_price = price FROM inserted
IF (@new_price <= 0)
BEGIN
PRINT 'wrong price'
ROLLBACK
END
END


drop trigger price_check_t

insert into edition(price) values(-1)
delete from edition where price <= 0 

SELECT 
 book_id, year, publisher_id, price, printing FROM edition