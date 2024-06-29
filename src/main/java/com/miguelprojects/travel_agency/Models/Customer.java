package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="customers")
@DynamicUpdate
public class Customer extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travels = new ArrayList<>();


    public Customer() {    }

    public Customer(String firstName, String lastName, String phoneNumber, String email,
                    String address, LocalDate dateOfBirth, List<Reservation> reservations, List<Travel> travels) {
        super(firstName, lastName, phoneNumber, email);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.reservations = reservations;
        this.travels = travels;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(address, customer.address)
                && Objects.equals(dateOfBirth, customer.dateOfBirth)
                && Objects.equals(reservations, customer.reservations) && Objects.equals(travels, customer.travels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, address, dateOfBirth, reservations, travels);
    }
}
