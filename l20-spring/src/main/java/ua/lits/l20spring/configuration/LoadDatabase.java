package ua.lits.l20spring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ua.lits.l20spring.service.UserService;

@Configuration
public class LoadDatabase {

    /**
     * This plain text file contains user table rows, the columns of which are separated by tab
     */
    @Value("classpath:user-table-data.txt")
    private Resource userResource;

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> userService.initDatabase(userResource);
    }
}
