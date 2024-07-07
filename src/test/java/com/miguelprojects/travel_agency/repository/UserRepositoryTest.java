package com.miguelprojects.travel_agency.repository;

import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<User> users;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }


    @Test
    @DisplayName("findByUsername Method---Ok")
    void findByUsername_existingUsername_userReturned() {
        User found = userRepository.findByUsername("pedrito");
        assertNotNull(found);
        assertEquals("pedrito", found.getUsername());
        assertEquals("Pedro Lopez", found.getName());
    }

    @Test
    @DisplayName("findByUsername Method---notFound")
    void findByUsername_nonExistingUsername_notFound() {
        User found = userRepository.findByUsername("Maria");
        assertNull(found);
    }

    @Test
    @DisplayName("existsByUsername Method---Ok")
    void existsByUsername_existingUsername_trueReturned() {
        Boolean exists = userRepository.existsByUsername("lucia");
        assertTrue(exists);
    }

    @Test
    @DisplayName("existByUsername Method---notFound")
    void existsByUsername_nonExistingUsername_falseReturned() {
        Boolean exists = userRepository.existsByUsername("nonExistingUser");
        assertFalse(exists);
    }
}
