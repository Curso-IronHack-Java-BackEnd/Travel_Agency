package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Enums.HotelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="hotels")
@DynamicUpdate
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @Size(min = 9, max = 15)
    @Column(name = "phone_number")
    @NotBlank(message = "Phone is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 0, message = "Rating must be greater than 0")
    @Max(value = 5, message = "Rating must be a maximum of 5")
    @Digits(integer = 2, fraction = 1, message = "Wrong Rating Format")
    private BigDecimal rating;

    @Column(name = "hotel_type")
    @NotBlank(message = "Hotel Type is mandatory")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "price_children")
    @Digits(integer = 6, fraction = 2, message = "Wrong Price Format")
    @NotBlank(message = "Price per Children is mandatory")
    private BigDecimal priceChildren;

    @Column(name = "price_adult")
    @Digits(integer = 6, fraction = 2, message = "Wrong Price Format")
    @NotBlank(message = "Price per Adult is mandatory")
    private BigDecimal priceAdult;

    @JsonIgnore
    @OneToOne(mappedBy="hotel")
    private HotelBooking hotelBooking;


    public Hotel() {    }

    public Hotel(String name, String address, String city, String country, String phoneNumber,
                 String email, BigDecimal rating, HotelType hotelType, Integer numberOfRooms,
                 BigDecimal priceChildren, BigDecimal priceAdult, HotelBooking hotelBooking) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rating = rating;
        this.hotelType = hotelType;
        this.numberOfRooms = numberOfRooms;
        this.priceChildren = priceChildren;
        this.priceAdult = priceAdult;
        this.hotelBooking = hotelBooking;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public BigDecimal getPriceChildren() {
        return priceChildren;
    }

    public void setPriceChildren(BigDecimal priceChildren) {
        this.priceChildren = priceChildren;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
    }

    public HotelBooking getHotelBooking() {
        return hotelBooking;
    }

    public void setHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(hotelId, hotel.hotelId) && Objects.equals(name, hotel.name)
                && Objects.equals(address, hotel.address) && Objects.equals(city, hotel.city)
                && Objects.equals(country, hotel.country) && Objects.equals(phoneNumber, hotel.phoneNumber)
                && Objects.equals(email, hotel.email) && Objects.equals(rating, hotel.rating)
                && hotelType == hotel.hotelType && Objects.equals(numberOfRooms, hotel.numberOfRooms)
                && Objects.equals(priceChildren, hotel.priceChildren) && Objects.equals(priceAdult, hotel.priceAdult)
                && Objects.equals(hotelBooking, hotel.hotelBooking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, name, address, city, country, phoneNumber, email, rating,
                hotelType, numberOfRooms, priceChildren, priceAdult, hotelBooking);
    }
}
