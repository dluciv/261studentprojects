/* Formatted on 2009/11/09 01:12 (Formatter Plus v4.8.5) */
-- Удалим все из таблицы разработок (то есть удалим полностью одну связь многие-ко-многим)

DELETE FROM development;

-- Удалим запись из таблицы приложений. Благодаря триггеру, она будет удалена и из дочерней таблицы

DELETE FROM application
      WHERE LOWER (NAME) = 'laboratory';

-- Удалим из заказчиков всех, для кого нет сопровождающего

DELETE FROM customer
      WHERE maintance IS NULL;

-- Обновим запись в таблице 

UPDATE employee
   SET phone = '9602612630'
 WHERE NAME = 'Anastasiya';