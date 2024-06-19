create table agents
(
    commission_rate decimal(4, 2) null,
    agent_id        bigint auto_increment
        primary key,
    manager_id      bigint        null,
    phone_number    varchar(15)   not null,
    email           varchar(255)  not null,
    first_name      varchar(255)  not null,
    last_name       varchar(255)  null,
    specialization  varchar(255)  null,
    constraint FKmwkndkbls7456s05cwg3dvwmm
        foreign key (manager_id) references managers (manager_id),
    check ((`commission_rate` >= 1) and (`commission_rate` <= 15))
);

