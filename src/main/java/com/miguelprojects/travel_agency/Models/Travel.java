package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
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

    private String destination;

    private String duration;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hotel> hotels = new ArrayList<>();

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    public Travel() {    }

    public Travel(Long travelId, String destination, String duration, BigDecimal finalPrice,
                  Customer customer, Reservation reservation, List<Hotel> hotels, List<Flight> flights) {
        this.travelId = travelId;
        this.destination = destination;
        this.duration = duration;
        this.finalPrice = finalPrice;
        this.customer = customer;
        this.reservation = reservation;
        this.hotels = hotels;
        this.flights = flights;
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

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return Objects.equals(travelId, travel.travelId) && Objects.equals(destination, travel.destination)
                && Objects.equals(duration, travel.duration) && Objects.equals(finalPrice, travel.finalPrice)
                && Objects.equals(customer, travel.customer) && Objects.equals(reservation, travel.reservation)
                && Objects.equals(hotels, travel.hotels) && Objects.equals(flights, travel.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelId, destination, duration, finalPrice, customer, reservation, hotels, flights);
    }
}
