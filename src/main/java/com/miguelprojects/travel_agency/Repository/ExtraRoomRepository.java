package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.ExtraRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExtraRoomRepository extends JpaRepository<ExtraRoom, Integer> {
}
