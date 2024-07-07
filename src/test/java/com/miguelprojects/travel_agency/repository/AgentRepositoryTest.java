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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AgentRepositoryTest {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private UserRepository userRepository;

    private List<Agent> agents;
    private List<User> users;

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

        users = userRepository.saveAll(List.of(
                new User(null, "Pedro Lopez", "pedro", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucia Marin", "lucia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Marta Polo", "marta", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucas Patiño", "lucas", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Sofia Genova", "sofia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Antonio Casas", "antonio", "1234", new ArrayList<>(), null, null, null)
        ));
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
    @DisplayName("findBySpecialization Method---Ok")
    void findBySpecialization_existingSpecialization_AgentsReturned() {
        List<Agent> foundAgents = agentRepository.findBySpecialization("Sales");
        assertNotNull(foundAgents);
        assertEquals(3, foundAgents.size());
    }

    @Test
    @DisplayName("findBySpecialization Method---notFound")
    void findBySpecialization_nonExistingSpecialization_notFound() {
        List <Agent> foundAgents = agentRepository.findBySpecialization("Ventas");
        assertNotNull(foundAgents);
        assertEquals(0, foundAgents.size());
    }

    @Test
    @DisplayName("existByUsername Method---Ok")
    void existsByUsername_ExistingUsername_AgentReturned() {
        Boolean exists = userRepository.existsByUsername("Lucia");
        assertTrue(exists);
    }

    @Test
    @DisplayName("existByUsername Method---notFound")
    void existsByUsername_nonExistingUsername_falseReturned() {
        Boolean exists = userRepository.existsByUsername("nonExistingUser");
        assertFalse(exists);
    }
}
