-- �������
CREATE TABLE Commands(
c_id int IDENTITY (1,1) PRIMARY KEY,
c_name VARCHAR(63),
c_town VARCHAR(63)
)

-- ������
CREATE TABLE Player_list(
p_id int IDENTITY (1,1) PRIMARY KEY,
p_name VARCHAR(63),
c_id int FOREIGN KEY REFERENCES Commands(c_id)
)

-- ���������
CREATE TABLE Transfers(
tr_id int IDENTITY (1,1) PRIMARY KEY,
old_c_id int FOREIGN KEY REFERENCES Commands(c_id),
new_c_id int FOREIGN KEY REFERENCES Commands(c_id),
p_id int FOREIGN KEY REFERENCES Player_list(p_id)
)

-- �����
CREATE TABLE Matchs(
m_id int IDENTITY (1,1) PRIMARY KEY,
first_c int FOREIGN KEY REFERENCES Commands(c_id),
first_score int,
second_c int FOREIGN KEY REFERENCES Commands(c_id),
second_score int,
data DATETIME
) 

-- ����
CREATE TABLE Goals(
g_id int IDENTITY (1,1) PRIMARY KEY,
p_id int FOREIGN KEY REFERENCES Player_list(p_id),
m_id int FOREIGN KEY REFERENCES Matchs(m_id)
)

-- ��������
CREATE TABLE Stadions(
s_id int IDENTITY (1,1) PRIMARY KEY,
c_id int FOREIGN KEY REFERENCES Commands(c_id),
s_name VARCHAR(63),
)
