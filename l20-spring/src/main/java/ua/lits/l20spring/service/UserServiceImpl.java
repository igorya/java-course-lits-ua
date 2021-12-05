package ua.lits.l20spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.lits.l20spring.UserNotFoundException;
import ua.lits.l20spring.helper.ResourceHelper;
import ua.lits.l20spring.model.User;
import ua.lits.l20spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id));
    }

    @Override
    public Collection<User> findByLastName(String lastName) {
        var result =  userRepository.findByLastName(lastName).orElseThrow();
        if (result.size() == 0) {
            throw new UserNotFoundException("lastName", lastName);
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public Collection<User> findByEmailEndingWith(String emailDomain) {
        var result = userRepository.findByEmailEndingWith(emailDomain).orElseThrow();
        if (result.size() == 0) {
            throw new UserNotFoundException("emailDomain", emailDomain);
        }
        return result;
    }

    @Override
    public Collection<User> findByAgeGreaterThanEqual(Integer age) {
        var result = userRepository.findByAgeGreaterThanEqual(age).orElseThrow();
        if (result.size() == 0) {
            throw new UserNotFoundException(String.format("User older than %d not found", age));
        }
        return result;
    }

    @Override
    public void initDatabase(Resource resource) {
        long recordCounter;
        if ((recordCounter = userRepository.count()) > 0) {
            logger.info("Table contains {} records. Skip inserting", recordCounter);
            logger.info("");
            return;
        }

        Iterable<User> userResult = null;
        try {
            List<User> userList = new ArrayList<>();
            String[] row;

            for (String str : ResourceHelper.readAsList(resource)) {
                row = str.split("\t", 5);
                userList.add(
                        User.builder()
                                .firstName(row[0])
                                .lastName(row[1])
                                .age(Integer.valueOf(row[2]))
                                .address(row[3])
                                .email(row[4])
                                .build()
                );
                userResult = userRepository.saveAll(userList);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("");
        }

        if (userResult != null) {
            logger.info("Following entities have been inserted into the User table:");
            userResult.forEach(user -> logger.info(user.toString()));
            logger.info("");
        }
    }

}

