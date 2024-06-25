package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Code: "+ reservationCode + " not found"));

        return reservation;
    }

    public void deleteReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Code: "+  reservationCode + " not found"));

        reservationRepository.delete(reservation);
    }
}
