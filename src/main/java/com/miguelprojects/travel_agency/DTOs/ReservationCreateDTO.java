package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDTO {

    @NotBlank
    private String reservationCode;

    @NotNull(message = "Number of adults is mandatory")
    private Integer adults;

    private Integer children;

    @NotNull(message = "Promotions are mandatory")
    @Enumerated(EnumType.STRING)
    private Promotions promotions;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment Method is mandatory")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Reservation Status is mandatory")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull(message = "Deposit is mandatory")
    @Min(value = 0, message = "Deposit must be positive or 0")
    private BigDecimal deposit;

    @Past(message = "Reservation must have been made in the past")
    private LocalDateTime dateOfReservation;


    private Long agentId;

    @NotNull
    private Long customerId;

}
