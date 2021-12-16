package ua.lits.l20spring.controller;

import ua.lits.l20spring.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lits.l20spring.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    @GetMapping
    public Collection<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/byEmail/{email}")
    public UserDTO findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/byEmailEnding/{emailDomain}")
    public Collection<UserDTO> findByEmailEndingWith(@PathVariable String emailDomain) {
        return userService.findByEmailEndingWith(emailDomain);
    }

    @GetMapping("/byLastName/{lastName}")
    public Collection<UserDTO> findByLastName(@PathVariable String lastName) {
        return userService.findByLastName(lastName);
    }

    @GetMapping("/ageGreater/{age}")
    public Collection<UserDTO> findByAgeGreaterThanEqual(@PathVariable Integer age) {
        return userService.findByAgeGreaterThanEqual(age);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
