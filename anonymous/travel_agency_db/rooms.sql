create table rooms
(
    price_per_night decimal(38, 2)                                                 not null,
    hotel_id        bigint                                                         null,
    room_number     varchar(255)                                                   not null
        primary key,
    room_type       enum ('DOUBLE', 'FAMILY', 'SINGLE', 'SUITE', 'TRIPLE', 'TWIN') not null,
    status          enum ('AVAILABLE', 'OUT_OF_SERVICE', 'RESERVED')               not null,
    constraint FKp5lufxy0ghq53ugm93hdc941k
        foreign key (hotel_id) references hotels (hotel_id)
);

