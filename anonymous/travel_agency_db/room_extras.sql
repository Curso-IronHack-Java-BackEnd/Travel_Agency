create table room_extras
(
    extra_id    int auto_increment
        primary key,
    extra_price decimal(38, 2) null,
    description varchar(255)   null,
    name        varchar(255)   not null,
    room_id     varchar(255)   null,
    constraint FKi726ud8np1b41ti7j2v8p86hk
        foreign key (room_id) references rooms (room_number)
);

