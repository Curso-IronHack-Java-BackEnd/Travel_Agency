create table customers
(
    customer_id   bigint auto_increment
        primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) null,
    phone_number  varchar(15)  not null,
    email         varchar(255) not null,
    address       varchar(255) not null,
    date_of_birth date         not null
);

create table managers
(
    manager_id          bigint auto_increment
        primary key,
    first_name          varchar(255) not null,
    last_name           varchar(255) null,
    phone_number        varchar(15)  not null,
    email               varchar(255) not null,
    department          varchar(255) not null,
    years_of_experience int          null
);

create table agents
(
    agent_id        bigint auto_increment
        primary key,
    first_name      varchar(255)    not null,
    last_name       varchar(255)    null,
    phone_number    varchar(15)     not null,
    email           varchar(255)    not null,
    specialization  varchar(255)    null,
    commission_rate decimal(4 , 2)  null,
    manager_id      bigint          null,
    constraint FKmwkndkbls7456s05cwg3dvwmm
        foreign key (manager_id) references managers (manager_id),
    check ((`commission_rate` >= 1) and (`commission_rate` <= 15))
);

create table hotels
(
    hotel_id          bigint auto_increment
        primary key,
    name              varchar(255)              not null,
    address           varchar(255)              not null,
    city              varchar(255)              not null,
    country           varchar(255)              not null,
    phone_number      varchar(15)               not null,
    email             varchar(255)              not null,
    rating            decimal(2, 1)             null,
    hotel_type        enum('AIRPORT', 'APARTMENT', 'BEACH', 'FAMILY', 'INN', 'LOW_COST', 'MONUMENT', 'MOTEL', 'NATURE', 'SPA', 'TOURIST', 'URBAN', 'LUXURY') not null,
    number_of_rooms   int                       null,
    price_children    decimal(6,2)              not null,
    price_adult       decimal(6,2)              not null,
    check ((`rating` >= 0) and (`rating` <= 5))
);

create table flights
(
    flight_id      bigint auto_increment
         primary key,
    flight_number  varchar(255)         not null,
    airline        varchar(255)         not null,
    aircraft       varchar(255)         null,
    origin         varchar(255)         not null,
    destination    varchar(255)         not null,
    mileage        int                  null,
    duration       int                  null,
    date_of_flight date                 not null,
    departure_time time(0)              null,
    arrival_time   time(0)              null,
    price          decimal(10,2)        not null,
    seat_number    varchar(255)         not null,
    ticket_class   enum ('BUSINESS', 'ECONOMY', 'FIRST_CLASS', 'PREMIUM_ECONOMY') not null
);

create table reservations
(
    reservation_code    varchar(255)        not null
        primary key,
    adults              int                 not null,
    children            int                 null,
    promotions          enum ('EARLY_BOOKING', 'EMPLOYEE', 'GROUP', 'LARGE_FAMILY', 'LONG_STAY', 'MEMBER', 'NONE', 'PROMOTIONAL_CODE', 'YOUNG') not null,
    payment_method      enum ('BANK_TRANSFER', 'CASH', 'CREDIT_CARD', 'DEBIT_CARD', 'OTHER', 'PAYPAL')                                          not null,
    reservation_status  enum ('CANCELLED', 'CONFIRMED', 'ON_HOLD', 'PENDING')   not null,
    deposit             decimal(10, 2)      not null,
    date_of_reservation datetime(6)         null,
    agent_id            bigint              null,
    customer_id         bigint              null,
    constraint FK5fsahuv9gg0nxspw1npsbu1r5
        foreign key (agent_id) references agents (agent_id),
    constraint FK8eccffekcj27jkdiyw2e9r8ks
        foreign key (customer_id) references customers (customer_id)
);

create table bills
(
    bill_id          bigint auto_increment
        primary key,
    flightBookingPrice      decimal(10, 2)      not null,
    hotelBookingPrice       decimal(10, 2)      null,
    totalBookingPrice       decimal(10, 2)      null
);
create table travels
(
    travel_id           bigint auto_increment
        primary key,
    destination         varchar(255)     null,
    duration            int              not null,
    final_price         decimal(10, 2)   not null,
    customer_id         bigint           null,
    reservation_code    varchar(255)     null,
    bill_id             bigint           null,
    constraint FK3bskbjk539qje74j8kf7xdxl1
        foreign key (customer_id) references customers (customer_id),
    constraint FKtrvp5this75t6jt6rj93od7u5
        foreign key (reservation_code) references reservations (reservation_code),
    constraint billToTravel
        foreign key (bill_id) references bills (bill_id)
);

