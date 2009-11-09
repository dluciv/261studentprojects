/* Formatted on 2009/11/09 01:19 (Formatter Plus v4.8.5) */
--Список всех заказчиков и прикрепленных к ним сопровождающих (только если есть сопровождающие)

SELECT c.NAME, e.NAME
  FROM maintancer m
     , employee e
     , customer c
 WHERE c.maintance = m.ID(+) AND m.ID = e.ID(+);


--Список всех соответствий заказчик-сопровождающий 
--(все заказчики, которые имеют сопровождающих)

 -- только идентификаторы
SELECT maintancer.ID, customer.NAME
  FROM maintancer INNER JOIN customer ON customer.maintance = maintancer.ID
       ;
 -- с именами
SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
  FROM maintancer INNER JOIN customer ON customer.maintance = maintancer.ID
       ;


--Список всех соответствий заказчик-сопровождающий, включая незадействованных нигде сопровождающих

SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
  FROM maintancer LEFT JOIN customer ON customer.maintance = maintancer.ID
       ;


--Список всех соответствий заказчик-сопровождающий, включающий заказчиков без прикрепленных сопровождающих

SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
/* Formatted on 2009/11/09 01:19 (Formatter Plus v4.8.5) */
--Список всех заказчиков и прикрепленных к ним сопровождающих (только если есть сопровождающие)

SELECT c.NAME, e.NAME
  FROM maintancer m
     , employee e
     , customer c
 WHERE c.maintance = m.ID(+) AND m.ID = e.ID(+);


--Список всех соответствий заказчик-сопровождающий 
--(все заказчики, которые имеют сопровождающих)

 -- только идентификаторы
SELECT maintancer.ID, customer.NAME
  FROM maintancer INNER JOIN customer ON customer.maintance = maintancer.ID
       ;
 -- с именами
SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
  FROM maintancer INNER JOIN customer ON customer.maintance = maintancer.ID
       ;


--Список всех соответствий заказчик-сопровождающий, включая незадействованных нигде сопровождающих

SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
  FROM maintancer LEFT JOIN customer ON customer.maintance = maintancer.ID
       ;


--Список всех соответствий заказчик-сопровождающий, включающий заказчиков без прикрепленных сопровождающих

SELECT (SELECT e.NAME
          FROM employee e
         WHERE e.ID = maintancer.ID) AS maintancer_name, customer.NAME
  FROM maintancer RIGHT JOIN customer ON customer.maintance = maintancer.ID