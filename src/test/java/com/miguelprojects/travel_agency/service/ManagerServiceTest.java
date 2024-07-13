package com.miguelprojects.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerUpdateDTO;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ManagerRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ManagerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ManagerServiceTest {

    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;

    private List<Manager> managers;
    private List<User> users;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        managers = managerRepository.saveAll(List.of(
                new Manager("Lucas", "Patiño", "676542335", "lucaspatiño@hotmail.com", "Sales", 4, null),
                new Manager("Juan Jose", "Perez", "645223676", "juanjoseperez@hotmail.com", "Operations", 12, null),
                new Manager("Margarita", "Garces", "908564345", "margaritagarces@hotmail.com", "Marketing", 2, null),
                new Manager("Maria", "Jimenez", "926785676", "mariajimenez@hotmail.com", "Administration", 6, null),
                new Manager("Salvador", "Dominguez", "666478423", "salvadordominguez@hotmail.com", "Product", 10, null),
                new Manager("Lucia", "Calles", "676009897", "luciacalles@hotmail.com", "Sales", 5, null)
        ));
        users = userRepository.saveAll(List.of(
                new User(null, "Pedro Lopez", "pedrito", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucia Marin", "lucia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Marta Polo", "marta", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucas Patiño", "lucas", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Sofia Genova", "sofia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Antonio Casas", "antonio", "1234", new ArrayList<>(), null, null, null)
        ));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }


    @Test
    @DisplayName("getAllManager Method---Ok")
    void getAllManager() {
        List<Manager> foundManagers = managerService.getAllManager();
        assertNotNull(foundManagers);
        assertEquals(6, foundManagers.size());
        assertEquals("Lucia", foundManagers.getLast().getFirstName());
    }

    @Test
    @DisplayName("getManagerById Method---Ok")
    void getManagerById() {
        Manager manager = managerRepository.findAll().get(4);
        Manager found = managerService.getManagerById(manager.getManagerId());
        assertNotNull(found);
        assertEquals("Salvador", found.getFirstName());
    }

    @Test
    @DisplayName("getManagerById Method---notFoundException")
    void getManagerById__wrongId_notFoundException() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            managerService.getManagerById(0L);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("getManagerByUsername Method---Ok")
    void getManagerByUsername() {
        Manager manager = new Manager("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", 6, null);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, manager);
        managerRepository.save(manager);
        userRepository.save(user);

        Manager foundManager = managerService.getManagerByUsername("jose");

        assertNotNull(foundManager);
        assertEquals("Jose", foundManager.getFirstName());
        assertEquals("josito@hotmail.com", foundManager.getEmail());
    }

    @Test
    @DisplayName("getManagerByUsername Method---nullPointerException")
    void getManagerByUsername_nonExistingUsername_nullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            managerService.getManagerByUsername("Andrea");
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    @DisplayName("deleteManagerByUsername Method---Ok")
    void deleteManagerByUsername_Ok() {
        Manager manager = new Manager("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", 6, null);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, manager);
        managerRepository.save(manager);
        userRepository.save(user);

        assertEquals(7, managerRepository.findAll().size());

        managerService.deleteManagerByUsername("jose");

        assertNotNull(user);
        assertNotNull(manager);
        assertNotEquals("Jose", managerRepository.findAll().getLast().getFirstName());
        assertEquals(6, managerRepository.findAll().size());
    }

    @Test
    @DisplayName("deleteManagerByUsername Method---nullPointerException")
    void deleteManagerByUsername_wrongUsername_nullPointerException() {
        Manager manager = new Manager("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", 6, null);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, manager);
        managerRepository.save(manager);
        userRepository.save(user);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            managerService.deleteManagerByUsername("Mariano");
        });

        assertNotNull(exception.getMessage());
        assertNotNull(manager);
        assertNotNull(user);
    }

    @Test
    @DisplayName("createManager Method---Ok")
    void createOwnManager() {
        ManagerCreateDTO managerDTO = new ManagerCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", 6);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, null);
        userRepository.save(user);

        Manager myNewManager = managerService.createManager("jose", managerDTO);
        managers.add(myNewManager);

        assertEquals("Jose", myNewManager.getFirstName());
        assertEquals("Garcia", managers.getLast().getLastName());
    }

    @Test
    @DisplayName("createManager Method--Conflict")
    void createOwnManager_existingManager_Conflict() {
        ManagerCreateDTO managerDTO = new ManagerCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", 6);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, null);
        userRepository.save(user);
        Manager manager = managerService.createManager("jose", managerDTO);
        user.setManager(manager);
        managers.add(manager);
        userRepository.save(user);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            managerService.createManager("jose", managerDTO);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());

        assertEquals(user.getManager().getEmail(), managers.getLast().getEmail());
    }

    @Test
    @DisplayName("updateManager Method--Ok")
    void updateManager() {
        ManagerUpdateDTO managerDTO = new ManagerUpdateDTO("Operations", 10);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null , null, managers.getLast());
        userRepository.save(user);

        Manager myUpdatedManager = managerService.updateManager("jose", managerDTO);
        managers.add(myUpdatedManager);

        assertEquals("Lucia", myUpdatedManager.getFirstName());
        assertEquals("Operations", managers.getLast().getDepartment());

    }

    @Test
    @DisplayName("updateManager Method--wrong Username--nullPointerException")
    void updateManager_wrongUsername_notFound() {
        ManagerUpdateDTO managerDTO = new ManagerUpdateDTO("Operations", 10);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null , null, managers.getLast());
        userRepository.save(user);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            managerService.updateManager("Manolo", managerDTO);
        });

        assertInstanceOf(NullPointerException.class, exception, "La excepción no es del tipo NullPointerException");
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("null"));
        assertNull(exception.getCause());
        assertEquals(NullPointerException.class, exception.getClass());

    }
}