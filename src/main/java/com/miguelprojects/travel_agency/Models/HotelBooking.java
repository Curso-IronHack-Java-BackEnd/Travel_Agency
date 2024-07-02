package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @Column(name = "hotel_booking_price")
    @Digits(integer = 8, fraction = 2, message = "Wrong Price Format")
    @NotNull(message = "Booking price for hotel is mandatory")
    private BigDecimal hotelBookingPrice;

    @NotNull(message = "Hotel Id is mandatory")
    @ManyToOne()
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "reservation_code")
//    private Reservation reservation;

    @ManyToOne()
    @JoinColumn(name = "travel_id")
    private Travel travel;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "bill_id")
//    private Bill bill;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings", fetch = FetchType.EAGER)
    private List<Amenity> amenities;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings", fetch = FetchType.EAGER)
    private List<ExtraHotel> hotelExtras;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelBookings", fetch = FetchType.EAGER)
    private List<ExtraRoom> roomExtras;

    public BigDecimal totalPriceHotelBooking(){
        Travel travel = getTravel();
        Reservation reservation = travel.getReservation();
        Hotel hotel = getHotel();
        BigDecimal roomExtrasTotalPrice = BigDecimal.ZERO;
        BigDecimal hotelExtrasTotalPrice = BigDecimal.ZERO;
        List<ExtraHotel> hotelExtras = getHotelExtras();
        for (ExtraHotel extraHotel : hotelExtras) {
            hotelExtrasTotalPrice.add(extraHotel.getExtraPrice());
        }
        List<ExtraRoom> roomExtras = getRoomExtras();
        for (ExtraRoom extraRoom : roomExtras) {
            roomExtrasTotalPrice.add(extraRoom.getExtraPrice());
        }
        Integer children = reservation.getChildren();
        Integer adults = reservation.getAdults();
        BigDecimal priceChildren = hotel.getPriceChildren().multiply(BigDecimal.valueOf(children));
        BigDecimal priceAdults = hotel.getPriceAdult().multiply(BigDecimal.valueOf(adults));
        return priceChildren.add(priceAdults).add(hotelExtrasTotalPrice).add(roomExtrasTotalPrice);
    }


}
