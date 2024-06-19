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

create table agents
(
    commission_rate decimal(2, 2) null,
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

create table reservations
(
    adults              int                                                                                                                     not null,
    children            int                                                                                                                     null,
    deposit               decimal(10, 2)                                                                                                          not null,
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

create table travels
(
    final_price    decimal(10, 2) null,
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
    aircraft       varchar(255)                                                   not null,
    destination    varchar(255)                                                   not null,
    flight_number  varchar(255)                                                   not null,
    origin         varchar(255)                                                   not null,
    price          decimal(10,2)                                                  not null,
    seat_number    varchar(255)                                                   not null,
    ticket_class   enum ('BUSINESS', 'ECONOMY', 'FIRST_CLASS', 'PREMIUM_ECONOMY') not null,
    constraint FKh7bv0m74ylp3ugb699572kjuc
        foreign key (travel_id) references travels (travel_id)
);

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

create table amenities
(
    amenity_id      bigint auto_increment
        primary key,
    hotel_id        bigint         null,
    description     varchar(255)   null,
    name            varchar(255)   not null,
    constraint FK23ayav9or91wbe85x0v9svxv1
        foreign key (hotel_id) references hotels (hotel_id)
);

create table hotel_extras
(
    extra_id    int auto_increment
        primary key,
    extra_price decimal(10, 2) null,
    hotel_id    bigint         null,
    description varchar(255)   null,
    name        varchar(255)   not null,
    constraint FKp9iwidowc0ba5a18k2fi4v285
        foreign key (hotel_id) references hotels (hotel_id)
);

create table rooms
(
    price_per_night decimal(10, 2)                                                 not null,
    hotel_id        bigint                                                         null,
    nights          bigint                                                         not null,
    room_number     varchar(255)                                                   not null
        primary key,
    room_type       enum ('DOUBLE', 'FAMILY', 'SINGLE', 'SUITE', 'TRIPLE', 'TWIN') not null,
    -- status          enum ('AVAILABLE', 'OUT_OF_SERVICE', 'RESERVED')               not null,
    constraint FKp5lufxy0ghq53ugm93hdc941k
        foreign key (hotel_id) references hotels (hotel_id)
);

create table room_extras
(
    extra_id    int auto_increment
        primary key,
    extra_price decimal(10, 2) null,
    description varchar(255)   null,
    name        varchar(255)   not null,
    room_id     varchar(255)   null,
    constraint FKi726ud8np1b41ti7j2v8p86hk
        foreign key (room_id) references rooms (room_number)
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


INSERT INTO agents (first_name, last_name, phone_number, email, specialization, commission_rate) VALUES
    ('Elena', 'Peña', '676542335', 'elenapeña@hotmail.com', 'Sales', 2.75),
    ('Yago', 'Martin', '645223676', 'yagomartin@hotmail.com', 'Operations', 12.25),
    ('Miguel', 'Payes', '908564345', 'miguelpayes@hotmail.com', 'Marketing', 5.33),
    ('Domingo', 'Ruiz', '926785676', 'domingoruiz@hotmail.com', 'Administration', 6.50),
    ('Santiago', 'Gonzalez', '666478423', 'santiagogonzalez@hotmail.com', 'Product', 10.00),
    ('Marta', 'Pozo', '676009897', 'martapozo@hotmail.com', 'Sales', 5.50);


INSERT INTO hotels (name, address, city, country, phone_number, email, rating, hotel_type, number_of_rooms, travel_id) VALUES
    ('Toledo Imperial', 'Avenida Recoletos 25', 'Toledo', 'España', '+34923495967', 'toledoimperial@gmail.es', 3.5, 'URBAN', 100, null),
    ('Indiana', 'C/ Europa, 12', 'Buenos Aires', 'Argentina', '+541146498000', 'indianabuenosaires@gmail.ar', 2.9, 'AIRPORT', 320,null),
    ('Heckfield Palace', 'Heckfield Place, 1', 'Hampshire', 'Reino Unido', '+441189326868', 'heckfielPalace@gmail.uk', 4.1, 'MONUMENT', 150,null),
    ('Regina Isabella', 'Piazza Santa Restituta, 1', 'Ischia', 'Italia', '+39081994322', 'reginaisabella@gmail.it', 4.5, 'TOURIST', 200,null),
    ('Hotel Imperial', 'Kärntner Ring 16', 'Viena', 'Austria', '+431501100', 'hotelimperial@gmail.at',4.0, 'NATURE', 160,null),
    ('Palacio Principe Real', 'R. de São Marçal 77', 'Lisboa', 'Portugal', '+351218792000', 'palacioprincipereal@gmail.pt',4.2, 'SPA', 180,null);



INSERT INTO flights (flight_number, airline, aircraft, origin, destination, mileage, duration, date_of_flight, departure_time, arrival_time, price, seat_number, ticket_class, travel_id) VALUES
    ('IBE3031', 'Iberia', 'Airbus A320', 'Barcelona', 'Madrid', 483, 65, '2024-07-20', '18:15:00', '19:20:00', 79, '47A', 'ECONOMY', null),
    ('AR1135', 'Aerolineas Argentinas', 'Airbus A330', 'Madrid', 'Buenos Aires', 10021 , 790, '2025-07-15', '09:30:00', '17:40:00', 1340, '54B', 'PREMIUM_ECONOMY', null),
    ('U28022', 'Iberia Express', 'Airbus A320', 'Madrid', 'Londres', 1264, 140, '2024-08-12', '18:40:00', '20:00:00', 195, '60A', 'ECONOMY', null),
    ('VY6500', 'Vueling', 'Airbus A320', 'Barcelona', 'Napoles', 1018, 140, '2024-09-10', '18:40:00', '20:00:00', 153, '60A', 'ECONOMY', null),
    ('FR7371', 'Ryanair', 'Boeing 737', 'Valencia', 'Viena', 1655, 165, '2024-10-01', '09:50:00', '12:35:00', 245, '14B', 'BUSINESS', null),
    ('TP1062', 'Tap Air Portugal', 'Embraer 190', 'Bilbao', 'Lisboa', 1264, 90, '2024-07-30', '09:10:00', '11:40:00', 232, '10A', 'PREMIUM_ECONOMY', null),
    ('DL127', 'Delta', 'Boeing 767', 'Madrid', 'New York', 5783, 495, '2024-11-15', '10:00:00', '18:45:00', 4876, '15B', 'BUSINESS', null),
    ('UAE142', 'Emirates', 'Airbus A380', 'Madrid', 'Dubai', 5656, 410, '2024-11-15', '03:35:00', '10:25:00', 3950, '2A', 'FIRST_CLASS', null);

INSERT INTO amenities (name, description, hotel_id) VALUES
    ('Parking', 'Simplify the stress of finding parking for guests arriving by car', null),
    ('Bellhops', 'Staff to assist guests with their luggage', null),
    ('24-hour reception', 'There should always be someone available for guests in case of need', null),
    ('Free WiFi', ' A basic service that all guests expect', null),
    ('Room service', 'Offer in-room dining options for added convenience', null),
    ('Complimentary toiletries', 'Shampoo, conditioner, body wash, lotion, and a dental kit', null),
    ('Mobile keys', 'Allow guests to use their mobile devices as room keys', null),
    ('Welcome drink and light snack', 'Offer a refreshing drink and a sweet treat to welcome them', null);

INSERT INTO hotel_extras (name, description, extra_price, hotel_id) VALUES
    ('Extra bed / crib', 'For stays with children or babies', 10, 1),
    ('Remote workspace', 'A shared space with high-speed broadband for guests to work comfortably', 50, null),
    ('Smart, cable or satellite TV', 'In-room entertainment options and access to streaming platforms', 15, null),
    ('Air conditioning or heating', 'Depending on the season and climate', 0, null),
    ('Minibar or fridge', 'To refrigerate drinks or food', 20, null),
    ('Safety deposit box', 'To store valuables', 30, null),
    ('Soundproof rooms', 'Provide a quiet and relaxing environment for a good sleep', 80, null),
    ('Sample of typical regional gastronomic products', 'to try and then purchase at the hotel restaurant', 0, null),
    ('Balcony or terrace', 'To enjoy the outdoors', 30, null),
    ('Panoramic views', 'Some rooms offer special views', 50, null);

INSERT INTO room_extras (name, description, extra_price, room_id) VALUES
    ('Early Check-in and Late Check-out', 'Flexible check-in and check-out times', 0, null),
    ('Tickets for Museums, Exhibitions, and Attractions', 'Access to local cultural sites and events', 25, null),
    ('Childcare and Babysitting Services', 'Professional care for children during your stay', 50, null),
    ('Shuttle service', 'Transportation options, including airport shuttles and local transport ', 75, null),
    ('Transportation and tech devices rental', 'Bycicles, cars, motorcycles, computers and tablets', 30, null),
    ('Food & Beverage Services', 'Romantic dinners, kid menus, brunch and snacks, takeaway food, etc', 0, null),
    ('Pet-Friendly Services', 'Amenities for pets, such as beds, food dishes, and walking areas', 10, null),
    ('Laundry and Dry Cleaning and Clothing Care', ' Washing, drying, and ironing', 10, null),
    ('Parking and electric Car Charging Stations', 'Parking place and charging facilities for electric vehicles', 0, null),
    ('Event and Meeting Rooms', 'Spaces for conferences, meetings, or special events', 200, null),
    ('Sauna, Steam Room, and Pool', 'Wellness facilities available for guests', 0, null),
    ('Gym', 'Fitness center accessible', 0, null),
    ('Swimming Pool', 'Indoor or outdoor pools for relaxation and sunbathing', 0, null),
    ('Restaurant', 'Breakfast, lunch and dinner services or even gourmet menus of High-end cuisine or local dishes', 40, null),
    ('Spa', 'Massages, facials, and other beauty and wellness treatments', 60, null),
    ('Recreational Activities', 'Yoga, hiking trails, bike tours, aquatic sports, etc', 30, null);

INSERT INTO rooms (room_number, room_type, price_per_night, nights, hotel_id) VALUES
    ('100', 'SINGLE' , 40, 2, null),
    ('200', 'DOUBLE', 70, 3, null),
    ('300', 'TWIN', 60, 1, null),
    ('400', 'TRIPLE', 90, 2, null),
    ('500', 'FAMILY', 100, 5, null),
    ('600', 'SUITE', 220, 3, null);

INSERT INTO reservations (reservation_code, promotions, adults, children, payment_method, reservation_status, deposit, date_of_reservation, agent_id, customer_id) VALUES
    ('AD456', 'EARLY_BOOKING', 2, 1, 'BANK_TRANSFER', 'PENDING', 120, '2024-06-10', null, null),
    ('RT909', 'NONE', 4, 0, 'CREDIT_CARD', 'CONFIRMED', 160, '2024-06-05', null, null),
    ('VD756', 'GROUP', 6, 2, 'CASH', 'PENDING', 320, '2024-05-16', null, null),
    ('XM132', 'LONG_STAY', 2, 2, 'CASH', 'CANCELLED', 160, '2024-06-01', null, null),
    ('GH990', 'LARGE_FAMILY', 2, 4, 'CREDIT_CARD', 'CONFIRMED', 160, '2024-06-11', null, null),
    ('DF549', 'EMPLOYEE', 2, 0, 'BANK_TRANSFER', 'PENDING', 80, '2024-05-30', null, null),
    ('KO672', 'NONE', 2, 1, 'PAYPAL', 'ON_HOLD', 120, '2024-06-09', null, null),
    ('KO672', 'PROMOTIONAL_CODE', 4, 1, 'BANK_TRANSFER', 'ON_HOLD', 200, '2024-04-27', null, null),
    ('MN435', 'NONE', 1, 0, 'DEBIT_CARD', 'CONFIRMED', 40, '2024-05-20', null, null);

INSERT INTO travels (destination, duration, final_price, customer_id, reservation_id) VALUES
    ('Buenos Aires', 6, null, null, null),
    ('Barcelona', 4, null, null, null),
    ('Viena', 10, null, null, null),
    ('Lisboa', 5, null, null, null),
    ('Hampshire', 14, null, null, null),
    ('Toledo', 10, null, null, null),
    ('Ischia', 15, null, null, null),
    ('Lisboa', 8, null, null, null),
    ('Buenos Aires', 22, null, null, null);

