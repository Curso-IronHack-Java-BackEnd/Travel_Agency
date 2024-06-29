package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AmenityRepository  extends JpaRepository<Amenity, Long> {
}
