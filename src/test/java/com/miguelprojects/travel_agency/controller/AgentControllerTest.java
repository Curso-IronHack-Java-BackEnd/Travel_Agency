package com.miguelprojects.travel_agency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AgentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Agent> agents;
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        agents = agentRepository.saveAll(List.of(
                new Agent("Elena", "Peña", "676542335", "elenapeña@hotmail.com", "Sales", BigDecimal.valueOf(2.75),null, null),
                new Agent("Yago", "Martin", "645223676", "yagomartin@hotmail.com", "Operations", BigDecimal.valueOf(12.25), null, null),
                new Agent("Miguel", "Payes", "908564345", "miguelpayes@hotmail.com", "Operations", BigDecimal.valueOf(5.33), null, null),
                new Agent("Domingo", "Ruiz", "926785676", "domingoruiz@hotmail.com", "Sales" ,BigDecimal.valueOf(6.50), null, null),
                new Agent("Santiago", "Gonzalez", "666478423", "santiagogonzalez@hotmail.com", "Marketing", BigDecimal.valueOf(10.00), null, null),
                new Agent("Marta", "Pozo", "676009897", "martapozo@hotmail.com", "Sales", BigDecimal.valueOf(5.50), null, null)
        ));
        user = new User();
        user.setUsername("testUser");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        agentRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }

    @Test
    void getAgentByUsername_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/agents/testUser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Miguel"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Marketing"));
    }

    @Test
    void selfCreateAgent() {
    }

    @Test
    void updateAgent() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void getAllReservations() {
    }

    @Test
    void getReservationById() {
    }

    @Test
    void getReservationsByCustomerId() {
    }

    @Test
    void createReservation() {
    }

    @Test
    void updateReservation() {
    }

    @Test
    void deleteReservation() {
    }

    @Test
    void getAllTravels() {
    }

    @Test
    void getTravelById() {
    }

    @Test
    void getTravelsByCustomerId() {
    }

    @Test
    void createTravel() {
    }

    @Test
    void updateTravel() {
    }

    @Test
    void getAllHotels() {
    }

    @Test
    void getAllFlights() {
    }

    @Test
    void getFlightsByTravelId() {
    }

    @Test
    void getHotelsByTravelId() {
    }

    @Test
    void createBillWithExtras() {
    }

    @Test
    void getBillByTravelId() {
    }
}