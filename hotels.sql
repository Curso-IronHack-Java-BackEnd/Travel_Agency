create table hotels
(
    breakfast_include bit                                                                                                                           null,
    number_of_rooms   int                                                                                                                           null,
    rating            decimal(2, 1)                                                                                                                 null,
    hotel_id          bigint auto_increment
        primary key,
    travel_id         bigint                                                                                                                        null,
    phone_number      varchar(15)                                                                                                                   not null,
    address           varchar(255)                                                                                                                  not null,
    city              varchar(255)                                                                                                                  not null,
    country           varchar(255)                                                                                                                  not null,
    email             varchar(255)                                                                                                                  not null,
    name              varchar(255)                                                                                                                  not null,
    hotel_type        enum ('AIRPORT', 'APARTMENT', 'BEACH', 'FAMILY', 'INN', 'LOW_COST', 'MONUMENT', 'MOTEL', 'NATURE', 'SPA', 'TOURIST', 'URBAN') not null,
    constraint FK5rfp6igh84rvloavlkilutl8f
        foreign key (travel_id) references travels (travel_id),
    check ((`rating` >= 0) and (`rating` <= 5))
);

