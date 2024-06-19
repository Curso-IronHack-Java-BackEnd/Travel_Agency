create table amenities
(
    extra_price     decimal(38, 2) null,
    target_audience int            not null,
    amenity_id      bigint auto_increment
        primary key,
    hotel_id        bigint         null,
    description     varchar(255)   null,
    name            varchar(255)   not null,
    constraint FK23ayav9or91wbe85x0v9svxv1
        foreign key (hotel_id) references hotels (hotel_id)
);

