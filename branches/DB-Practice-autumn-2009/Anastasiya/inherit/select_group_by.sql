/* Formatted on 2009/11/09 01:18 (Formatter Plus v4.8.5) */
-- ������ ���� �����������, ���������� �����-���� �� ������������ ������� ���������� ������ � ����������� ���� ���������� � ��������� �������

SELECT   s.NAME, COUNT (*)
    FROM (SELECT e.NAME
            FROM employee e
               , tester t
           WHERE e.ID = t.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , maintancer m
           WHERE e.ID = m.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , tech_writer w
           WHERE e.ID = w.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , programmer p
           WHERE e.ID = p.ID) s
GROUP BY NAME
ORDER BY COUNT (*) DESC;

-- ����, ��� � � ����������, �� � ������ ��������� ������ ����������, ���������� ����� ����� ���������

SELECT   s.NAME, COUNT (*)
    FROM (SELECT e.NAME
            FROM employee e
               , tester t
           WHERE e.ID = t.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , maintancer m
           WHERE e.ID = m.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , tech_writer w
           WHERE e.ID = w.ID
          UNION ALL
          SELECT e.NAME
            FROM employee e
               , programmer p
           WHERE e.ID = p.ID) s
GROUP BY NAME
  HAVING COUNT (*) > 1
ORDER BY COUNT (*) DESC;

-- ��� ��� ������ ���� ����������� � ���������� ���������� ����������

SELECT   e.NAME
       ,   DECODE (e.ID
                 , t.ID, 1
                 , 0
                  )
         + DECODE (e.ID
                 , w.ID, 1
                 , 0
                  )
         + DECODE (e.ID
                 , p.ID, 1
                 , 0
                  )
         + DECODE (e.ID
                 , m.ID, 1
                 , 0
                  ) AS wrk
    FROM employee e
       , tester t
       , tech_writer w
       , programmer p
       , maintancer m
   WHERE e.ID = t.ID(+) AND e.ID = w.ID(+) AND e.ID = p.ID(+) AND e.ID = m.ID(+)
ORDER BY wrk;

-- ������ ���� ����������� ��� ����������� ����������� � ���������� ����������, ��� �������� ���������� �������� �������������� �� ���������� ����������

SELECT   e.NAME AS worker, COUNT (*) AS applic_count
    FROM development d
       , application a
       , employee e
   WHERE d.applicationid = a.ID AND d.employeeid = e.ID
GROUP BY e.NAME
ORDER BY appl