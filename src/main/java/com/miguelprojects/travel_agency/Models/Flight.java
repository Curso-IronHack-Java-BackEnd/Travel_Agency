package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Enums.TicketClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name ="flights")
@DynamicUpdate
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @NotBlank(message = "Flight Number is mandatory")
    @Column(name = "flight_number")
    private String flightNumber;

    @NotBlank(message = "Airline is mandatory")
    private String airline;

    private String aircraft;

    @NotBlank(message = "Origin is mandatory")
    private String origin;

    @NotBlank(message = "Destination is mandatory")
    private String destination;

    private Integer mileage;

    private Integer duration;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of_flight")
    @NotNull(message = "Date of Flight is mandatory")
    @Future(message = "Date of Flight must be in the future")
    private LocalDate dateOfFlight;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @NotBlank(message = "Price is mandatory")
    private BigDecimal price;

    @NotBlank(message = "Seat Number is mandatory")
    @Column(name = "seat_number")
    private String seatNumber;

    @NotNull(message = "Ticket Class is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_class")
    private TicketClass ticketClass;

    @JsonIgnore
    @OneToOne(mappedBy="flight")
    private FlightBooking flightBooking;

    public Flight() {    }

    public Flight(String flightNumber, String airline, String aircraft, String origin, String destination,
                  Integer mileage, Integer duration, LocalDate dateOfFlight, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, BigDecimal price, String seatNumber, TicketClass ticketClass) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.aircraft = aircraft;
        this.origin = origin;
        this.destination = destination;
        this.mileage = mileage;
        this.duration = duration;
        this.dateOfFlight = dateOfFlight;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;

        this.seatNumber = seatNumber;
        this.ticketClass = ticketClass;
    }

    public Long getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightId, flight.flightId) && Objects.equals(flightNumber, flight.flightNumber)
                && Objects.equals(airline, flight.airline) && Objects.equals(aircraft, flight.aircraft) &&
                Objects.equals(origin, flight.origin) && Objects.equals(destination, flight.destination)
                && Objects.equals(mileage, flight.mileage) && Objects.equals(duration, flight.duration)
                && Objects.equals(dateOfFlight, flight.dateOfFlight) && Objects.equals(departureTime, flight.departureTime)
                && Objects.equals(arrivalTime, flight.arrivalTime) && Objects.equals(price, flight.price)
                && Objects.equals(seatNumber, flight.seatNumber) && ticketClass == flight.ticketClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightNumber, airline, aircraft, origin, destination, mileage, duration,
                dateOfFlight, departureTime, arrivalTime, price, seatNumber, ticketClass);
    }
}
