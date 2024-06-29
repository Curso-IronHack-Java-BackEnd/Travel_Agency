package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
}
