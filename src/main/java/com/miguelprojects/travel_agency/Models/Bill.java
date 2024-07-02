package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name ="bills")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    private BigDecimal flightBookingPrice;

    private BigDecimal hotelBookingPrice;

    private BigDecimal totalBookingPrice;


//    @JsonIgnore
//    @OneToMany(mappedBy="bill", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<HotelBooking> hotelBookings;
//
//    @JsonIgnore
//    @OneToMany(mappedBy="bill", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FlightBooking> flightBookings;


    @OneToOne(mappedBy="bill")
    private Travel travel;

    // Funciones que calculan el precio de todos los bookings de hotels y flights
    public BigDecimal hotelBookingsPrice(Travel travel) {
        List<HotelBooking> hotelBookings = travel.getHotelBookings();

        BigDecimal totalHotelBookings = BigDecimal.ZERO;
        for (HotelBooking hotelBooking : hotelBookings) {
            totalHotelBookings.add(hotelBooking.totalPriceHotelBooking());
        }
        return totalHotelBookings;
    }


    public BigDecimal flightBookingsPrice(Travel travel){
        List<FlightBooking> flightBookings = travel.getFlightBookings();
        BigDecimal totalFlightBookings = BigDecimal.ZERO;

        for(FlightBooking flightBooking : flightBookings){
            totalFlightBookings.add(flightBooking.getFlightBookingPrice());
        }
        return totalFlightBookings;
    }
}
