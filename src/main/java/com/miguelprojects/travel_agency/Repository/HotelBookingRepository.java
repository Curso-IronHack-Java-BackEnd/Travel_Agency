package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Amenity;
import com.miguelprojects.travel_agency.Models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
}
