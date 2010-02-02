CREATE TRIGGER price_check_i ON cars
FOR INSERT
AS 
BEGIN
DECLARE @new_price numeric(9, 2)
SELECT @new_price = price FROM inserted
IF (@new_price <= 0)
BEGIN
PRINT 'wrong price'
ROLLBACK
END
END

INSERT INTO cars(maxspeed, color, price, parking_id, mark_id) 
VALUES ('300', 'grey', -1, 1, 1)

CREATE TRIGGER price_check_u ON cars
FOR UPDATE
AS 
BEGIN
DECLARE @new_price numeric(9, 2)
SELECT @new_price = price FROM inserted
IF (@new_price <= 0)
BEGIN
PRINT 'wrong price'
ROLLBACK
END
END

UPDATE cars SET price = -1