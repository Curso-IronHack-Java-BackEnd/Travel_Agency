package com.miguelprojects.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ManagerRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ManagerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerServiceTest {

    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerRepository managerRepository;

    private List<Manager> managers;

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
    }

    @AfterEach
    void tearDown() {
        managerRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }


    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void getAllManager() {
    }

    @Test
    void getManagerById() {
    }

    @Test
    void getManagerByUsername() {
    }

    @Test
    void deleteManagerByUsername() {
    }

    @Test
    void createManager() {
    }

    @Test
    void updateManager() {
    }
}