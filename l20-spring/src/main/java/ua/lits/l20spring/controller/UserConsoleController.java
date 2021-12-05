package ua.lits.l20spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.lits.l20spring.service.ResultDumperService;
import ua.lits.l20spring.service.UserService;

@Component
public class UserConsoleController {

    private static final Logger logger = LoggerFactory.getLogger(UserConsoleController.class);

    private final UserService userService;

    private final ResultDumperService dumperService;

    public UserConsoleController(UserService userService, ResultDumperService dumperService) {
        this.userService = userService;
        this.dumperService = dumperService;
    }

    public void findAll() {
        dumperService.dump(userService.findAll(), "displaying all entities");
    }

    public void findById(Long id) {
        try {
            dumperService.dump(userService.findById(id), "id", id);
        } catch (Exception e) {
            dumperService.error(e.getMessage());
        }
    }

    public void findByLastName(String lastName) {
        try {
            dumperService.dump(userService.findByLastName(lastName), "lastName", lastName);
        } catch (Exception e) {
            dumperService.error(e.getMessage());
        }
    }

    public void findByEmail(String email) {
        try {
            dumperService.dump(userService.findByEmail(email), "email", email);
        } catch (Exception e) {
            dumperService.error(e.getMessage());
        }
    }

    public void findByAgeGreaterThanEqual(Integer age) {
        try {
            dumperService.dump(
                    userService.findByAgeGreaterThanEqual(age),
                    "users whose age older than " + age
            );
        } catch (Exception e) {
            dumperService.error(e.getMessage());
        }
    }

    public void findByEmailEndingWith(String emailDomain) {
        try {
            dumperService.dump(
                    userService.findByEmailEndingWith(emailDomain),
                    "users whose email ending with '" + emailDomain +"'"
            );
        } catch (Exception e) {
            dumperService.error(e.getMessage());
        }
    }
}
