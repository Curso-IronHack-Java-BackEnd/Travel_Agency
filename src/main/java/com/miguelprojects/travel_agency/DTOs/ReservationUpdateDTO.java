package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;


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

    private LocalDate dateOfReservation;

}
