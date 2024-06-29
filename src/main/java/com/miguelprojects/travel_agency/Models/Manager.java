package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="managers")
@DynamicUpdate
public class Manager extends User{

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
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agent> agents = new ArrayList<>();

    public Manager() {    }

    public Manager(String firstName, String lastName, String phoneNumber, String email,
                   Long managerId, String department, Integer yearsOfExperience, List<Agent> agents) {
        super(firstName, lastName, phoneNumber, email);
        this.managerId = managerId;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.agents = agents;
    }

    public Long getManagerId() {
        return managerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(managerId, manager.managerId)
                && Objects.equals(department, manager.department)
                && Objects.equals(yearsOfExperience, manager.yearsOfExperience)
                && Objects.equals(agents, manager.agents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerId, department, yearsOfExperience, agents);
    }
}