create table hotel_bookings
(
    hotel_booking_id        bigint auto_increment
        primary key,
    duration                int                     not null,
    hotel_booking_price     decimal(8,2)            not null,
    hotel_id                bigint                  null,
    reservation_code        varchar(255)            null,
    travel_id               bigint                  null,
     constraint hotelToHotelBooking
        foreign key (hotel_id) references hotels (hotel_id),
     constraint reservationToHotelBooking
        foreign key (reservation_code) references reservations (reservation_code),
     constraint travelToHotelBooking
        foreign key (travel_id) references travels (travel_id),
    check (`duration` >= 1)
);

create table flight_bookings
(
    flight_booking_id       bigint auto_increment
        primary key,
    flight_booking_price    decimal(8,2)            not null,
    reservation_code        varchar(255)            null,
    travel_id               bigint                  null,
    flight_id               bigint                  null,
     constraint reservationToFlightBooking
        foreign key (reservation_code) references reservations (reservation_code),
     constraint travelToFlightBooking
        foreign key (travel_id) references travels (travel_id),
     constraint flightToFlightBooking
        foreign key (flight_id) references flights (flight_id)
);


create table amenities
(
    amenity_id          int auto_increment
        primary key,
    name                varchar(255)   not null,
    description         varchar(255)   null,
    hotel_booking_id    bigint         null,
    constraint FK23ayav9or91wbe85x0v9svxv1
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id)
);

create table amenities_at_hotel
(
    amenities_at_hotel_id    int auto_increment
        primary key,
    hotel_booking_id         bigint               null,
    amenity_id               int                  null,

      constraint hotelBookingToAmenity
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id),
      constraint amenityToHotelBooking
        foreign key (amenity_id) references amenities (amenity_id)
);


create table hotel_extras
(
    extra_id    int auto_increment
        primary key,
    name        varchar(255)   not null,
    description varchar(255)   null,
    extra_price decimal(10, 2) null,
    hotel_booking_id    bigint         null,
    constraint FKp9iwidowc0ba5a18k2fi4v285
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id)
);

create table extras_at_hotel
(
    extra_at_hotel_id    int auto_increment
        primary key,
    hotel_booking_id       bigint               null,
    hotel_extra_id               int                  null,

     constraint hotelBookingToExtraHotel
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id),
     constraint extraHotelToHotelBooking
        foreign key (hotel_extra_id) references hotel_extras (extra_id)
);

create table room_extras
(
    extra_id    int auto_increment
        primary key,
    name        varchar(255)   not null,
    description varchar(255)   null,
    extra_price decimal(10, 2) null,
    hotel_booking_id     bigint   null,
      constraint FKi726ud8np1b41ti7j2v8p86hk
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id)
);

create table extras_at_room
(
    extra_at_room_id    int auto_increment
        primary key,
    hotel_booking_id       bigint               null,
    room_extra_id               int                  null,

     constraint hotelBookingToExtraRoom
        foreign key (hotel_booking_id) references hotel_bookings (hotel_booking_id),
     constraint extraRoomToHotelBooking
        foreign key (room_extra_id) references room_extras (extra_id)
);

INSERT INTO customers (first_name, last_name, phone_number, email, address, date_of_birth) VALUES
    ('Angela', 'Rodriguez', '676542335', 'angelarodriguez@hotmail.com', 'C/ Blanca, 12', '1990-02-15'),
    ('Juan Carlos', 'Sanchez', '645223676', 'juancarlossanchez@hotmail.com', 'C/ Culebras, 34', '1999-02-15'),
    ('Sofia', 'Teruel', '908564345', 'sofiateruel@hotmail.com', 'C/ Santo Tomas, 1', '2001-02-15'),
    ('Alba', 'Rojas', '926785676', 'albarojas@gmail.com', 'C/ Luciernaga, 3', '2000-02-15'),
    ('Julio', 'Bermudez', '666478423', 'juliobermudez@gmail.com', 'C/ Melancolia 34', '1989-02-15'),
    ('Marcos', 'Tenorio', '676009897', 'marcostenorio@gmail.com', 'C/ Roma, 33', '1978-02-15');


