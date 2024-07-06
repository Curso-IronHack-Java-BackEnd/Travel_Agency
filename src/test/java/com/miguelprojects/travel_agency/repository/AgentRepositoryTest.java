package com.miguelprojects.travel_agency.repository;

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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AgentRepositoryTest {

    @Autowired
    private AgentRepository agentRepository;

    private List<Agent> agents;

    @BeforeEach
    void setUp() {

//        Agent agent = new Agent(null, "Ventas", BigDecimal.valueOf(2.65), null,null);
//        agents.add(agent);
//        agentRepository.saveAll(agents);
//        agents = agentRepository.saveAll(List.of(
//
//                new Agent("miguel", "lopez", "4334355", "email@email.com", "ventas"),
//                new Agent(null, "sanchez", BigDecimal.ONE, null, null),
//                new Agent(1L, "Sales", BigDecimal.ONE, null, null),
//                new Agent(761527L, "Product", BigDecimal.TEN, null, null),
//                new Agent(166552L, "Sales", BigDecimal.TWO, null, null),
//                new Agent(156545L, "Marketing", BigDecimal.TWO, null, null),
//                new Agent(172456L, "Sales", BigDecimal.ONE, null, null)
//        ));
    }
    @AfterEach
    void tearDown() {
        agentRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

//    @Test
//    @DisplayName("findBySpecialization Method---Ok")
//    void findBySpecialization_existingSpecialization_AgentsReturned() {
//        List<Agent> foundAgents = agentRepository.findBySpecialization("Sales");
////        assertNotNull(foundAgents);
//        assertEquals(3, foundAgents.size());
//
//    }

//    @Test
//    @DisplayName("findBySpecialization Method---notFound")
//    void findBySpecialization_nonExistingSpecialization_notFound() {
//        List <Agent> foundAgents = agentRepository.findBySpecialization("Ventas");
//        assertNotNull(foundAgents);
//        assertEquals(0, agents.size());
//    }
//
//    @Test
//    @DisplayName("existByUsername Method---notFound")
//    void existsByUsername_nonExistingUsername_falseReturned() {
//        Boolean exists = userRepository.existsByUsername("nonExistingUser");
//        assertFalse(exists);
//    }
}
