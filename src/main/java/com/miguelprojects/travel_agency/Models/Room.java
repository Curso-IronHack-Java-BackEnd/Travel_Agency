package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.RoomStatus;
import com.miguelprojects.travel_agency.Enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name ="rooms")
@DynamicUpdate
public class Room {
    @Id
    @Column(name = "room_number")
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Room Type is mandatory")
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "price_per_night")
    @NotNull(message = "Price per night is mandatory")
    private BigDecimal pricePerNight;

    //private Integer capacity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Room Status is mandatory")
    private RoomStatus status;


    @Column(name = "room_extras")
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraRoom> extras;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