INSERT INTO managers (first_name, last_name, phone_number, email, department, years_of_experience) VALUES
    ('Lucas', 'Patiño', '676542335', 'lucaspatiño@hotmail.com', 'Sales', 4),
    ('Juan Jose', 'Perez', '645223676', 'juanjoseperez@hotmail.com', 'Operations', 12),
    ('Margarita', 'Garces', '908564345', 'margaritagarces@hotmail.com', 'Marketing', 2),
    ('Maria', 'Jimenez', '926785676', 'mariajimenez@hotmail.com', 'Administration', 6),
    ('Salvador', 'Dominguez', '666478423', 'salvadordominguez@hotmail.com', 'Product', 10),
    ('Lucia', 'Calles', '676009897', 'luciacalles@hotmail.com', 'Sales', 5);


INSERT INTO agents (first_name, last_name, phone_number, email, specialization, commission_rate, manager_id) VALUES
    ('Elena', 'Peña', '676542335', 'elenapeña@hotmail.com', 'Sales', 2.75, 1),
    ('Yago', 'Martin', '645223676', 'yagomartin@hotmail.com', 'Operations', 12.25, 2),
    ('Miguel', 'Payes', '908564345', 'miguelpayes@hotmail.com', 'Operations', 5.33, 2),
    ('Domingo', 'Ruiz', '926785676', 'domingoruiz@hotmail.com', 'Sales', 6.50, 1),
    ('Santiago', 'Gonzalez', '666478423', 'santiagogonzalez@hotmail.com', 'Product', 10.00, 5),
    ('Marta', 'Pozo', '676009897', 'martapozo@hotmail.com', 'Sales', 5.50, 6);


INSERT INTO hotels (name, address, city, country, phone_number, email, rating, hotel_type, number_of_rooms, price_children, price_adult) VALUES
    ('Toledo Imperial', 'Avenida Recoletos 25', 'Toledo', 'España', '+34923495967', 'toledoimperial@gmail.es', 3.5, 'URBAN', 100, 20.00, 30.00),
    ('Indiana', 'C/ Europa, 12', 'Buenos Aires', 'Argentina', '+541146498000', 'indianabuenosaires@gmail.ar', 2.9, 'AIRPORT', 320, 15.00, 35.00),
    ('Heckfield Palace', 'Heckfield Place, 1', 'Hampshire', 'Reino Unido', '+441189326868', 'heckfielPalace@gmail.uk', 4.1, 'MONUMENT', 150, 75.00, 110.00),
    ('Regina Isabella', 'Piazza Santa Restituta, 1', 'Ischia', 'Italia', '+39081994322', 'reginaisabella@gmail.it', 4.5, 'TOURIST', 200, 40.00, 80.00),
    ('Hotel Imperial', 'Karntner Ring 16', 'Viena', 'Austria', '+431501100', 'hotelimperial@gmail.at', 4.0, 'NATURE', 160, 65.00, 90.00),
    ('Palacio Principe Real', 'R. de São Marçal 77', 'Lisboa', 'Portugal', '+351218792000', 'palacioprincipereal@gmail.pt',4.2, 'SPA', 180, 50.00, 75.00),
    ('Jumeirah Burj Al Arab', 'Jumeirah Beach Road', 'Dubai', 'Emiratos Árabes Unidos', '+97143665000', 'website-feedback@jumeirah.com',4.8, 'LUXURY', 300, 950.00, 1199.00),
    ('Park Central Hotel', '870 7Th Avenue', 'New York', 'Estados Unidos', '+12122478000', 'reservations@parkcentralny.com',3.9, 'URBAN', 430, 315.00, 420.00);



