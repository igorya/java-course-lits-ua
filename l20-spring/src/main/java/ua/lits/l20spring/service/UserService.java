package ua.lits.l20spring.service;

import ua.lits.l20spring.dto.UserDTO;
import org.springframework.core.io.Resource;

import java.util.Collection;

public interface UserService {

    Collection<UserDTO> findAll();

    UserDTO findById(Long id);

    Collection<UserDTO> findByLastName(String lastName);

    UserDTO findByEmail(String email);

    Collection<UserDTO> findByEmailEndingWith(String emailDomain);

    Collection<UserDTO> findByAgeGreaterThanEqual(Integer age);

    UserDTO createUser(UserDTO userDTO);

    void initDatabase(Resource resource);
}
