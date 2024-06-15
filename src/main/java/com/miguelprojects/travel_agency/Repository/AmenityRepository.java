package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository  extends JpaRepository<Amenity, Long> {
}
