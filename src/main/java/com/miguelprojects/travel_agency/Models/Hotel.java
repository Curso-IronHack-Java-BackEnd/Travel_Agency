package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.HotelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="hotels")
@DynamicUpdate
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @Size(min = 9, max = 15)
    @Column(name = "phone_number")
    @NotBlank(message = "Phone is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 0, message = "Rating must be greater than 0")
    @Max(value = 5, message = "Rating must be a maximum of 5")
    @Digits(integer = 1, fraction = 1, message = "Wrong Rating Format")
    private BigDecimal rating;

    @Column(name = "breakfast_include")
    private Boolean breakfastInclude;

    @Column(name = "hotel_type")
    @NotBlank(message = "Hotel Type is mandatory")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "hotel_extras")
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraHotel> extras = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Amenity> amenities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;
}
