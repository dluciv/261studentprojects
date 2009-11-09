/* Formatted on 2009/11/09 01:19 (Formatter Plus v4.8.5) */

CREATE OR REPLACE TRIGGER check_inh_applic_inner
   BEFORE INSERT OR UPDATE
   ON inner_application
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   id_count                      NUMBER;
BEGIN
   SELECT COUNT (i.ID)
     INTO id_count
     FROM individual_application i
    WHERE i.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
         (-20000
        , 'inheritance error: key exists on ''individual_application'' table');
   END IF;

   SELECT COUNT (o.ID)
     INTO id_count
     FROM outer_application o
    WHERE o.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
              (-20000
             , 'inheritance error: key exists on ''outer_application'' table');
   END IF;
END check_inh_applic_inner;
/
CREATE OR REPLACE TRIGGER check_inh_applic_outer
   BEFORE INSERT OR UPDATE
   ON outer_application
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   id_count                      NUMBER;
BEGIN
   SELECT COUNT (i.ID)
     INTO id_count
     FROM individual_application i
    WHERE i.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
         (-20000
        , 'inheritance error: key exists on ''individual_application'' table');
   END IF;

   SELECT COUNT (o.ID)
     INTO id_count
     FROM inner_application o
    WHERE o.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
              (-20000
             , 'inheritance error: key exists on ''inner_application'' table');
   END IF;
END check_inh_applic_outer;
/
CREATE OR REPLACE TRIGGER check_inh_applic_individual
   BEFORE INSERT OR UPDATE
   ON individual_application
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   id_count                      NUMBER;
BEGIN
   SELECT COUNT (i.ID)
     INTO id_count
     FROM inner_application i
    WHERE i.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
              (-20000
             , 'inheritance error: key exists on ''inner_application'' table');
   END IF;

   SELECT COUNT (o.ID)
     INTO id_count
     FROM inner_application o
    WHERE o.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
              (-20000
             , 'inheritance error: key exists on ''inner_application'' table');
   END IF;
END check_inh_applic_individual;
/

CREATE OR REPLACE TRIGGER check_inh_applic_free
   BEFORE INSERT OR UPDATE
   ON free_application
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   id_count                      NUMBER;
BEGIN
   SELECT COUNT (i.ID)
     INTO id_count
     FROM optional_application i
    WHERE i.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
           (-20000
          , 'inheritance error: key exists on ''optional_application'' table');
   END IF;
END check_inh_applic_free;
/

CREATE OR REPLACE TRIGGER check_inh_applic_optional
   BEFORE INSERT OR UPDATE
   ON optional_application
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   id_count                      NUMBER;
BEGIN
   SELECT COUNT (i.ID)
     INTO id_count
     FROM inner_application i
    WHERE i.ID = :newrow.ID;

   IF id_count <> 0
   THEN
      raise_application_error
              (-20000
             , 'inheritance error: key exists on ''inner_application'' table');
   END IF;
END check_inh_applic_optional;
/