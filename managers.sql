create table managers
(
    years_of_experience int          null,
    manager_id          bigint auto_increment
        primary key,
    phone_number        varchar(15)  not null,
    department          varchar(255) not null,
    email               varchar(255) not null,
    first_name          varchar(255) not null,
    last_name           varchar(255) null
);

