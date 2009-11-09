/* Formatted on 2009/11/09 01:06 (Formatter Plus v4.8.5) */
CREATE OR REPLACE PACKAGE data_manager
AS
   FUNCTION insert_abstract_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
   )
      RETURN application.ID%TYPE;

   FUNCTION insert_inner_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_access                   IN       inner_application.outer_access%TYPE
   )
      RETURN application.ID%TYPE;

   FUNCTION insert_individual_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_price                    IN       individual_application.price%TYPE
    , n_cust                     IN       customer.ID%TYPE
   )
      RETURN application.ID%TYPE;

   FUNCTION insert_free_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_required                 IN       free_application.required%TYPE
   )
      RETURN application.ID%TYPE;

   FUNCTION insert_optional_applic (
      n_name                     IN       application.NAME%TYPE
    , n_desc                     IN       application.description%TYPE
    , n_price                    IN       optional_application.price%TYPE
   )
      RETURN application.ID%TYPE;

   FUNCTION insert_abstract_employee (
      n_name                     IN       employee.NAME%TYPE
    , n_phone                    IN       employee.phone%TYPE
    , n_icq                      IN       employee.icq%TYPE
    , n_mail                     IN       employee.email%TYPE
    , n_jabber                   IN       employee.jabber%TYPE
   )
      RETURN employee.ID%TYPE;

--   FUNCTION insert_tech_writer(n_name in employee.name%type
--                           ,n_phone in employee.phone%type
--                        ,n_icq in employee.icq%type
--                        ,n_mail in employee.email%type
--                        ,n_jabber in employee.jabber%type)
--                    return employee.id%type;
--
--   FUNCTION insert_maintancer (n_name in employee.name%type
--                           ,n_phone in employee.phone%type
--                        ,n_icq in employee.icq%type
--                        ,n_mail in employee.email%type
--                        ,n_jabber in employee.jabber%type)
--                    return employee.id%type;
--   FUNCTION insert_tester     (n_name in employee.name%type
--                           ,n_phone in employee.phone%type
--                        ,n_icq in employee.icq%type
--                        ,n_mail in employee.email%type
--                        ,n_jabber in employee.jabber%type)
--                    return employee.id%type;
--   FUNCTION insert_programmer  (n_name in employee.name%type
--                           ,n_phone in employee.phone%type
--                        ,n_icq in employee.icq%type
--                        ,n_mail in employee.email%type
--                        ,n_jabber in employee.jabber%type
--                        ,n_exp in programmer.experience%type)
--                    return employee.id%type;
--
   FUNCTION insert_customer (
      n_name                     IN       customer.NAME%TYPE
    , n_dep                      IN       customer.depart%TYPE
    , n_phone                    IN       customer.phone%TYPE
    , n_maintancer               IN       maintancer.ID%TYPE
   )
      RETURN customer.ID%TYPE;
END data_manager;
/