package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AgentRepository agentRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow
        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: "+ customerId + " not found"));

        return customer;
    }

    public void deleteCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: "+  customerId + " not found"));

        customerRepository.delete(customer);
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Reservation Code: "+ reservationCode + " not found"));

        return reservation;
    }

    public List <Reservation> getReservationsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " +
                        customerId+ " not found"));

        List<Reservation> reservations = customer.getReservations();

        return reservations;
    }

    public Reservation createReservation (Reservation reservation){

        Agent agent = agentRepository.findById(reservation.getAgent().getAgentId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent " + reservation.getAgent().getAgentId() +" not found"));

        Customer customer = customerRepository.findById(reservation.getCustomer().getCustomerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer " +
                        reservation.getCustomer().getFirstName()+" "+reservation.getCustomer().getLastName() +" not found"));

        Reservation newReservation = new Reservation();
        newReservation.setPromotions(reservation.getPromotions());
        newReservation.setAdults(reservation.getAdults());
        newReservation.setChildren(reservation.getChildren());
        newReservation.setPaymentMethod(reservation.getPaymentMethod());
        newReservation.setReservationStatus(reservation.getReservationStatus());
        newReservation.setDeposit(reservation.getDeposit());
        newReservation.setDateOfReservation(reservation.getDateOfReservation());
        newReservation.setAgent(agent);
        newReservation.setCustomer(customer);

        return reservationRepository.save(newReservation);
    }

    public void deleteReservationById(String reservationCode) {
        Reservation reservation = reservationRepository.findById(reservationCode).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with Reservation Code: "+  reservationCode + " not found"));

        reservationRepository.delete(reservation);
    }

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

    public Travel createTravel (Travel travel){

        Customer customer = customerRepository.findById(travel.getCustomer().getCustomerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer " +
                        travel.getCustomer().getFirstName()+" "+travel.getCustomer().getLastName() +" not found"));

        Travel newTravel = new Travel();
        newTravel.setDestination(travel.getDestination());
        newTravel.setDuration(travel.getDuration());
        newTravel.setFinalPrice(travel.getFinalPrice());
        newTravel.setCustomer(customer);

        return travelRepository.save(newTravel);
    }
}
