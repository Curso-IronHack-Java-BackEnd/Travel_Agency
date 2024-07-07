package com.miguelprojects.travel_agency.repository;

import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Role;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.RoleRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private List<Role> roles;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        roles = roleRepository.saveAll(List.of(
                new Role(null, "ROLE_CUSTOMER"),
                new Role(null, "ROLE_AGENT"),
                new Role(null, "ROLE_MANAGER"),
                new Role(null, "ROLE_PRUEBA")
        ));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }

    @Test
    @DisplayName("findByName Method---Ok")
    void findByName_existingRole_RoleReturned() {
        Role foundRole = roleRepository.findByName("ROLE_PRUEBA");
        assertNotNull(foundRole);
        assertEquals("ROLE_PRUEBA", foundRole.getName());
    }

    @Test
    @DisplayName("findByName Method---notFound")
    void findByName_nonExistingRole_notFound() {
        Role foundRole = roleRepository.findByName("ROLE_NOT_FOUND");
        assertNull(foundRole);
    }

}
