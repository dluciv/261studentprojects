--������� 
CREATE TABLE owners(
id int IDENTITY(1,1),
title varchar(50) NOT NULL,
age int,
PRIMARY KEY (id)
)

--��������
CREATE TABLE gas_stations(
id int IDENTITY(1,1),
address varchar(50) NOT NULL,
PRIMARY KEY (id)
)

--�������
CREATE TABLE parking_places(
id int IDENTITY(1,1),
title varchar(20) NOT NULL,
PRIMARY KEY(id)
)

--���������� �������
CREATE TABLE free(
id int IDENTITY(1,1),
parking_id int,
PRIMARY KEY(id),
FOREIGN KEY(parking_id) REFERENCES parking_places(id)
)

--������� �������
CREATE TABLE paying(
id int IDENTITY(1,1),
tax_in_month char(10),
parking_id int,
PRIMARY KEY(id),
FOREIGN KEY(parking_id) REFERENCES parking_places(id)
)

--����� ������
CREATE TABLE mark (
id int IDENTITY (1,1),
title varchar(50) NOT NULL,
country varchar(50)
PRIMARY KEY (id)
)

--������������ ����� ������
CREATE TABLE mark_child (
id int IDENTITY (1,1),
title varchar(50),
mark_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY(mark_id) REFERENCES mark(id)
ON DELETE CASCADE
)

--������
CREATE TABLE cars (
id int IDENTITY (1,1),
maxspeed varchar(10),
color varchar(10),
price numeric(9,2)
    NOT NULL,
parking_id int,
mark_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY(parking_id) REFERENCES parking_places(id) ON DELETE CASCADE,
FOREIGN KEY(mark_id) REFERENCES mark(id) ON DELETE CASCADE,
)

--����� ������ �� ������ ����� ���������� � ��������
CREATE TABLE refuelling(
id int IDENTITY(1,1),
car_id int NOT NULL,
station_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE,
FOREIGN KEY(station_id) REFERENCES owners(id) ON DELETE CASCADE
)

-- ����� ������ �� ������ ����� �������� � ���������
CREATE TABLE ownership(
id int IDENTITY(1,1),
car_id int NOT NULL,
owner_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE,
FOREIGN KEY(owner_id) REFERENCES owners(id) ON DELETE CASCADE,
)