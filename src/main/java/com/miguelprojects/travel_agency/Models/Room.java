package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.RoomStatus;
import com.miguelprojects.travel_agency.Enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    public Room() {    }

    public Room(String roomNumber, RoomType roomType, BigDecimal pricePerNight,
                RoomStatus status, List<ExtraRoom> extras, Hotel hotel) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.extras = extras;
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public List<ExtraRoom> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraRoom> extras) {
        this.extras = extras;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && roomType == room.roomType
                && Objects.equals(pricePerNight, room.pricePerNight) && status == room.status
                && Objects.equals(extras, room.extras) && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomType, pricePerNight, status, extras, hotel);
    }
}
