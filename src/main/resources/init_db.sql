grant usage on schema public to public;
grant create on schema public to public;
grant usage on schema schedule to public;
grant create on schema schedule to public;
delete from schedule.sc_user s where s.e_mail like ('%senatov%');
INSERT INTO schedule.sc_user (e_mail, first_name, last_name, user_name, password) VALUES ('senatov@outlook.de', 'Iakov', 'Senatov', 'is', 'aaa');
delete from schedule.sc_user s where s.e_mail like ('%senatov%');