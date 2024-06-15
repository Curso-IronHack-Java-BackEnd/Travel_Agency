package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.ExtraHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraHotelRepository extends JpaRepository<ExtraHotel, Integer> {
}
