package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="amenities")
@DynamicUpdate
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_id")
    private Integer amenityId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "amenities_at_hotel",
            joinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "amenity_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_booking_id", referencedColumnName = "hotel_booking_id"))
    private List<HotelBooking> hotelBookings;

    public Amenity() {    }

    public Amenity(String name, String description, List<HotelBooking> hotelBookings) {
        this.name = name;
        this.description = description;
        this.hotelBookings = hotelBookings;
    }

    public Integer getAmenityId() {
        return amenityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return Objects.equals(amenityId, amenity.amenityId) && Objects.equals(name, amenity.name)
                && Objects.equals(description, amenity.description) && Objects.equals(hotelBookings, amenity.hotelBookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityId, name, description, hotelBookings);
    }
}
