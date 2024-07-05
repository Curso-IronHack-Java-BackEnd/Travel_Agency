package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.ReservationCreateDTO;
import com.miguelprojects.travel_agency.DTOs.ReservationUpdateDTO;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private UserRepository userRepository;


    // Obtener todos los Reservations (getAll)
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    // Obtener una Reservation concreta (getById)
    public Reservation getReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Code: "+ reservationCode + " not found"));

        return reservation;
    }

    // Eliminar una Reservation concreta (deleteById)
    public void deleteReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Code: "+  reservationCode + " not found"));
        Travel travel = travelRepository.findById(reservation.getTravel().getTravelId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Reservation doesn´t have any assigned travel"));

        for (HotelBooking hotelBooking : travel.getHotelBookings()) {
                hotelBooking.setTravel(null);
        }
        for (FlightBooking flightBooking : travel.getFlightBookings()) {
            flightBooking.setTravel(null);
        }
        travel.getHotelBookings().clear();
        travel.getFlightBookings().clear();
        travel.setReservation(null);

        reservation.setAgent(null);
        reservation.setCustomer(null);
//        reservation.setTravel(null);
        reservationRepository.save(reservation);

        reservationRepository.delete(reservation);
    }

    // Crear nueva Reservation (create/post)
    public Reservation createReservation (String username, ReservationCreateDTO reservationDTO){
        User user = userRepository.findByUsername(username);
        Agent agent = user.getAgent();
        Customer customer = customerRepository.findById(reservationDTO.getCustomerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Reservation doesn´t have any assigned customer"));

        Reservation newReservation = new Reservation();
        newReservation.setReservationCode(reservationDTO.getReservationCode());
        newReservation.setAdults(reservationDTO.getAdults());
        newReservation.setChildren(reservationDTO.getChildren());
        newReservation.setPromotions(reservationDTO.getPromotions());
        newReservation.setPaymentMethod(reservationDTO.getPaymentMethod());
        newReservation.setReservationStatus(reservationDTO.getReservationStatus());
        newReservation.setDeposit(reservationDTO.getDeposit());
        newReservation.setDateOfReservation(reservationDTO.getDateOfReservation());
        newReservation.setAgent(agent);
        newReservation.setCustomer(customer);

        return reservationRepository.save(newReservation);
    }

    // Modificar una Reservation concreta (update/ById)
    public Reservation updateReservation (String reservationCode, ReservationUpdateDTO reservationDTO){

        Reservation updatedReservation = reservationRepository.findById(reservationCode).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + reservationCode +" not found"));

        if (reservationDTO.getPromotions() != null){
            updatedReservation.setPromotions(reservationDTO.getPromotions());
        }
        if (reservationDTO.getAdults() != null){
            updatedReservation.setAdults(reservationDTO.getAdults());
        }
        if (reservationDTO.getChildren() != null){
            updatedReservation.setChildren(reservationDTO.getChildren());
        }
        if (reservationDTO.getPaymentMethod() != null){
            updatedReservation.setPaymentMethod(reservationDTO.getPaymentMethod());
        }
        if (reservationDTO.getReservationStatus() != null){
            updatedReservation.setReservationStatus(reservationDTO.getReservationStatus());
        }
        if (reservationDTO.getDeposit() != null){
            updatedReservation.setDeposit(reservationDTO.getDeposit());
        }
        if (reservationDTO.getDateOfReservation() != null){
            updatedReservation.setDateOfReservation(reservationDTO.getDateOfReservation());
        }

        reservationRepository.save(updatedReservation);

        return updatedReservation;
    }

    public List <Reservation> getReservationsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " +
                        customerId+ " not found"));

        List<Reservation> reservations = customer.getReservations();

        return reservations;
    }

    // Obtener todas las reservations de un user (getReservationsByUsername)
    public List <Reservation> getReservationsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Customer customer = user.getCustomer();

        List<Reservation> reservations = customer.getReservations();

        return reservations;
    }

}
