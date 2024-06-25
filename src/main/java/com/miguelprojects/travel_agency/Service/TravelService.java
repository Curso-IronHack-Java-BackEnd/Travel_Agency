package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
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

    public List<Travel> getAllTravel() {
        return travelRepository.findAll();
    }

    public Travel getTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+ travelId + " not found"));

        return travel;
    }

    public void deleteTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+  travelId + " not found"));

        travelRepository.delete(travel);
    }
}
