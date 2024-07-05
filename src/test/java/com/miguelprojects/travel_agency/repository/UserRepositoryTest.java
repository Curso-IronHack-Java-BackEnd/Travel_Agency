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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setUsername("testUser1");
        User user2 = new User();
        user2.setUsername("testUser2");
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("findByUsername Method---Ok")
    void findByUsername_existingUsername_userReturned() {
        User found = userRepository.findByUsername("testUser1");
        assertNotNull(found);
        assertEquals("testUser1", found.getUsername());
    }

    @Test
    @DisplayName("existsByUsername Method---Ok")
    void existsByUsername_existingUsername_trueReturned() {
        Boolean exists = userRepository.existsByUsername("testUser1");
        assertTrue(exists);
    }

    @Test
    @DisplayName("existByUsername Method---notFound")
    void existsByUsername_nonExistingUsername_falseReturned() {
        Boolean exists = userRepository.existsByUsername("nonExistingUser");
        assertFalse(exists);
    }
}
