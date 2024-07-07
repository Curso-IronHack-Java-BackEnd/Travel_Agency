package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO extends Person {

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    public CustomerCreateDTO(String firstName, String lastName, String phoneNumber, String email, String address, LocalDate dateOfBirth) {
        super(firstName, lastName, phoneNumber, email);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
