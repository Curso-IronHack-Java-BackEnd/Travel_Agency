package com.miguelprojects.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Service.AgentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgentServiceTest {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentService agentServic;

    private List<Agent> agents;

    @BeforeEach
    void setUp() {
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
        agentRepository.deleteAll();
    }

    @Test
    void getAllAgent() {
    }

    @Test
    void getAgentById() {
    }

    @Test
    void getAgentByUsername() {
    }

    @Test
    void deleteAgentById() {
    }

    @Test
    void createAgent() {
    }

    @Test
    void selfCreateAgent() {
    }

    @Test
    void updateAgent() {
    }

    @Test
    void updateAgentByUsername() {
    }

    @Test
    void patchAgentCommission() {
    }

    @Test
    void agentsBySpecialization() {
    }
}