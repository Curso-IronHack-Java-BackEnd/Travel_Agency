create table hotel_extras
(
    extra_id    int auto_increment
        primary key,
    extra_price decimal(38, 2) null,
    hotel_id    bigint         null,
    description varchar(255)   null,
    name        varchar(255)   not null,
    constraint FKp9iwidowc0ba5a18k2fi4v285
        foreign key (hotel_id) references hotels (hotel_id)
);

