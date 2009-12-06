create or replace view employee_extended as
select em.* 
	   , (select count (*) from maintancer m where m.id = em.ID) as is_maintancer
	   , (select count (*) from tester m where m.id = em.ID) as is_tester
	   , (select count (*) from programmer m where m.id = em.ID) as is_programmer
	   , (select count (*) from tech_writer m where m.id = em.ID) as is_tech_writer
from employee em;

create or replace view programmer_languages as 
select p.id, e.name, l.id as lang_id, l.name as lang_name
  from programmer p, employee e, devel_lang d, prog_language l
 where p.id = e.id and p.id = d.progID and d.langID = l.ID;
 
create or replace view writer_languages as 
select p.id, e.name, l.id as lang_id, l.name as lang_name
  from tech_writer p, employee e, write_lang d, language l
 where p.id = e.id and p.id = d.writerID and d.langID = l.ID;
 
 create or replace view application_employee as 
select a.id as application_id, a.name as application_name, 
	   e.id as employee_id, e.name as eployee_name 
from application a, development d, employee e
where a.id = d.applicationID and e.id = d.employeeID;
 
create or replace view application_extended as
select em.* 
	   , (select count (*) from inner_application m where m.id = em.ID) as is_inner
	   , (select count (*) from free_application m where m.id = em.ID) as is_free
	   , (select count (*) from optional_application m where m.id = em.ID) as is_optional
	   , (select count (*) from individual_application m where m.id = em.ID) as is_individual
from application em;

create or replace view indiviaual_applic_customer as
select a.id as application_id, appl.name as application_name, c.id as customer_id, c.name as customer_name   
from individual_application a, customer c, application appl 
where a.customerId = c.id and a.id = appl.id; 

create or replace view application_customer as
select a.id as application_id, a.name as application_name, c.id as customer_id, c.name as customer_name
from application a, customer c, installed i
where a.id = i.applicationID and c.id = i.customerID;