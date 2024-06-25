package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="travels")
@DynamicUpdate
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long travelId;

    @NotNull(message = "Destination is mandatory")
    private String destination;

    @NotNull(message = "Duration is mandatory")
    private String duration;

    @NotNull(message = "Final price is mandatory")
    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @JsonIgnore
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelBooking> hotelBookings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightBooking> flightBookings = new ArrayList<>();

    public Travel() {    }

    public Travel(String destination, String duration, BigDecimal finalPrice,
                  Customer customer, Reservation reservation, List<HotelBooking> hotelBookings, List<FlightBooking> flightBookings) {
        this.destination = destination;
        this.duration = duration;
        this.finalPrice = finalPrice;
        this.customer = customer;
        this.reservation = reservation;
        this.hotelBookings = hotelBookings;
        this.flightBookings = flightBookings;
    }

    public Long getTravelId() {
        return travelId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public List<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return Objects.equals(travelId, travel.travelId) && Objects.equals(destination, travel.destination)
                && Objects.equals(duration, travel.duration) && Objects.equals(finalPrice, travel.finalPrice)
                && Objects.equals(customer, travel.customer) && Objects.equals(reservation, travel.reservation)
                && Objects.equals(hotelBookings, travel.hotelBookings) && Objects.equals(flightBookings, travel.flightBookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelId, destination, duration, finalPrice, customer, reservation, hotelBookings, flightBookings);
    }
}
