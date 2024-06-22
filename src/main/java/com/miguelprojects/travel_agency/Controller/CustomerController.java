package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ReservationService;
import com.miguelprojects.travel_agency.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private ReservationService reservationService;


    // El cliente solo debe de poder ver su perfil(getCustomerById)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@RequestParam Long id) {
        return customerRepository.findById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // Ver todos los detalles del travel (getTravelByCustomerId)
    @GetMapping("/travels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Travel getTravelById(@RequestParam (name = "id")Long travelId) {
        return travelRepository.findById(travelId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    //Update su propio perfil (updateCustomerById)
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

    }



    //Post new customer (postCustomer)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) {

    }



    //Get reservation details(getReservationByCustomerId)
//    @GetMapping("/reservations/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Reservation getReservationById(@RequestParam Long id) {
//    }
}
