package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
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
