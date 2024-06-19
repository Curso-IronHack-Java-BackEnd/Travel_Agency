create table customers
(
    date_of_birth date         not null,
    customer_id   bigint auto_increment
        primary key,
    phone_number  varchar(15)  not null,
    address       varchar(255) not null,
    email         varchar(255) not null,
    first_name    varchar(255) not null,
    last_name     varchar(255) null
);

