SELECT * FROM cars LEFT JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars RIGHT JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars FULL JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars INNER JOIN parking_places 
ON cars.id = parking_places.id

SELECT * FROM cars CROSS JOIN parking_places 
