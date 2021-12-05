package ua.lits.l20spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.lits.l20spring.controller.UserConsoleController;

@SpringBootApplication
public class L20SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(L20SpringApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserConsoleController userConsoleController) {
        return args -> {
            userConsoleController.findAll();

            userConsoleController.findById(170L);
            // Displays error
            userConsoleController.findById(999999L);

            userConsoleController.findByLastName("MUsk");
            // Displays error
            userConsoleController.findByLastName("NoName");

            userConsoleController.findByEmail("john3@GMAIL.com");
            // Displays error
            userConsoleController.findByEmail("NoEmail");

            userConsoleController.findByAgeGreaterThanEqual(21);
            // Displays error
            userConsoleController.findByAgeGreaterThanEqual(99);

            userConsoleController.findByEmailEndingWith("gmail.com");
            // Displays error
            userConsoleController.findByEmailEndingWith("nonexistent.com");
        };
    }
}
