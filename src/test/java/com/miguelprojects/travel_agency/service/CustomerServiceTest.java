package com.miguelprojects.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import com.miguelprojects.travel_agency.Service.AgentService;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.util.bcel.classfile.EnumElementValue;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.miguelprojects.travel_agency.Enums.PaymentMethod.CASH;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AgentService agentService;
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private List<Customer> customers;
    private List<User> users;
    private List<Agent> agents;
    private List<Reservation> reservations;


    @BeforeEach
    void setUp() {
        customers = customerRepository.saveAll(List.of(

                new Customer("Angela", "Rodriguez", "676542335", "angelarodriguez@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"), null, null),
                new Customer("Juan Carlos", "Sanchez", "645223676", "juancarlossanchez@hotmail.com", "C/ Culebras, 34", LocalDate.parse("1999-02-15"),null, null),
                new Customer("Sofia", "Teruel", "908564345", "sofiateruel@hotmail.com", "C/ Santo Tomas, 1", LocalDate.parse("2001-02-15"),null, null),
                new Customer("Alba", "Rojas", "926785676", "albarojas@gmail.com", "C/ Luciernaga, 3", LocalDate.parse("2000-02-15"), null, null),
                new Customer("Julio", "Bermudez", "666478423", "juliobermudez@gmail.com", "C/ Melancolia 34", LocalDate.parse("1989-02-15"), null, null),
                new Customer("Marcos", "Tenorio", "676009897", "marcostenorio@gmail.com", "C/ Roma, 33", LocalDate.parse("1978-02-15"), null, null)
        ));
        users = userRepository.saveAll(List.of(
                new User(null, "Pedro Lopez", "pedrito", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucia Marin", "lucia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Marta Polo", "marta", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucas Patiño", "lucas", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Sofia Genova", "sofia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Antonio Casas", "antonio", "1234", new ArrayList<>(), null, null, null)
        ));
        agents = agentRepository.saveAll(List.of(
                new Agent("Elena", "Peña", "676542335", "elenapeña@hotmail.com", "Sales", BigDecimal.valueOf(2.75),null, null),
                new Agent("Yago", "Martin", "645223676", "yagomartin@hotmail.com", "Operations", BigDecimal.valueOf(12.25), null, null),
                new Agent("Miguel", "Payes", "908564345", "miguelpayes@hotmail.com", "Operations", BigDecimal.valueOf(5.33), null, null),
                new Agent("Domingo", "Ruiz", "926785676", "domingoruiz@hotmail.com", "Sales" ,BigDecimal.valueOf(6.50), null, null),
                new Agent("Santiago", "Gonzalez", "666478423", "santiagogonzalez@hotmail.com", "Marketing", BigDecimal.valueOf(10.00), null, null),
                new Agent("Marta", "Pozo", "676009897", "martapozo@hotmail.com", "Sales", BigDecimal.valueOf(5.50), null, null)
        ));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        customerRepository.deleteAll();
        agentRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }


    @Test
    @DisplayName("getAllCustomer Method---Ok")
    void getAllCustomer_Ok() {
        List<Customer> foundCustomers = customerService.getAllCustomer();
        assertNotNull(foundCustomers);
        assertEquals(6, foundCustomers.size());
        assertEquals("Marcos", foundCustomers.getLast().getFirstName());
    }

    @Test
    @DisplayName("getCustomerById Method---Ok")
    void getCustomerById() {
        Customer customer = customerRepository.findAll().getFirst();
        Customer found = customerService.getCustomerById(customer.getCustomerId());
        assertNotNull(found);
        assertEquals("Angela", found.getFirstName());
    }

    @Test
    @DisplayName("getCustomerById Method--wrongId--notFoundException")
    void getCustomerById_notFoundException() {
        assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerById(0L);
        });
    }

    @Test
    @DisplayName("getCustomerByUsername Method---Ok")
    void getCustomerByUsername() {
        Customer customer = new Customer("Jose", "Garcia", "676542335", "josito@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"), null, null);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), customer, null, null);
        customerRepository.save(customer);
        userRepository.save(user);

        Customer foundCustomer = customerService.getCustomerByUsername("jose");

        assertNotNull(foundCustomer);
        assertEquals("Jose", foundCustomer.getFirstName());
        assertEquals("josito@hotmail.com", foundCustomer.getEmail());
    }

    @Test
    @DisplayName("getCustomerByUsername Method---nullPointerException")
    void getCustomerByUsername_nullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            customerService.getCustomerByUsername("Andrea");
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    @DisplayName("deleteCustomerById Method---Ok")
    void deleteCustomerById_Ok() {
        customerRepository.deleteById(1L);
        assertFalse(customerRepository.existsById(1L));
    }

    @Test
    @DisplayName("deleteCustomerById Method--wrongId--notFound")
    void deleteCustomerById_notFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.deleteCustomerById(0L);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("createCustomer Method---Ok")
    void createCustomer_Ok() {
        CustomerCreateDTO customer = new CustomerCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"));
        Customer foundCustomer = customerService.createCustomer(customer);
        customers.add(foundCustomer);

        assertEquals("Jose", foundCustomer.getFirstName());
        assertEquals("Garcia", customers.getLast().getLastName());
    }

    @Test
    @DisplayName("createCustomer Method---validationException")
    void createCustomer_wrongData_validationException() {
        CustomerCreateDTO customer = new CustomerCreateDTO(null, "Garcia", "676542335", "josito@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"));
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            customerService.createCustomer(customer);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    @DisplayName("createOwnCustomer Method---Ok")
    void createOwnCustomer_Ok() {
        CustomerCreateDTO customerDTO = new CustomerCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"));
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, null);
        userRepository.save(user);

        Customer myNewCustomer = customerService.createOwnCustomer("jose", customerDTO);
        customers.add(myNewCustomer);

        assertEquals("Jose", myNewCustomer.getFirstName());
        assertEquals("Garcia", customers.getLast().getLastName());
    }

    @Test
    @DisplayName("createOwnCustomer Method---existingCustomer--Conflict")
    void createOwnCustomer_existingCustomer_Conflict() {
        CustomerCreateDTO customerDTO = new CustomerCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "C/ Blanca, 12", LocalDate.parse("1990-02-15"));
        Customer customer = customerService.createCustomer(customerDTO);
        customers.add(customer);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), customer, null, null);
        userRepository.save(user);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.createOwnCustomer("jose", customerDTO);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());

        assertEquals(user.getCustomer().getEmail(), customers.getLast().getEmail());
    }

    @Test
    @DisplayName("updateCustomer Method---Ok")
    void updateCustomer() {
        Customer customer = customerRepository.findAll().getFirst();
        Customer found = customerService.getCustomerById(customer.getCustomerId());
        CustomerUpdateDTO customerDTO = new CustomerUpdateDTO("Callejon del Gato, 19", LocalDate.parse("1990-02-15"));

        Customer updatedCustomer = customerService.updateCustomer(customer.getCustomerId(), customerDTO);
        customers.add(updatedCustomer);

        assertEquals(customers.getLast().getFirstName(), found.getFirstName() );
        assertEquals("Callejon del Gato, 19", customers.getLast().getAddress());
    }

    @Test
    @DisplayName("updateCustomer Method---notFound")
    void updateCustomer_invalidId_notFound() {
        CustomerUpdateDTO customerDTO = new CustomerUpdateDTO("Callejon del Gato, 19", LocalDate.parse("1990-02-15"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.updateCustomer(0L, customerDTO);
        });

        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }


    @Test
    @DisplayName("updateCustomerByUsername Method---Ok")
    void updateCustomerByUsername_Ok() {
        CustomerUpdateDTO customerDTO = new CustomerUpdateDTO("Callejon del Gato, 19", LocalDate.parse("1990-02-15"));
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), customers.getLast(), null, null);
        userRepository.save(user);

        Customer myUpdatedCustomer = customerService.updateCustomerByUsername("jose", customerDTO);
        customers.add(myUpdatedCustomer);

        assertEquals("Marcos", myUpdatedCustomer.getFirstName());
        assertEquals("Callejon del Gato, 19", customers.getLast().getAddress());
    }


    @Test
    @DisplayName("updateCustomerByUsername Method---nullPointerException")
    void updateCustomerByUsername_wrongUsername_nullPointerException() {
        CustomerUpdateDTO customerDTO = new CustomerUpdateDTO("Callejon del Gato, 19", LocalDate.parse("1990-02-15"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.updateCustomerByUsername("jose", customerDTO);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("getCustomersByAgentId Method---Ok")
    void getCustomersByAgentId_ok() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Reservation reservation1= new Reservation("AD456", 2, 1, Promotions.EARLY_BOOKING, PaymentMethod.CASH, ReservationStatus.PENDING, BigDecimal.ONE, LocalDate.parse("2024-06-10", formatter), agents.getFirst(), customers.getFirst(), null);
        Reservation reservation2= new Reservation("AE346", 5, 2, Promotions.EARLY_BOOKING, PaymentMethod.CASH, ReservationStatus.PENDING, BigDecimal.ONE, LocalDate.parse("2024-06-10", formatter), agents.getFirst(), customers.getFirst(), null);
        Reservation reservation3= new Reservation("GF765", 2, 0, Promotions.EARLY_BOOKING, PaymentMethod.CASH, ReservationStatus.PENDING, BigDecimal.ONE, LocalDate.parse("2024-06-10", formatter), agents.getLast(), customers.getLast(), null);
        Agent agent1 = agentRepository.findAll().getFirst();
        Agent agent2 = agentRepository.findAll().getLast();

        reservations = reservationRepository.saveAll(List.of(reservation1,reservation2,reservation3));

        agent1.setReservations(List.of(reservation1, reservation2));
        agent2.setReservations(List.of(reservation3));

        agentRepository.save(reservations.getFirst().getAgent());
        agentRepository.save(reservations.getLast().getAgent());

        List<Customer> foundCustomers = customerService.getCustomersByAgentId(agent1.getAgentId());

        assertNotNull(foundCustomers);
        assertEquals(2, foundCustomers.size());
    }

        @Test
        @DisplayName("getCustomersByAgentId Method---notFound")
        void getCustomersByAgentId__wrongAgent__notFound() {
            ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
                customerService.getCustomersByAgentId(0L);
            });

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        }
}