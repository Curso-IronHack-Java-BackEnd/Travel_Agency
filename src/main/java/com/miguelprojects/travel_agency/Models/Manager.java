package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="managers")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long managerId;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @Column(name = "years_of_experience")
    @Min(value = 2, message = "Minimun experience is 2 years")
    private Integer yearsOfExperience;

    @JsonIgnore
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Agent> agents = new ArrayList<>();

    public Manager(String firstName, String lastName, String phoneNumber, String email, String department, Integer yearsOfExperience, List<Agent> agents) {
        super(firstName, lastName, phoneNumber, email);
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.agents = agents;
    }

    //    @JsonIgnore
//    @OneToOne ()
//    private User user;
}
