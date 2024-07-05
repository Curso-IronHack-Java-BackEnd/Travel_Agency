package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.Travel;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ReservationService;
import com.miguelprojects.travel_agency.Service.TravelService;
import com.miguelprojects.travel_agency.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLOutput;
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
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;


//    // Buscar customer por id, lo hace el agent(getCustomerById)
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Customer getCustomerById(@PathVariable Long id) {
//        return customerRepository.findById(id).orElseThrow
//                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
//    }

    //El cliente solo puede ver su perfil (getCustomerByUsername)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        return customerService.getCustomerByUsername(username);
    }

    //Update su propio perfil (updateCustomerByUsername)
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody CustomerUpdateDTO customer) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        customerService.updateCustomerByUsername(username, customer);
    }

    //Post new customer (postCustomer)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody CustomerCreateDTO customer) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        return customerService.createOwnCustomer(username, customer);
    }


    // Ver todos los detalles del travel (getTravelByUsername) (SOLO SU VIAJE)
    @GetMapping("/travels")
    @ResponseStatus(HttpStatus.OK)
    public List<Travel> getTravelByUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        return travelService.getTravelsByUsername(username);
    }

    // Ver todos los detalles de la reservation (getReservationByUsername) (SOLO SU RESERVA)
    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationByUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        return reservationService.getReservationsByUsername(username);
    }


    //Obtener Bills por cada Travel

}
