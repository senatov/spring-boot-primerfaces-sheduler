CREATE DATABASE is WITH ENCODING ='UTF8' OWNER =postgres CONNECTION LIMIT =25;
CREATE SCHEMA IF NOT EXISTS schedule_db;

INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME) VALUES ('senatov@gamai.de', 'Iakov', 'Senatov', 'senatov');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME) VALUES ('ronald.reigan@gamai.de', 'Ronny', 'Gonny', 'ronny');
INSERT INTO schedule_db.SC_USER (E_MAIL, FIRST_NAME, LAST_NAME, USER_NAME) VALUES ('george.wn@gamai.de', 'georgiw', 'bush', 'bush');