INSERT INTO flights (flight_number, airline, aircraft, origin, destination, mileage, duration, date_of_flight, departure_time, arrival_time, price, seat_number, ticket_class) VALUES
    ('IBE3031', 'Iberia', 'Airbus A320', 'Barcelona', 'Madrid', 483, 65, '2024-07-20', '18:15:00', '19:20:00', 79, '47A', 'ECONOMY'),
    ('IBE3045', 'Iberia', 'Airbus A320', 'Madrid', 'Barcelona', 483, 65, '2024-07-16', '17:15:00', '18:20:00', 79, '45B', 'ECONOMY'),
    ('AR1135', 'Aerolineas Argentinas', 'Airbus A330', 'Madrid', 'Buenos Aires', 10021 , 790, '2025-07-15', '09:30:00', '17:40:00', 1340, '54B', 'PREMIUM_ECONOMY'),
    ('U28022', 'Iberia Express', 'Airbus A320', 'Madrid', 'Londres', 1264, 140, '2024-08-12', '18:40:00', '20:00:00', 195, '60A', 'ECONOMY'),
    ('VY6500', 'Vueling', 'Airbus A320', 'Barcelona', 'Napoles', 1018, 140, '2024-09-10', '18:40:00', '20:00:00', 153, '60A', 'ECONOMY'),
    ('FR7371', 'Ryanair', 'Boeing 737', 'Valencia', 'Viena', 1655, 165, '2024-10-01', '09:50:00', '12:35:00', 245, '14B', 'BUSINESS'),
    ('TP1062', 'Tap Air Portugal', 'Embraer 190', 'Bilbao', 'Lisboa', 1264, 90, '2024-07-30', '09:10:00', '11:40:00', 232, '10A', 'PREMIUM_ECONOMY'),
    ('DL127', 'Delta', 'Boeing 767', 'New York', 'Madrid', 5783, 495, '2024-11-15', '10:00:00', '18:45:00', 4876, '15B', 'BUSINESS'),
    ('UAE142', 'Emirates', 'Airbus A380', 'Madrid', 'Dubai', 5656, 410, '2024-11-15', '03:35:00', '10:25:00', 3950, '2A', 'FIRST_CLASS'),
    ('U26723', 'EasyJet', 'Boeing 737', 'Lisboa', 'Madrid', 503, 80, '2024-12-01', '19:35:00', '20:55:00', 53, '22A', 'ECONOMY'),
    ('W46902', 'Wizz Air', 'Airbus A321', 'Londres', 'Napoles', 1619, 160, '2024-12-01', '19:35:00', '20:55:00', 77, '54B', 'ECONOMY'),
    ('I23705', 'Iberia Express', 'Airbus A320', 'Napoles', 'Madrid', 1518, 165, '2024-05-16', '06:50:00', '09:35:00', 243, '60B', 'ECONOMY'),
    ('AA954', 'American', 'Boeing 777', 'Buenos Aires', 'New York', 8493, 651, '2024-12-01', '21:29:00', '08:20:00', 2431, '10A', 'BUSINESS');

INSERT INTO reservations (reservation_code, adults, children, promotions, payment_method, reservation_status, deposit, date_of_reservation, agent_id, customer_id) VALUES
    ('AD456', 2, 1, 'EARLY_BOOKING', 'BANK_TRANSFER', 'PENDING', 0, '2024-06-10', 1, 1),
    ('RT909', 4, 0, 'NONE', 'CREDIT_CARD', 'CONFIRMED', 160, '2024-06-05', 1, 2),
    ('VD756', 6, 2, 'GROUP', 'CASH', 'CONFIRMED', 320, '2024-05-16', 4, 3),
    ('XM132', 2, 2, 'PROMOTIONAL_CODE', 'CASH', 'CANCELLED', 0, '2024-06-01', 4, 4),
    ('GH990', 2, 4, 'LARGE_FAMILY', 'CREDIT_CARD', 'CONFIRMED', 160, '2024-06-11', 4, 5),
    ('DF549', 2, 0, 'EMPLOYEE', 'BANK_TRANSFER', 'PENDING', 0, '2024-05-30', 6, 6),
    ('KO672', 2, 1, 'NONE', 'PAYPAL', 'ON_HOLD', 0, '2024-06-09', 6, 1),
    ('KO788', 1, 0, 'PROMOTIONAL_CODE', 'BANK_TRANSFER', 'CONFIRMED', 40, '2024-04-27', 6, 2),
    ('MN435', 4, 1, 'NONE', 'DEBIT_CARD', 'CONFIRMED', 200, '2024-05-20', 1, 3),
    ('HG456', 3, 0, 'EARLY_BOOKING', 'CREDIT_CARD', 'ON_HOLD', 0, '2024-06-16', 4, 4);

