--сколько машин у хоз€ина
SELECT owners.title, count(car_id) AS num_cars FROM ownership, owners WHERE ownership.owner_id = owners.id
GROUP BY owners.title

--средн€€ стоимость машин дл€ производител€
SELECT mark.title, AVG(cars.price) AS avg_printing FROM mark JOIN cars ON mark.id = cars.mark_id
GROUP BY mark.title

--средний возраст владельцев
SELECT AVG(owners.age) AS age FROM owners

-- оличество машин
SELECT count(id) AS num FROM cars

--ќтсортировать по сколько машин каждого цвета
SELECT cars.color, count(cars.id) AS num FROM cars 
GROUP BY cars.color