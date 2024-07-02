package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.HotelBooking;
import com.miguelprojects.travel_agency.Models.FlightBooking;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TravelRepository travelRepository;


    // Obtener todos los Customers (getAll)
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    // Obtener un Customer concreto (getById)
    public Customer getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: "+ customerId + " not found"));

        return customer;
    }

    // Eliminar un Customer concreto (deleteById)
    public void deleteCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: "+  customerId + " not found"));

        // Primero chequear que tiene reservas (no es null) lo mismo para travels, reservation y travel hotel/flight bookings etc para no intentar "get" algo que es null
        for (Reservation reservation : customer.getReservations()) {
            reservation.setCustomer(null);
            reservationRepository.save(reservation);
        }

        for (Travel travel : customer.getTravels()) {
            for (HotelBooking hotelBooking : travel.getHotelBookings()) {
                hotelBooking.setTravel(null);
            }
            for (FlightBooking flightBooking : travel.getFlightBookings()) {
                flightBooking.setTravel(null);
            }
            travel.getHotelBookings().clear();
            travel.getFlightBookings().clear();
            travel.setCustomer(null);
            travelRepository.save(travel);
        }

        customer.getReservations().clear();
        customer.getTravels().clear();
        customerRepository.save(customer);

        // Delete customer
        customerRepository.delete(customer);
    }

    // Crear nuevo Customer (create/post)
    public Customer createCustomer (CustomerCreateDTO customerDTO){

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDTO.getFirstName());
        newCustomer.setLastName(customerDTO.getLastName());
        newCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        newCustomer.setEmail(customerDTO.getEmail());
        newCustomer.setAddress(customerDTO.getAddress());
        newCustomer.setDateOfBirth(customerDTO.getDateOfBirth());

        return customerRepository.save(newCustomer);
    }

    // Modificar un Customer concreto (update/ById)
    public Customer updateCustomer (Long customerId, CustomerUpdateDTO customerDTO){

        Customer updatedCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer " + customerId +" not found"));

        if (customerDTO.getFirstName() != null){
            updatedCustomer.setFirstName(customerDTO.getFirstName());
        }
        if (customerDTO.getLastName() != null){
            updatedCustomer.setLastName(customerDTO.getLastName());
        }
        if (customerDTO.getPhoneNumber() != null){
            updatedCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        }
        if (customerDTO.getEmail() != null){
            updatedCustomer.setEmail(customerDTO.getEmail());
        }
        if (customerDTO.getAddress() != null){
            updatedCustomer.setAddress(customerDTO.getAddress());
        }
        if (customerDTO.getDateOfBirth() != null){
            updatedCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        }

        customerRepository.save(updatedCustomer);

        return updatedCustomer;
    }

    //Obtener todos los customers por Agent (getCustomerByAgentId)
    public List<Customer> getCustomersByAgentId(Long agentId) {
        Agent agent = agentRepository.findById(agentId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent with id " +
                        agentId+ " not found"));

        List<Customer> customers = new ArrayList<>();

        List<Reservation> reservations = agent.getReservations();
        for (Reservation reservation : reservations){
            customers.add(reservation.getCustomer());
        }
        return customers;
    }

}
