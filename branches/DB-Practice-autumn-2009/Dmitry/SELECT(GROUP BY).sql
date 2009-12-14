-- запрос турнирной таблицы
SELECT winsAndDraws.c_name, SUM(wins) AS wins, SUM(draw) AS draws,
SUM(lose) AS lose, SUM(wins)*3 + SUM(draw) AS score, SUM(wins)+SUM(draw)+SUM(lose) AS games
FROM
(SELECT Commands.c_name, 0 AS wins, 1 AS draw, 0 AS lose
FROM Commands, Matchs
WHERE 
(Matchs.first_c = Commands.c_id 
AND 
Matchs.first_score = Matchs.second_score)
OR 
(Matchs.second_c = Commands.c_id
AND
Matchs.first_score = Matchs.second_score)

UNION ALL

SELECT Commands.c_name,1 AS wins, 0 AS draw, 0 AS lose
FROM Commands, Matchs
WHERE 
(Matchs.first_c = Commands.c_id 
AND 
Matchs.first_score > Matchs.second_score)
OR 
(Matchs.second_c = Commands.c_id
AND
Matchs.first_score < Matchs.second_score)

UNION ALL

SELECT Commands.c_name,0 AS wins, 0 AS draw, 1 AS lose
FROM Commands, Matchs
WHERE 
(Matchs.first_c = Commands.c_id 
AND 
Matchs.first_score < Matchs.second_score)
OR 
(Matchs.second_c = Commands.c_id
AND
Matchs.first_score > Matchs.second_score)) winsAndDraws
GROUP BY winsAndDraws.c_name
ORDER BY score DESC