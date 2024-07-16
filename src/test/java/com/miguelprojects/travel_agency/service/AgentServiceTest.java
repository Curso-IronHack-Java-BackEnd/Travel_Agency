package com.miguelprojects.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelprojects.travel_agency.DTOs.*;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.ManagerRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
import com.miguelprojects.travel_agency.Service.AgentService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgentServiceTest {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentService agentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ManagerRepository managerRepository;

    private List<Agent> agents;
    private List<User> users;
    private List<Manager> managers;

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
                new User(null, "Pedro Lopez", "pedrito", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucia Marin", "lucia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Marta Polo", "marta", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Lucas Patiño", "lucas", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Sofia Genova", "sofia", "1234", new ArrayList<>(), null, null, null),
                new User(null, "Antonio Casas", "antonio", "1234", new ArrayList<>(), null, null, null)
        ));

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
        userRepository.deleteAll();
        agentRepository.deleteAll();
        managerRepository.deleteAll();
    }


    @Test
    @DisplayName("Test Vacio para inicializar setUp")
    void contextLoads() {
    }


    @Test
    @DisplayName("getAllAgent Method---Ok")
    void getAllAgent_Ok() {
        List<Agent> foundAgents = agentService.getAllAgent();
        assertNotNull(foundAgents);
        assertEquals(6, foundAgents.size());
        assertEquals("Marta", foundAgents.getLast().getFirstName());
    }

    @Test
    @DisplayName("getAgentById Method---Ok")
    void getAgentById_Ok() {
        Agent agent = agentRepository.findAll().get(4);
        Agent found = agentService.getAgentById(agent.getAgentId());
        assertNotNull(found);
        assertEquals("Santiago", found.getFirstName());
    }

    @Test
    @DisplayName("getAgentById Method---notFound")
    void getAgentById_nonExistingId_notFoundException() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.getAgentById(0L);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("getAgentByUsername Method---Ok")
    void getAgentByUsername_Ok() {
        Agent agent = new Agent("Jose", "Garcia", "676542335", "josito@hotmail.com", "Sales", BigDecimal.valueOf(4.50),null, null);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, agent, null);
        agentRepository.save(agent);
        userRepository.save(user);

        Agent foundAgent = agentService.getAgentByUsername("jose");

        assertNotNull(foundAgent);
        assertEquals("Jose", foundAgent.getFirstName());
        assertEquals("Sales", foundAgent.getSpecialization());
        assertEquals("josito@hotmail.com", foundAgent.getEmail());
    }

    @Test
    @DisplayName("getAgentByUsername Method---notFound")
    void getAgentByUsername_nonExistingUsername_notFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.getAgentByUsername("Andrea");
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("deleteAgentById Method---Ok")
    void deleteAgentById() {
        agentRepository.deleteById(agents.getFirst().getAgentId());
        agentService.deleteAgentById(agentRepository.findAll().get(3).getAgentId());

        agents = agentRepository.findAll();

        assertEquals(4, agentRepository.findAll().size());
        assertEquals(4, agents.size());
        assertFalse(agentRepository.existsById(1L));
    }

    @Test
    @DisplayName("deleteAgentById Method--notFound")
    void deleteAgentById_wrongId_notFoundException() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.deleteAgentById(0L);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("createAgent Method---Ok")
    void createAgent_Ok() {
        AgentCreateDTO agent = new AgentCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "Marketing",BigDecimal.valueOf(4.50),managers.get(2).getManagerId());
        Agent foundAgent = agentService.createAgent(agent);
        agents.add(foundAgent);

        assertEquals("Jose", foundAgent.getFirstName());
        assertEquals("Garcia", agents.getLast().getLastName());
    }

    @Test
    @DisplayName("createAgent Method---notFound")
    void createAgent_noAssignedManager_notFound() {
        AgentCreateDTO agent = new AgentCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "Marketing",BigDecimal.valueOf(4.50),0L);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
        agentService.createAgent(agent);
    });
    // Verifica el código de estado
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("selfCreateAgent Method---Ok")
    void selfCreateAgent_ok() {
        AgentSelfCreateDTO agent = new AgentSelfCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com", "Marketing");
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, null, null);
        userRepository.save(user);

        Agent myNewAgent = agentService.selfCreateAgent("jose", agent);
        agents.add(myNewAgent);

        assertEquals("Jose", myNewAgent.getFirstName());
        assertEquals("Garcia", agents.getLast().getLastName());
    }

    @Test
    @DisplayName("selfCreateAgent Method---Conflict")
    void selfCreateAgent_existingAgent_Conflict() {
        AgentCreateDTO agentDTO = new AgentCreateDTO("Jose", "Garcia", "676542335", "josito@hotmail.com","Operations",BigDecimal.valueOf(4.50),managers.get(2).getManagerId());
        Agent agent = agentService.createAgent(agentDTO);
        agents.add(agent);
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, agent, null);
        userRepository.save(user);

        AgentSelfCreateDTO agentSelf = new AgentSelfCreateDTO(agentDTO.getFirstName(), agentDTO.getLastName(), agentDTO.getPhoneNumber(), agentDTO.getEmail(), agentDTO.getSpecialization());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.selfCreateAgent("jose", agentSelf);
        });
        // Verifica el código de estado
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());

        assertEquals(user.getAgent().getEmail(), agents.getLast().getEmail());
    }

    @Test
    @DisplayName("updateAgent Method---Ok")
    void updateAgent_Ok() {
        Agent agent = agentRepository.findAll().getFirst();
        Agent found = agentService.getAgentById(agent.getAgentId());
        AgentUpdateDTO agentDTO = new AgentUpdateDTO("Marketing");

        Agent updatedAgent = agentService.updateAgent(agent.getAgentId(), agentDTO);
        agents.add(updatedAgent);

        assertEquals(agents.getLast().getFirstName(), found.getFirstName() );
        assertEquals("Marketing", agents.getLast().getSpecialization());
    }

    @Test
    @DisplayName("updateAgent Method---notFound")
    void updateAgent_wrongId_notFound() {
        AgentUpdateDTO agentDTO = new AgentUpdateDTO("Marketing");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.updateAgent(0L, agentDTO);
        });

        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("updateAgentByUsername Method---Ok")
    void updateAgentByUsername_ok() {
        AgentUpdateDTO agentDTO = new AgentUpdateDTO("Marketing");
        User user = new User(null, "Jose Garcia", "jose", "1234", new ArrayList<>(), null, agents.getLast(), null);
        userRepository.save(user);

        Agent myUpdatedAgent = agentService.updateAgentByUsername("jose", agentDTO);
        agents.add(myUpdatedAgent);

        assertEquals("Marta", myUpdatedAgent.getFirstName());
        assertEquals("Marketing", agents.getLast().getSpecialization());
    }

    @Test
    @DisplayName("updateAgentByUsername Method---notFound")
    void updateAgentByUsername_nonExistingUsername_notFound() {
        AgentUpdateDTO agentDTO = new AgentUpdateDTO("Marketing");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.updateAgentByUsername("Paloma", agentDTO);
        });

        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("patchAgentCommission Method---Ok")
    void patchAgentCommission_Ok() {
        Agent agent = agentRepository.findAll().getFirst();
        BigDecimal commissionValue = new BigDecimal("7.50");
        AgentCommissionPatchDTO commisionDTO = new AgentCommissionPatchDTO(commissionValue);

        agentService.patchAgentCommission(agent.getAgentId(), commisionDTO);
        agents = agentRepository.findAll();


        assertEquals("Elena", agent.getFirstName());
        assertEquals(commissionValue, agents.getFirst().getCommissionRate());
        assertTrue(agents.getFirst().getCommissionRate().compareTo(BigDecimal.valueOf(7.50)) == 0);
    }

    @Test
    @DisplayName("patchAgentCommission Method---notFound")
    void patchAgentCommission_wrongId_notFound() {
        AgentCommissionPatchDTO commission = new AgentCommissionPatchDTO(BigDecimal.valueOf(7.50));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            agentService.patchAgentCommission(0L, commission);
        });

        // Verifica el código de estado
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("patchAgentCommission Method---Validation Error when commissionRate is null")
    void patchAgentCommission_NullCommissionRate_ValidationError() {
        Agent agent = agentRepository.findAll().getFirst();
        AgentCommissionPatchDTO commision = new AgentCommissionPatchDTO(null);

        TransactionSystemException exception = assertThrows(TransactionSystemException.class, () -> {
            agentService.patchAgentCommission(agent.getAgentId(), commision);
        });
        Throwable cause = exception.getCause();
        boolean found = false;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                found = true;
                break;
            }
            cause = cause.getCause();
        }
        assertTrue(found,  "CommissionRate must not be null");
    }

    @Test
    @DisplayName("patchAgentCommission Method---Validation Error when commissionRate is below minimum")
    void patchAgentCommission_BelowMinCommissionRate_ValidationError() {
        Agent agent = agentRepository.findAll().getFirst();
        AgentCommissionPatchDTO commision = new AgentCommissionPatchDTO(BigDecimal.valueOf(0.50));

        TransactionSystemException exception = assertThrows(TransactionSystemException.class, () -> {
            agentService.patchAgentCommission(agent.getAgentId(), commision);
        });
        Throwable cause = exception.getCause();
        boolean found = false;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                found = true;
                break;
            }
            cause = cause.getCause();
        }
        assertTrue(found,  "CommissionRate must be greater than 1.00%");
    }

    @Test
    @DisplayName("patchAgentCommission Method---Validation Error when commissionRate is above maximum")
    void patchAgentCommission_AboveMaxCommissionRate_ValidationError() {
        Agent agent = agentRepository.findAll().getFirst();
        AgentCommissionPatchDTO commision = new AgentCommissionPatchDTO(BigDecimal.valueOf(16.00));

        TransactionSystemException exception = assertThrows(TransactionSystemException.class, () -> {
            agentService.patchAgentCommission(agent.getAgentId(), commision);
        });
        Throwable cause = exception.getCause();
        boolean found = false;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                found = true;
                break;
            }
            cause = cause.getCause();
        }
        assertTrue(found,  "CommissionRate must be smaller than 15.00%");
    }

    @Test
    @DisplayName("patchAgentCommission Method---Validation Error when commissionRate has wrong format")
    void patchAgentCommission_WrongFormatCommissionRate_ValidationError() {
        Agent agent = agentRepository.findAll().getFirst();
        AgentCommissionPatchDTO commision = new AgentCommissionPatchDTO(new BigDecimal("12345.678"));

        TransactionSystemException exception = assertThrows(TransactionSystemException.class, () -> {
            agentService.patchAgentCommission(agent.getAgentId(), commision);
        });
        Throwable cause = exception.getCause();
        boolean found = false;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                found = true;
                break;
            }
            cause = cause.getCause();
        }
        assertTrue(found,  "Wrong CommissionRate format");
    }

    @Test
    @DisplayName("agentsBySpecialization Method---Ok")
    void agentsBySpecialization_Ok() {
        List<Agent> agentList = agentService.agentsBySpecialization("Sales");

        assertEquals(3, agentList.size());
        assertEquals("Domingo", agentList.get(1).getFirstName());
    }
}