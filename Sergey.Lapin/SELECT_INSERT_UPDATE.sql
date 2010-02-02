SELECT * FROM cars;

-- перекрасить машины владельца
UPDATE cars SET color = 'blue' WHERE 
id = (SELECT id FROM ownership WHERE 
owner_id = (SELECT id FROM owners WHERE title = 'Lapin Sergey'))

SELECT * FROM cars;