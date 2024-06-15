package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
