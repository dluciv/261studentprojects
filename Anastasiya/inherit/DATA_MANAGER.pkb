/* Formatted on 2009/11/09 01:05 (Formatter Plus v4.8.5) */
CREATE OR REPLACE PACKAGE BODY data_manager
AS
   FUNCTION insert_abstract_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
   )
      RETURN application.ID%TYPE
   AS
      newid                         application.ID%TYPE;
   BEGIN
      SELECT applic_id.NEXTVAL
        INTO newid
        FROM DUAL;

      INSERT INTO application
                  (ID, NAME, description
                  )
           VALUES (newid, n_name, n_desc
                  );

      RETURN newid;
   END insert_abstract_applic;

   FUNCTION insert_inner_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_access                   IN       inner_application.outer_access%TYPE
   )
      RETURN application.ID%TYPE
   AS
      newid                         application.ID%TYPE;
   BEGIN
      newid := insert_abstract_applic (n_name, n_desc);

      INSERT INTO inner_application
                  (ID, outer_access
                  )
           VALUES (newid, n_access
                  );

      RETURN newid;
   END insert_inner_applic;

   FUNCTION insert_individual_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_price                    IN       individual_application.price%TYPE
    , n_cust                     IN       customer.ID%TYPE
   )
      RETURN application.ID%TYPE
   AS
      newid                         application.ID%TYPE;
   BEGIN
      newid := insert_abstract_applic (n_name, n_desc);

      INSERT INTO individual_application
                  (ID, price, customerid
                  )
           VALUES (newid, n_price, n_cust
                  );

      RETURN newid;
   END insert_individual_applic;

   FUNCTION insert_free_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_required                 IN       free_application.required%TYPE
   )
      RETURN application.ID%TYPE
   AS
      newid                         application.ID%TYPE;
   BEGIN
      newid := insert_abstract_applic (n_name, n_desc);

      INSERT INTO outer_application
                  (ID
                  )
           VALUES (newid
                  );

      INSERT INTO free_application
                  (ID, required
                  )
           VALUES (newid, n_required
                  );

      RETURN newid;
   END insert_free_applic;

   FUNCTION insert_optional_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_price                    IN       optional_application.price%TYPE
   )
      RETURN application.ID%TYPE
   AS
      newid                         application.ID%TYPE;
   BEGIN
      newid := insert_abstract_applic (n_name, n_desc);

      INSERT INTO outer_application
                  (ID
                  )
           VALUES (newid
                  );

      INSERT INTO optional_application
                  (ID, price
                  )
           VALUES (newid, n_price
                  );

      RETURN newid;
   END insert_optional_applic;

   FUNCTION insert_abstract_employee (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
   )
      RETURN employee.ID%TYPE
   AS
      newid                         employee.ID%TYPE;
   BEGIN
      SELECT employee_id.NEXTVAL
        INTO newid
        FROM DUAL;

      INSERT INTO employee
                  (ID, NAME, phone, icq, email, jabber
                  )
           VALUES (newid, n_name, n_phone, n_icq, n_mail, n_jabber
                  );

      RETURN newid;
   END insert_abstract_employee;

   FUNCTION insert_tech_writer (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
   )
      RETURN employee.ID%TYPE
   AS
      newid                         employee.ID%TYPE;
   BEGIN
      newid :=
          insert_abstract_employee (n_name
                                  , n_phone
                                  , n_icq
                                  , n_mail
                                  , n_jabber
                                   );

      INSERT INTO tech_writer
                  (ID
                  )
           VALUES (newid
                  );

      RETURN newid;
   END insert_tech_writer;

   FUNCTION insert_maintancer (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
   )
      RETURN employee.ID%TYPE
   AS
      newid                         employee.ID%TYPE;
   BEGIN
      newid :=
          insert_abstract_employee (n_name
                                  , n_phone
                                  , n_icq
                                  , n_mail
                                  , n_jabber
                                   );

      INSERT INTO maintancer
                  (ID
                  )
           VALUES (newid
                  );

      RETURN newid;
   END insert_maintancer;

   FUNCTION insert_tester (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
   )
      RETURN employee.ID%TYPE
   AS
      newid                         employee.ID%TYPE;
   BEGIN
      newid :=
          insert_abstract_employee (n_name
                                  , n_phone
                                  , n_icq
                                  , n_mail
                                  , n_jabber
                                   );

      INSERT INTO tester
                  (ID
                  )
           VALUES (newid
                  );

      RETURN newid;
   END insert_tester;

   FUNCTION insert_programmer (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
    , n_exp                      IN       programmer.experience%TYPE
   )
      RETURN employee.ID%TYPE
   AS
      newid                         employee.ID%TYPE;
   BEGIN
      newid :=
          insert_abstract_employee (n_name
                                  , n_phone
                                  , n_icq
                                  , n_mail
                                  , n_jabber
                                   );

      INSERT INTO programmer
                  (ID, experience
                  )
           VALUES (newid, n_exp
                  );

      RETURN newid;
   END insert_programmer;

   FUNCTION insert_customer (
      n_name                     IN       customer.NAME%TYPE
    , n_dep                      IN       customer.depart%TYPE
    , n_phone                    IN       customer.phone%TYPE
    , n_maintancer               IN       maintancer.ID%TYPE
   )
      RETURN customer.ID%TYPE
   AS
      newid                         customer.ID%TYPE;
   BEGIN
      SELECT customer_id.NEXTVAL
        INTO newid
        FROM DUAL;

      INSERT INTO customer
                  (ID, NAME, phone, depart, maintance
                  )
           VALUES (newid, n_name, n_phone, n_dep, n_maintancer
                  );

      RETURN newid;
   END insert_customer;
END data_manager;
/