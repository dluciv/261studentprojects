SELECT * FROM cars LEFT JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars RIGHT JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars FULL JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars INNER JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars CROSS JOIN parking_places 

--машины с хозяевами
SELECT car_id, maxspeed, color, price, parking_id, mark_id, title, age FROM 
(SELECT owner_id, car_id, maxspeed, color, price, parking_id, mark_id  FROM 
ownership INNER JOIN cars ON ownership.car_id = cars.id) AS mod_cars INNER JOIN owners
ON mod_cars.owner_id = owners.id


