package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.TicketClass;
import com.sun.jdi.ClassType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="flights")
@DynamicUpdate
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @NotBlank(message = "First Name is mandatory")
    @Column(name = "flight_number")
    private String flightNumber;

    private String airline;

    @NotBlank(message = "Origin is mandatory")
    private String origin;

    @NotBlank(message = "Destination is mandatory")
    private String destination;

    private Integer mileage;

    private Integer duration;

    @Column(name = "date_of_flight")
    @NotNull(message = "Date of Flight is mandatory")
    private LocalDate dateOfFlight;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @NotBlank(message = "Seat Number is mandatory")
    @Column(name = "seat_number")
    private String seatNumber;

    @NotNull(message = "Ticket Class is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_class")
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

}
