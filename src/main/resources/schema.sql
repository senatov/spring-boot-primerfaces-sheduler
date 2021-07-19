create table sc_schedule
(
    id           bigint generated by default as identity,
    description  varchar(255),
    end_date     timestamp not null,
    group_id     varchar(255),
    is_editable  boolean default TRUE,
    schedule_id  varchar(255),
    start_date   timestamp not null,
    style_class  varchar(255),
    title        varchar(255),
    url          varchar(255),
    user_name_id bigint,
    primary key (id)
);

create table sc_user
(
    id         bigint generated by default as identity,
    e_mail     varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    user_name  varchar(255),
    primary key (id)
);

create unique index UK_6hyq8432klvcnc5bo3wcmqebg on sc_user (e_mail);

alter table sc_schedule
    add constraint username_fk foreign key (user_name_id) references sc_user;
