package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.User;
import jakarta.validation.constraints.Past;
import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDTO extends User {

    private String address;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
}