INSERT INTO travels (destination, duration, final_price, customer_id, reservation_code, bill_id) VALUES
    ('Buenos Aires', 6, 4430, 1, 'AD456',null),
    ('Barcelona', 4, 316, 2, 'RT909', null),
    ('Viena', 10, 2685, 3, 'VD756',null),
    ('Hampshire', 14, 1820, 5, 'GH990',null),
    ('Ischia', 6, 741, 6, 'DF549',null),
    ('Toledo', 10, 250, 1, 'KO672',null),
    ('Dubai', 8, 14112, 2, 'KO788',null),
    ('Europe', 20, 12290, 3, 'MN435',null),
    ('America', 22, 42611, 4, 'HG456',null);

INSERT INTO hotel_bookings (duration, hotel_booking_price, hotel_id, reservation_code, travel_id) VALUES
    (6, 270, 2, 'AD456', 1),
    (10, 670, 6, 'VD756', 3),
    (14, 520, 3, 'GH990', 4),
    (6, 160, 4, 'DF549', 5),
    (10, 80, 1, 'KO672', 6),
    (8, 9592, 7, 'KO788', 7),
    (4, 1400, 6, 'MN435', 8),
    (3, 420, 1, 'MN435', 8),
    (6, 3090, 3, 'MN435', 8),
    (7, 2520, 4, 'MN435', 8),
    (10, 1050, 2, 'HG456', 9),
    (12, 15120, 8, 'HG456', 9);

INSERT INTO flight_bookings (flight_booking_price, reservation_code, travel_id, flight_id) VALUES
    (4020, 'AD456', 1, 3),
    (316, 'RT909', 2, 2),
    (1960, 'VD756', 3, 6),
    (1170, 'GH990', 4, 4),
    (306, 'DF549', 5, 5),
    (3950, 'KO788', 7, 9),
    (1160, 'MN435', 8, 7),
    (265, 'MN435', 8, 10),
    (975, 'MN435', 8, 4),
    (385, 'MN435', 8, 11),
    (1215, 'MN435', 8, 12),
    (4020, 'HG456', 9, 3),
    (7293, 'HG456', 9, 13),
    (14628, 'HG456', 9, 8);

INSERT INTO amenities (name, description) VALUES
    ('Parking', 'Simplify the stress of finding parking for guests arriving by car'),
    ('Bellhops', 'Staff to assist guests with their luggage'),
    ('24-hour reception', 'There should always be someone available for guests in case of need'),
    ('Free WiFi', ' A basic service that all guests expect'),
    ('Room service', 'Offer in-room dining options for added convenience'),
    ('Complimentary toiletries', 'Shampoo, conditioner, body wash, lotion, and a dental kit'),
    ('Mobile keys', 'Allow guests to use their mobile devices as room keys'),
    ('Welcome drink and light snack', 'Offer a refreshing drink and a sweet treat to welcome them');

INSERT INTO amenities_at_hotel (hotel_booking_id, amenity_id) VALUES
    (1,1),  (1,2),  (1,4),
    (3,1),  (3,2),  (3,3),  (3,4),  (3,5),
    (4,1),  (4,3),  (4,6),  (4,7),  (4,8),
    (5,8),  (5,8),  (5,8),  (5,8),  (5,8),  (5,8),
    (6,8),  (6,8),  (6,8),  (6,8),  (6,8),  (5,8),
    (7,1),  (7,2),  (7,3),  (7,4),  (7,5),  (7,6),  (7,7),  (7,8),
    (8,1),  (8,2),  (8,4),  (8,5),  (8,6),  (8,7),  (8,8),
    (9,1),  (9,3),  (9,4),  (9,5),  (9,6),  (9,7),
    (10,1), (10,2), (10,3), (10,4), (10,5), (10,6), (10,7), (10,8),
    (11,1), (11,2), (11,3), (11,5), (11,6), (11,7), (11,8),
    (12,1), (12,2), (12,3), (12,4), (12,6), (12,7);

