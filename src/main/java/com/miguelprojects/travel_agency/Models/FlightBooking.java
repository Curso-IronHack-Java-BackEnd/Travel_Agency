package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name ="flight_bookings")
@DynamicUpdate
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_booking_id")
    private Long flightBookingId;

    @Column(name = "flight_booking_price")
    @Digits(integer = 8, fraction = 2, message = "Wrong Price Format")
    @NotBlank(message = "Booking price for flight is mandatory")
    private BigDecimal flightBookingPrice;

    @ManyToOne
    @JoinColumn(name = "reservation_code")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @OneToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    public FlightBooking() {    }

    public FlightBooking(Long flightBookingId, BigDecimal flightBookingPrice,
                         Reservation reservation, Travel travel, Flight flight) {
        this.flightBookingId = flightBookingId;
        this.flightBookingPrice = flightBookingPrice;
        this.reservation = reservation;
        this.travel = travel;
        this.flight = flight;
    }

    public Long getFlightBookingId() {
        return flightBookingId;
    }

    public BigDecimal getFlightBookingPrice() {
        return flightBookingPrice;
    }

    public void setFlightBookingPrice(BigDecimal flightBookingPrice) {
        this.flightBookingPrice = flightBookingPrice;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightBooking that = (FlightBooking) o;
        return Objects.equals(flightBookingId, that.flightBookingId)
                && Objects.equals(flightBookingPrice, that.flightBookingPrice)
                && Objects.equals(reservation, that.reservation) && Objects.equals(travel, that.travel)
                && Objects.equals(flight, that.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightBookingId, flightBookingPrice, reservation, travel, flight);
    }
}
