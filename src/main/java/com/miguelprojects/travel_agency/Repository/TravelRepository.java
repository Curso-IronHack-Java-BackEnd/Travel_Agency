package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Integer> {
}
