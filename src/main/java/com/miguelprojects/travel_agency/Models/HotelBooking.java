package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="hotel_bookings")
@DynamicUpdate
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_booking_id")
    private Long hotelBookingId;

    @NotNull(message ="Duration of Stay is mandatory")
    @Min(value = 1, message = "Minimum stay is 1 day")
    private Integer duration;

    @Column(name = "hotel_booking_price")
    @Digits(integer = 8, fraction = 2, message = "Wrong Price Format")
    @NotBlank(message = "Booking price for hotel is mandatory")
    private BigDecimal hotelBookingPrice;

    @NotNull(message = "Hotel Id is mandatory")
    @OneToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "reservation_code")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings")
    private List<Amenity> amenities;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings")
    private List<ExtraHotel> hotelExtras;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings")
    private List<ExtraRoom> roomExtras;

    public HotelBooking() {    }

    public HotelBooking(Long hotelBookingId, Integer duration, BigDecimal hotelBookingPrice,
                        Hotel hotel, Reservation reservation, Travel travel, List<Amenity> amenities,
                        List<ExtraHotel> hotelExtras, List<ExtraRoom> roomExtras) {
        this.hotelBookingId = hotelBookingId;
        this.duration = duration;
        this.hotelBookingPrice = hotelBookingPrice;
        this.hotel = hotel;
        this.reservation = reservation;
        this.travel = travel;
        this.amenities = amenities;
        this.hotelExtras = hotelExtras;
        this.roomExtras = roomExtras;
    }

    public Long getHotelBookingId() {
        return hotelBookingId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getHotelBookingPrice() {
        return hotelBookingPrice;
    }

    public void setHotelBookingPrice(BigDecimal hotelBookingPrice) {
        this.hotelBookingPrice = hotelBookingPrice;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<ExtraHotel> getHotelExtras() {
        return hotelExtras;
    }

    public void setHotelExtras(List<ExtraHotel> hotelExtras) {
        this.hotelExtras = hotelExtras;
    }

    public List<ExtraRoom> getRoomExtras() {
        return roomExtras;
    }

    public void setRoomExtras(List<ExtraRoom> roomExtras) {
        this.roomExtras = roomExtras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelBooking that = (HotelBooking) o;
        return Objects.equals(hotelBookingId, that.hotelBookingId) && Objects.equals(duration, that.duration)
                && Objects.equals(hotelBookingPrice, that.hotelBookingPrice) && Objects.equals(hotel, that.hotel)
                && Objects.equals(reservation, that.reservation) && Objects.equals(travel, that.travel)
                && Objects.equals(amenities, that.amenities) && Objects.equals(hotelExtras, that.hotelExtras)
                && Objects.equals(roomExtras, that.roomExtras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelBookingId, duration, hotelBookingPrice, hotel,
                reservation, travel, amenities, hotelExtras, roomExtras);
    }
}
