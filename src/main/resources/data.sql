truncate TABLE schedule_db.sc_user cascade;
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('senatov@gamai.de', 'Iakov', 'Senatov', 'senatov');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('ronald.reigan@gamai.de', 'Ronny', 'Gonny', 'ronny');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('george.wn@gamai.de', 'georgiw', 'bush', 'bush');

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('room123-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr1', true, '1',
        '2', 'room123-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('WXC Lab 2-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr2', true, '2',
        '2', 'WXC Lab 2-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('WXC Lab-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr3', true, '3',
        '2', 'WXC Lab-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('room123-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr4', true, '4',
        '2', 'room123-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('Kantine-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr4', true, '5',
        '2', 'Kantine-title', 'url1', null);

INSERT INTO schedule_db.sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url, user_name_id)
VALUES ('Meeting Room 34-descr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gr4', true, '5',
        '2', 'Meeting Room 34-title', 'url1', null);

update schedule_db.sc_schedule set user_name_id=( select u.id from schedule_db.sc_user u where user_name like '%senatov%') where title like ('%room123%');
update schedule_db.sc_schedule set user_name_id=( select u.id from schedule_db.sc_user u where user_name like '%ronny%') where title like ('%Kantine%');
update schedule_db.sc_schedule set user_name_id=( select u.id from schedule_db.sc_user u where user_name like '%bush%') where title like ('%Meet%');
update schedule_db.sc_schedule set user_name_id=( select u.id from schedule_db.sc_user u where user_name like '%senatov%') where title like ('%WXC%');