INSERT INTO hotel_extras (name, description, extra_price) VALUES
    ('Extra bed / crib', 'For stays with children or babies', 10),
    ('Remote workspace', 'A shared space with high-speed broadband for guests to work comfortably', 50),
    ('Smart, cable or satellite TV', 'In-room entertainment options and access to streaming platforms', 15),
    ('Air conditioning or heating', 'Depending on the season and climate', 0),
    ('Minibar or fridge', 'To refrigerate drinks or food', 20),
    ('Safety deposit box', 'To store valuables', 30),
    ('Soundproof rooms', 'Provide a quiet and relaxing environment for a good sleep', 80),
    ('Sample of typical regional gastronomic products', 'to try and then purchase at the hotel restaurant', 0),
    ('Balcony or terrace', 'To enjoy the outdoors', 30),
    ('Panoramic views', 'Some rooms offer special views', 50);

INSERT INTO extras_at_hotel (hotel_booking_id, hotel_extra_id) VALUES
    (1,1),  (1,3),  (1,8),  (1,9),
    (3,3),  (3,4),  (3,8),
    (4,1),  (4,3),  (4,4),  (4,8),
    (5,2),  (5,4),  (5,5),  (5,8),  (5,9),  (5,10),
    (6,1),  (6,3),  (6,4),  (6,8),  (6,9),
    (7,2),  (7,3),  (7,4),  (7,5),  (7,6),  (7,7),  (7,8),
    (8,2),  (8,3),  (8,4),  (8,5),  (8,6),  (8,7), (8,9),  (8,10),
    (9,3),  (9,4),  (9,8),  (9,9),
    (10,3), (10,4), (10,8), (10,9),
    (11,3), (11,4), (11,8), (11,10),
    (12,2), (12,3), (12,4), (12,5), (12,6);

INSERT INTO room_extras (name, description, extra_price) VALUES
    ('Early Check-in and Late Check-out', 'Flexible check-in and check-out times', 0),
    ('Tickets for Museums, Exhibitions, and Attractions', 'Access to local cultural sites and events', 25),
    ('Childcare and Babysitting Services', 'Professional care for children during your stay', 50),
    ('Shuttle service', 'Transportation options, including airport shuttles and local transport ', 75),
    ('Transportation and tech devices rental', 'Bycicles, cars, motorcycles, computers and tablets', 30),
    ('Food & Beverage Services', 'Romantic dinners, kid menus, brunch and snacks, takeaway food, etc', 0),
    ('Pet-Friendly Services', 'Amenities for pets, such as beds, food dishes, and walking areas', 10),
    ('Laundry and Dry Cleaning and Clothing Care', ' Washing, drying, and ironing', 10),
    ('Parking and electric Car Charging Stations', 'Parking place and charging facilities for electric vehicles', 0),
    ('Event and Meeting Rooms', 'Spaces for conferences, meetings, or special events', 200),
    ('Sauna, Steam Room, and Pool', 'Wellness facilities available for guests', 0),
    ('Gym', 'Fitness center accessible', 0),
    ('Swimming Pool', 'Indoor or outdoor pools for relaxation and sunbathing', 0),
    ('Restaurant', 'Breakfast, lunch and dinner services or even gourmet menus of High-end cuisine or local dishes', 40),
    ('Spa', 'Massages, facials, and other beauty and wellness treatments', 60),
    ('Recreational Activities', 'Yoga, hiking trails, bike tours, aquatic sports, etc', 30);

INSERT INTO extras_at_room (hotel_booking_id,room_extra_id) VALUES
    (1,2),  (1,3),  (1,6),  (1,8),
    (3,1),  (3,6),  (3,8),  (2,13), (2,16),
    (4,1),  (4,4),  (4,6),  (4,16),
    (5,2),  (5,6),  (5,11),  (5,14),  (5,15),
    (6,1),  (6,2),  (6,3),  (6,14),
    (7,4),  (7,10),  (7,11),  (7,14),  (7,15),
    (8,1),  (8,4),  (8,5),  (8,6),
    (9,4),  (9,5),  (9,6),  (9,14),
    (10,1), (10,4), (10,14),
    (11,1), (11,4), (11,5), (11,14),
    (12,1), (12, 4), (12,5), (12,14), (12,16);


