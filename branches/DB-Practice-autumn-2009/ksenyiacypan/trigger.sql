CREATE OR REPLACE TRIGGER check_starosta
   BEFORE INSERT OR UPDATE
   ON groups
   REFERENCING NEW AS newrow
   FOR EACH ROW
DECLARE
   tmp  NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO tmp
     FROM students s
    WHERE s.student_ID = :newrow.starosta_ID and s.group_ID = :newrow.group_ID;

   IF tmp = 0
   THEN
      raise_application_error
         (-20000
        , 'error: WRONG STAROSTAS GROUP');
   END IF;

END check_starosta;
/