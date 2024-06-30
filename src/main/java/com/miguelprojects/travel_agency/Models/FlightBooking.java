package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Repository.FlightBookingRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name ="flight_bookings")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_booking_id")
    private Long flightBookingId;

    @JsonIgnore
    @Column(name = "flight_booking_price")
    @Digits(integer = 8, fraction = 2, message = "Wrong Price Format")
    @NotNull(message = "Booking price for flight is mandatory")
    private BigDecimal flightBookingPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_code")
    private Reservation reservation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="flight_id")
    private Flight flight;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "bill_id")
//    private Bill bill;

    public BigDecimal totalPriceFlightBooking(FlightBooking flightBooking){
        Reservation reservation = flightBooking.getReservation();
        Flight flight = flightBooking.getFlight();
        Integer children = reservation.getChildren();
        Integer adults = reservation.getAdults();
        BigDecimal price = flight.getPrice();
        return price.multiply(BigDecimal.valueOf(children)).add(BigDecimal.valueOf(adults));
    }

}
