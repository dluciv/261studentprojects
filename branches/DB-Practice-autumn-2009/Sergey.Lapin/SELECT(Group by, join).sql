--������� ����� � �������
SELECT owners.title, count(car_id) AS num_cars FROM ownership, owners WHERE ownership.owner_id = owners.id
GROUP BY owners.title

--������� ��������� ����� ��� �������������
SELECT mark.title, AVG(cars.price) AS avg_printing FROM mark JOIN cars ON mark.id = cars.mark_id
GROUP BY mark.title

--������� ������� ����������
SELECT AVG(owners.age) AS age FROM owners

--���������� �����
SELECT count(id) AS num FROM cars

--������������� �� ������� ����� ������� �����
SELECT cars.color, count(cars.id) AS num FROM cars 
GROUP BY cars.color