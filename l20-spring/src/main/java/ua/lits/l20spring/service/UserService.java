package ua.lits.l20spring.service;

import org.springframework.core.io.Resource;
import ua.lits.l20spring.model.User;

import java.util.Collection;

public interface UserService {

    Iterable<User> findAll();

    User findById(Long id);

    Collection<User> findByLastName(String lastName);

    User findByEmail(String email);

    Collection<User> findByEmailEndingWith(String emailDomain);

    Collection<User> findByAgeGreaterThanEqual(Integer age);

    void initDatabase(Resource resource);
}
