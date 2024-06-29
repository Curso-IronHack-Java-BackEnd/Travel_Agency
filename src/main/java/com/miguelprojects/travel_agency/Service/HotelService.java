package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.Models.Hotel;
import com.miguelprojects.travel_agency.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    // Obtener todos los Hotels (getAll)
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    // Obtener un Hotel concreto (getById)
    public Hotel getHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel with id: "+ hotelId + " not found"));

        return hotel;
    }

    // Eliminar un Hotel concreto (deleteById)
    public void deleteHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel with id: "+  hotelId + " not found"));

        hotelRepository.delete(hotel);
    }

    // Crear nuevo Hotel (create/post)
    // Modificar un Hotel concreto (update/ById)
}
