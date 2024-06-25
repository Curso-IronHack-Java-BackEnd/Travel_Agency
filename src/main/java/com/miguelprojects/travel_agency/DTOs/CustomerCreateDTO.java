package com.miguelprojects.travel_agency.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO extends User{

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;


}
