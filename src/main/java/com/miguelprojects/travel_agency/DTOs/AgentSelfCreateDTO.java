package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Person;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgentSelfCreateDTO extends Person {

    private String specialization;

    public AgentSelfCreateDTO(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        super(firstName, lastName, phoneNumber, email);
        this.specialization = specialization;
    }
}
