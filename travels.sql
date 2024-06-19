create table travels
(
    final_price    decimal(38, 2) null,
    customer_id    bigint         null,
    travel_id      bigint auto_increment
        primary key,
    destination    varchar(255)   null,
    duration       varchar(255)   null,
    reservation_id varchar(255)   null,
    constraint UK3gatrbb3a3b5vnfivoskl98cj
        unique (reservation_id),
    constraint FK3bskbjk539qje74j8kf7xdxl1
        foreign key (customer_id) references customers (customer_id),
    constraint FKtrvp5this75t6jt6rj93od7u5
        foreign key (reservation_id) references reservations (reservation_code)
);

