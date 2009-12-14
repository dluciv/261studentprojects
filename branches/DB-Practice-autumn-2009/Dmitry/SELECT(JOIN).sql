SELECT Commands.c_name, Stadions.s_name FROM Commands LEFT JOIN Stadions 
ON Commands.c_id = Stadions.c_id

SELECT Commands.c_name, Stadions.s_name FROM Commands RIGHT JOIN Stadions 
ON Commands.c_id = Stadions.c_id


SELECT Commands.c_name, Stadions.s_name FROM Commands FULL JOIN Stadions 
ON Commands.c_id = Stadions.c_id


SELECT Commands.c_name, Stadions.s_name FROM Commands INNER JOIN Stadions 
ON Commands.c_id = Stadions.c_id


SELECT Commands.c_name, Stadions.s_name FROM Commands CROSS JOIN Stadions