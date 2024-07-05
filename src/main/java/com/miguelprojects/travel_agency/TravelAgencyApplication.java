package com.miguelprojects.travel_agency;

import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.Models.Role;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Service.UserService;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.AgentService;
import com.miguelprojects.travel_agency.Service.ManagerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    // To centralize configuration and allow to use it through dependency injection in our application
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // To have some data to start with, it's executed automatically
    @Bean
    CommandLineRunner run(UserService userService, CustomerService customerService, AgentService agentService) {
        return args -> {
            Role role1 = userService.saveRole(new Role(null, "ROLE_CUSTOMER"));
            Role role2 = userService.saveRole(new Role(null, "ROLE_AGENT"));
            Role role3 = userService.saveRole(new Role(null, "ROLE_MANAGER"));

            userService.saveUser(new User(null, "Pedro Lopez", "pedro", "1234", new ArrayList<>(), null, null, null));
            userService.saveUser(new User(null, "Lucia Marin", "lucia", "1234", new ArrayList<>(), null, null, null));
            userService.saveUser(new User(null, "Marta Polo", "marta", "1234", new ArrayList<>(), null, null, null));
            userService.saveUser(new User(null, "Lucas Patiño", "lucas", "1234", new ArrayList<>(), null, null, null));
            userService.saveUser(new User(null, "Sofia Genova", "sofia", "1234", new ArrayList<>(), null, null, null));
            userService.saveUser(new User(null, "Antonio Casas", "antonio", "1234", new ArrayList<>(), null, null, null));

            userService.addRoleToUser("pedro", "ROLE_CUSTOMER");
            userService.addRoleToUser("lucia", "ROLE_CUSTOMER");
            userService.addRoleToUser("marta", "ROLE_AGENT");
            userService.addRoleToUser("lucas", "ROLE_AGENT");
            userService.addRoleToUser("sofia", "ROLE_MANAGER");
            userService.addRoleToUser("antonio", "ROLE_MANAGER");

        };
    }
}
