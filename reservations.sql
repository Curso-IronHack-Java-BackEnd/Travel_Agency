create table reservations
(
    adults              int                                                                                                                     not null,
    children            int                                                                                                                     null,
    price               decimal(38, 2)                                                                                                          not null,
    agent_id            bigint                                                                                                                  null,
    customer_id         bigint                                                                                                                  null,
    date_of_reservation datetime(6)                                                                                                             null,
    reservation_code    varchar(255)                                                                                                            not null
        primary key,
    payment_method      enum ('BANK_TRANSFER', 'CASH', 'CREDIT_CARD', 'DEBIT_CARD', 'OTHER', 'PAYPAL')                                          not null,
    promotions          enum ('EARLY_BOOKING', 'EMPLOYEE', 'GROUP', 'LARGE_FAMILY', 'LONG_STAY', 'MEMBER', 'NONE', 'PROMOTIONAL_CODE', 'YOUNG') not null,
    reservation_status  enum ('CANCELLED', 'CONFIRMED', 'ON_HOLD', 'PENDING')                                                                   not null,
    constraint FK5fsahuv9gg0nxspw1npsbu1r5
        foreign key (agent_id) references agents (agent_id),
    constraint FK8eccffekcj27jkdiyw2e9r8ks
        foreign key (customer_id) references customers (customer_id)
);

