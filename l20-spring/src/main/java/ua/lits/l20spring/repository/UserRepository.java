package ua.lits.l20spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lits.l20spring.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<Collection<User>> findByLastName(String lastName);

    Optional<User> findByEmail(String email);

    Optional<Collection<User>> findByEmailEndingWith(String emailDomain);

    Optional<Collection<User>> findByAgeGreaterThanEqual(Integer age);
}
