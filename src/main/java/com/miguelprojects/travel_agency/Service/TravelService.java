package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.TravelCreateDTO;
import com.miguelprojects.travel_agency.DTOs.TravelUpdateDTO;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;


    // Obtener todos los Travels (getAll)
    public List<Travel> getAllTravel() {
        return travelRepository.findAll();
    }

    // Obtener un Travel concreto (getById)
    public Travel getTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+ travelId + " not found"));

        return travel;
    }

    // Eliminar un Travel concreto (deleteById)
    public void deleteTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+  travelId + " not found"));

        travelRepository.delete(travel);
    }

    // Crear nuevo Travel (create/post)
    public Travel createTravel (TravelCreateDTO travelDTO){

        Reservation reservation = reservationRepository.findById(travelDTO.getReservationCode()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Travel does not have any assigned reservation"));

        Customer customer = customerRepository.findById(travelDTO.getCustomerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Travel does not have any assigned customer"));

        Travel newTravel = new Travel();
        newTravel.setDestination(travelDTO.getDestination());
        newTravel.setDuration(travelDTO.getDuration());
        newTravel.setFinalPrice(travelDTO.getFinalPrice());
        newTravel.setReservation(reservation);
        newTravel.setCustomer(customer);

        return travelRepository.save(newTravel);
    }

    // Modificar un Travel concreto (update/ById)
    public Travel updateTravel (Long travelId, TravelUpdateDTO travelDTO){

        Travel updatedTravel = travelRepository.findById(travelId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel " + travelId +" not found"));

        if (travelDTO.getDestination() != null){
            updatedTravel.setDestination(travelDTO.getDestination());
        }
        if (travelDTO.getDuration() != null){
            updatedTravel.setDuration(travelDTO.getDuration());
        }
        if (travelDTO.getFinalPrice() != null){
            updatedTravel.setFinalPrice(travelDTO.getFinalPrice());
        }

        return updatedTravel;
    }

}
