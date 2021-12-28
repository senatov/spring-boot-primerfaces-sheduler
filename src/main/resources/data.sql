------------------
-- insert users
------------------
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('senatov@gamai.de', 'Iakov', 'Senatov', 'senatov');
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('ronald.reigan@gamai.de', 'Ronny', 'Gonny', 'ronny');
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('george.wn@gamai.de', 'georgiw', 'bush', 'bush');
INSERT INTO SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME)
VALUES ('lasty.tasty@example.org', 'lasty001', 'lasty', 'tasty');



------------------
-- insert schedules
------------------
INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('roomXYZ-title',
        date_trunc('hour', current_timestamp) - interval '58 hour',
        date_trunc('hour', current_timestamp) - interval '54 hour',
        'gr2', true,
        '1',
        '2',
        'roomXYZ-title',
        '',
        1,
        Now(),
        Now());

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('Zimmer ABCCDE',
        date_trunc('hour', current_timestamp) + interval '64 hour',
        date_trunc('hour', current_timestamp) + interval '66 hour',
        'gr3',
        true,
        '2',
        '2',
        'Zimmer ABCCDE-title',
        '',
        2,
        Now(),
        Now());

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('WXC Lab 2-descr',
        date_trunc('hour', current_timestamp) + interval '122 hour  20 minutes',
        date_trunc('hour', current_timestamp) + interval '126 hour 10 minutes',
        'gr4',
        true,
        '3',
        '4',
        'WXC Lab 2-title',
        '',
        1,
        Now(),
        Now());

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('room345-descr',
        date_trunc('hour', current_timestamp) - interval '12 hour',
        date_trunc('hour', current_timestamp) - interval '10 hour',
        'gr5',
        true,
        '4',
        '2',
        'room345-title',
        '',
        1,
        Now(),
        Now());

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('Kantine-descr',
        date_trunc('hour', current_timestamp) + interval '20 hour 00 minutes',
        date_trunc('hour', current_timestamp) + interval '22 hour 30 minutes',
        'gr6',
        true,
        '5',
        '2',
        'Kantine-title',
        '',
        1,
        Now(),
        Now());

INSERT INTO sc_schedule (description, start_date, end_date, group_id, is_editable,
                         schedule_id,
                         style_class,
                         title, url,
                         user_entity_id,
                         createdat,
                         modifiedat)
VALUES ('Meeting Room 34-descr',
        date_trunc('hour', current_timestamp) + interval '32 hour 30 minutes',
        date_trunc('hour', current_timestamp) + interval '36 hour 30 minutes',
        'gr7',
        true,
        '6',
        '1',
        'Meeting Room 34-title',
        '',
        1,
        Now(),
        Now());



------------------
-- update FK link;
------------------
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%senatov%')
where title like ('%room123%');
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%ronny%')
where title like ('%room345%');
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%ronny%')
where title like ('%Kan%');
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%senatov%')
where title like ('%WXC%');
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%bush%')
where title like ('%Meet%');
update sc_schedule
set user_entity_id=(select u.id from sc_user u where user_name like '%bush%')
where title like ('%Meet%');
