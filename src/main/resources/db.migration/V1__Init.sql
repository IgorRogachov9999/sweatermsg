create sequence hibernate_sequence start 1 increment 1;

create table message (
    id int8 not null,
    read boolean not null,
    receiver_id int8,
    sender_id int8,
    text varchar(2048),
    timestamp int8,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    active boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists user_role
    add constraint user_role_fk
    foreign key (user_id) references usr;