package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Flight;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlight() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight with id: "+ flightId + " not found"));

        return flight;
    }

    public void deleteFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight with id: "+  flightId + " not found"));

        flightRepository.delete(flight);
    }
}
