/* Formatted on 2009/11/09 01:16 (Formatter Plus v4.8.5) */
DECLARE
   added                         application.ID%TYPE;
BEGIN
   added :=
      data_manager.insert_free_applic
                ('ArmAdministrator'
               , 'Управление сущностями базы данных на уровне общей системы'
               , 0
                );
   added :=
      data_manager.insert_free_applic ('ArmSysAdministrator'
                                     , 'Разворачивает общую структуру базы'
                                     , 1
                                      );
   added := data_manager.insert_inner_applic ('ReqSystem'
                                            , 'Баг-трекер'
                                            , 1
                                             );
   added :=
      data_manager.insert_optional_applic ('ArmRegistratura'
                                         , 'Регистратура поликлиники'
                                         , 100
                                          );
   added :=
      data_manager.insert_optional_applic ('ArmKoikoFond'
                                         , 'Учет мест в стационаре'
                                         , 300
                                          );
   added :=
      data_manager.insert_optional_applic ('ArmEconomist'
                                         , 'Работа с платными услугами'
                                         , 250
                                          );
   added :=
      data_manager.insert_optional_applic ('ArmFinansist'
                                         , 'Бухгалтерский учет поликлиники'
                                         , 530
                                          );
   added :=
      data_manager.insert_optional_applic ('ArmContent'
                                         , 'Работа со справочниками'
                                         , 890
                                          );
END;
/

DECLARE
   added                         employee.ID%TYPE;
