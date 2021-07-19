truncate table schedule_db.sc_user restart identity cascade;
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('senatov@gamai.de', 'Iakov', 'Senatov', 'senatov');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('ronald.reigan@gamai.de', 'Ronny', 'Gonny', 'ronny');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('george.wn@gamai.de', 'georgiw', 'bush', 'bush');

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('room123-descr', current_timestamp - interval '43 hour', current_timestamp - interval '40 hour', 'gr1', true, '1',
        '2', 'room123-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('WXC Lab 1-descr', current_timestamp - interval '77 hour 45 minutes', current_timestamp - interval '75 hour 15 minutes', 'gr2', true, '2',
        '2', 'WXC Lab 1-title', 'url2', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('WXC Lab 2-descr', current_timestamp - interval '64 hour 5 minutes', current_timestamp - interval '62 hour 20 minutes', 'gr3', true, '3',
        '2', 'WXC Lab 2-title', 'url3', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('room345-descr', current_timestamp - interval '12 hour', current_timestamp - interval '10 hour', 'gr4', true, '4',
        '2', 'room345-title', 'url4', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('Kantine-descr', current_timestamp + interval '18 hour 35 minutes', current_timestamp + interval '19 hour 12 minutes', 'gr4', true, '5',
        '2', 'Kantine-title', 'url5', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('Meeting Room 34-descr', current_timestamp + interval '32 hour', current_timestamp + interval '34 hour 30 minutes', 'gr4', true, '5',
        '2', 'Meeting Room 34-title', 'url6', null);

update schedule_db.sc_schedule
set user_name_id=(select u.id from schedule_db.sc_user u where user_name like '%senatov%')
where title like ('%room123%');
update schedule_db.sc_schedule
set user_name_id=(select u.id from schedule_db.sc_user u where user_name like '%ronny%')
where title like ('%room345%');
update schedule_db.sc_schedule
set user_name_id=(select u.id from schedule_db.sc_user u where user_name like '%ronny%')
where title like ('%Kantine%');
update schedule_db.sc_schedule
set user_name_id=(select u.id from schedule_db.sc_user u where user_name like '%bush%')
where title like ('%WXC%');
update schedule_db.sc_schedule
set user_name_id=(select u.id from schedule_db.sc_user u where user_name like '%senatov%')
where title = 'Meeting Room 34-title'
