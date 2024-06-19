create table flights
(
    date_of_flight date                                                           not null,
    duration       int                                                            null,
    mileage        int                                                            null,
    arrival_time   datetime(6)                                                    null,
    departure_time datetime(6)                                                    null,
    flight_id      bigint auto_increment
        primary key,
    travel_id      bigint                                                         null,
    airline        varchar(255)                                                   null,
    destination    varchar(255)                                                   not null,
    flight_number  varchar(255)                                                   not null,
    origin         varchar(255)                                                   not null,
    seat_number    varchar(255)                                                   not null,
    ticket_class   enum ('BUSINESS', 'ECONOMY', 'FIRST_CLASS', 'PREMIUM_ECONOMY') not null,
    constraint FKh7bv0m74ylp3ugb699572kjuc
        foreign key (travel_id) references travels (travel_id)
);