BEGIN
   added := data_manager.insert_abstract_employee ('Volodya'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added :=
          data_manager.insert_abstract_employee ('Anastasiya'
                                               , ''
                                               , ''
                                               , ''
                                               , ''
                                                );
   added :=
           data_manager.insert_abstract_employee ('Vladislav'
                                                , ''
                                                , ''
                                                , ''
                                                , ''
                                                 );
   added := data_manager.insert_abstract_employee ('Dmitry'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added := data_manager.insert_abstract_employee ('Sergey'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added := data_manager.insert_abstract_employee ('Olga'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added := data_manager.insert_abstract_employee ('Alexey'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added := data_manager.insert_abstract_employee ('Elena'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added :=
           data_manager.insert_abstract_employee ('Alexander'
                                                , ''
                                                , ''
                                                , ''
                                                , ''
                                                 );
   added := data_manager.insert_abstract_employee ('Ulia'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
   added := data_manager.insert_abstract_employee ('Ramiz'
                                                 , ''
                                                 , ''
                                                 , ''
                                                 , ''
                                                  );
END;
/

INSERT INTO tech_writer
            (ID)
   (SELECT e.ID
      FROM employee e
     WHERE e.NAME = 'Elena');
INSERT INTO tester
            (ID)
   (SELECT e.ID
      FROM employee e
     WHERE e.NAME = 'Elena');
INSERT INTO maintancer
            (ID)
   (SELECT e.ID
      FROM employee e
     WHERE e.NAME = 'Elena');
INSERT INTO maintancer
            (ID)
   (SELECT e.ID
      FROM employee e
     WHERE e.NAME = 'Ulia' OR e.NAME = 'Ramiz' OR e.NAME = 'Alexander'
           OR e.NAME = 'Olga' OR e.NAME = 'Alexey');

INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Volodya'), 7
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Sergey'), 5
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Anastasiya'), 2
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Vladislav'), 1
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Dmitry'), 1
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Alexey'), 4
            );
INSERT INTO programmer
            (ID, experience
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE e.NAME = 'Olga'), 1
            );

DECLARE
   added                         customer.ID%TYPE;
   maint_id                      maintancer.ID%TYPE;
BEGIN
   SELECT m.ID
     INTO maint_id
     FROM employee e
        , maintancer m
    WHERE e.NAME = 'Olga' AND e.ID = m.ID AND ROWNUM = 1;

   added := data_manager.insert_customer ('med122'
                                        , 1
                                        , ''
                                        , maint_id
                                         );
   added := data_manager.insert_customer ('1med'
                                        , 1
                                        , ''
                                        , maint_id
                                         );

   SELECT m.ID
     INTO maint_id
     FROM employee e
        , maintancer m
    WHERE e.NAME = 'Elena' AND e.ID = m.ID AND ROWNUM = 1;

   added := data_manager.insert_customer ('semashko'
                                        , 1
                                        , ''
                                        , maint_id
                                         );
   added := data_manager.insert_customer ('detakaya'
                                        , 1
                                        , ''
                                        , maint_id
                                         );

   SELECT m.ID
     INTO maint_id
     FROM employee e
        , maintancer m
    WHERE e.NAME = 'Alexander' AND e.ID = m.ID AND ROWNUM = 1;

   added := data_manager.insert_customer ('arhangelsk'
                                        , 2
                                        , ''
                                        , maint_id
                                         );
   added := data_manager.insert_customer ('biohimmak'
                                        , 1
                                        , ''
                                        , NULL
                                         );
END;
/

DECLARE
   added                         application.ID%TYPE;
   cust                          customer.ID%TYPE;
BEGIN
   SELECT ID
     INTO cust
     FROM customer
    WHERE TRIM (LOWER (NAME)) = 'biohimmak';

   added :=
      data_manager.insert_individual_applic ('laboratory'
                                           , 'Лабораторная система'
                                           , 100000
                                           , cust
                                            );
END;
/

INSERT INTO LANGUAGE
     VALUES (la.NEXTVAL, 'Uk', 'Украинский язык');
INSERT INTO LANGUAGE
     VALUES (la.NEXTVAL, 'Ru', 'Русский язык');

INSERT INTO prog_language
     VALUES (pr_la.NEXTVAL, 'SQL', 'Oracle SQL');
INSERT INTO prog_language
     VALUES (pr_la.NEXTVAL, 'Java SE', 'Java 2 Standard Edition');
INSERT INTO prog_language
     VALUES (pr_la.NEXTVAL, 'Java EE', 'Java 2 Enterprise Edition');
INSERT INTO prog_language
     VALUES (pr_la.NEXTVAL, 'C++', 'C/C++');

-- Тут уже просто декартово произведение. а то лень что-то выписывать.
INSERT INTO installed
            (applicationid, customerid)
   (SELECT a.ID, c.ID
      FROM customer c
         , free_application a
     WHERE TRIM (LOWER (c.NAME)) <> 'biohimmak');

INSERT INTO installed
            (applicationid, customerid)
   (SELECT a.ID, c.ID
      FROM customer c
         , optional_application a
     WHERE TRIM (LOWER (c.NAME)) <> 'biohimmak');

INSERT INTO devel_lang
            (progid, langid)
   (SELECT p.ID, l.ID
      FROM programmer p
         , LANGUAGE l
     WHERE l.NAME = 'SQL');
INSERT INTO devel_lang
            (progid, langid)
   (SELECT p.ID, l.ID
      FROM programmer p
         , LANGUAGE l
         , employee e
     WHERE LOWER (l.NAME) = 'java ee' AND e.ID = p.ID AND e.NAME = 'Volodya');
INSERT INTO devel_lang
            (progid, langid)
   (SELECT p.ID, l.ID
      FROM programmer p
         , LANGUAGE l
         , employee e
     WHERE LOWER (l.NAME) = 'java se' AND e.ID = p.ID
           AND (e.NAME = 'Volodya' OR e.NAME = 'Anastasiya'
                OR e.NAME = 'Vladislav' OR e.NAME = 'Dmitry'
               ));
INSERT INTO devel_lang
            (progid, langid)
   (SELECT p.ID, l.ID
      FROM programmer p
         , LANGUAGE l
         , employee e
     WHERE LOWER (l.NAME) = 'c++' AND e.ID = p.ID
           AND (e.NAME = 'Volodya' OR e.NAME = 'Alexey' OR e.NAME = 'Olga'
                OR e.NAME = 'Sergey'
               ));
INSERT INTO write_lang
            (writerid, langid)
   (SELECT t.ID, l.ID
      FROM tech_writer t
         , LANGUAGE l);

INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'anastasiya')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armadministrator')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'volodya')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'reqsystem')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'volodya')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armsysadministrator')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'sergey')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armeconomist')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'dmitry')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'laboratory')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'volodya')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armcontent')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'dmitry')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armcontent')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'vladislav')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armcontent')
            );
INSERT INTO development
            (employeeid
           , applicationid
            )
     VALUES ((SELECT e.ID
                FROM employee e
               WHERE TRIM (LOWER (e.NAME)) = 'olga')
           , (SELECT ID
                FROM application
               WHERE TRIM (LOWER (NAME)) = 'armfinansist')
            );
INSERT INTO development
            (employeeid, applicationid)
   (SELECT e.ID, a.ID
      FROM employee e
         , application a
     WHERE TRIM (LOWER (e.NAME)) = 'elena');

INSERT INTO development
            (employeeid, applicationid)
   (SELECT e.ID, a.ID
      FROM maintancer e
         , free_application a 
		 
     MINUS

	SELECT employeeID, applicationID
      FROM development);
INSERT INTO development
            (employeeid, applicationid)
   (SELECT e.ID, a.ID
      FROM maintancer e
         , optional_application a
		 
     MINUS

	SELECT employeeID, applicationID
      FROM development);
