------------------
-- insert users
------------------
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('senatov@gamai.de', 'Iakov', 'Senatov', 'senatov');
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('ronald.reigan@gamai.de', 'Ronny', 'Gonny', 'ronny');
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('george.wn@gamai.de', 'georgiw', 'bush', 'bush');

------------------
-- insert schedules
------------------
INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('room123-descr', current_timestamp - interval '58 hour',
        current_timestamp - interval '54 hour',
        'gr1', true,
        '1',
        '2',
        'room123-title',
        'url1');

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('WXC Lab 1-descr', current_timestamp + interval '64 hour',
        current_timestamp + interval '66 hour',
        'gr2',
        true, '2',
        '2',
        'WXC Lab 1-title',
        'url2');

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('WXC Lab 2-descr', current_timestamp + interval '122 hour  20 minutes',
        current_timestamp + interval '126 hour 10 minutes',
        'gr3',
        true, '3',
        '2',
        'WXC Lab 2-title',
        'url3');

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('room345-descr', current_timestamp - interval '12 hour',
        current_timestamp - interval '10 hour',
        'gr4',
         true,
        '4',
        '2',
        'room345-title',
        'url4');

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('Kantine-descr', current_timestamp + interval '20 hour 00 minutes',
        current_timestamp + interval '22 hour 30 minutes',
        'gr4',
        true,
        '5',
        '2',
        'Kantine-title',
        'url5');

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable, schedule_id, style_class, title, url)
VALUES ('Meeting Room 34-descr', current_timestamp + interval '32 hour 30 minutes',
        current_timestamp + interval '36 hour 30 minutes',
        'gr4',
        true,
        '6',
        '1',
        'Meeting Room 34-title',
        'url6');


------------------
-- update FK link;
------------------
update sc_schedule set user_entity_id=(select u.id from sc_user u where user_name like '%senatov%') where title like ('%room123%');
update sc_schedule set user_entity_id=(select u.id from sc_user u where user_name like '%ronny%') where title like ('%room345%');
update sc_schedule set user_entity_id=(select u.id from sc_user u where user_name like '%ronny%') where title like ('%Kan%');
update sc_schedule set user_entity_id=(select u.id from sc_user u where user_name like '%senatov%') where title like ('%WXC%');
update sc_schedule set user_entity_id=(select u.id from sc_user u where user_name like '%bush%') where title like ('%Meet%');
