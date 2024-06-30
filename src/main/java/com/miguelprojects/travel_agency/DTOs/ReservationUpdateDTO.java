package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUpdateDTO {

    private Promotions promotions;

    private Integer adults;

    private Integer children;


    private PaymentMethod paymentMethod;

    private ReservationStatus reservationStatus;

    private BigDecimal deposit;

    @Past(message = "Reservation must have been made in the past")
    private LocalDateTime dateOfReservation;

}