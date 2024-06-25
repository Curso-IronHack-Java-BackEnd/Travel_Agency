package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.User;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgentUpdateDTO extends User {

    private String specialization;

}
