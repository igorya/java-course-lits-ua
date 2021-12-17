package ua.lits.l20spring.service;

import ua.lits.l20spring.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.lits.l20spring.exception.UserNotFoundException;
import ua.lits.l20spring.helper.ResourceHelper;
import ua.lits.l20spring.model.User;
import ua.lits.l20spring.repository.UserRepository;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Validator validator) {
        super(modelMapper, validator);
        this.userRepository = userRepository;
    }

    @Override
    public Collection<UserDTO> findAll() {
        return transformCollection(
                (Collection<User>)userRepository.findAll(),
                UserDTO.class,
                () -> new UserNotFoundException("Collection is empty")
        );
    }

    @Override
    public UserDTO findById(Long id) {
        return modelMapper.map(
                userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id)),
                UserDTO.class
        );
    }

    @Override
    public Collection<UserDTO> findByLastName(String lastName) {
        return transformCollection(
                userRepository.findByLastName(lastName).orElseThrow(),
                UserDTO.class,
                () -> new UserNotFoundException("lastName", lastName)
        );
    }

    @Override
    public UserDTO findByEmail(String email) {
        return modelMapper.map(
                userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email", email)),
                UserDTO.class
        );
    }

    @Override
    public Collection<UserDTO> findByEmailEndingWith(String emailDomain) {
        return transformCollection(
                userRepository.findByEmailEndingWith(emailDomain).orElseThrow(),
                UserDTO.class,
                () -> new UserNotFoundException("emailDomain", emailDomain)
        );
    }

    @Override
    public Collection<UserDTO> findByAgeGreaterThanEqual(Integer age) {
        return transformCollection(
                userRepository.findByAgeGreaterThanEqual(age).orElseThrow(),
                UserDTO.class,
                () -> new UserNotFoundException(String.format("User older than %d not found", age))
        );
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return modelMapper.map(
                userRepository.save(modelMapper.map(userDTO, User.class)),
                UserDTO.class
        );
    }

    @Override
    public void initDatabase(Resource resource) {
        final int FIRST_NAME = 0;
        final int LAST_NAME = 1;
        final int AGE = 2;
        final int SALARY = 3;
        final int ADDRESS = 4;
        final int EMAIL = 5;

        long recordCounter;
        if ((recordCounter = userRepository.count()) > 0) {
            logger.info("Table contains {} records. Skip inserting", recordCounter);
            logger.info("");
            return;
        }

        Collection<User> userResult = null;
        try {
            List<User> userList = new ArrayList<>();
            String[] row;
            UserDTO userDTO;

            for (String str : ResourceHelper.readAsList(resource)) {
                row = str.split("\t", 6);
                userDTO = UserDTO.builder()
                        .firstName(row[FIRST_NAME])
                        .lastName(row[LAST_NAME])
                        .age(Integer.valueOf(row[AGE]))
                        .salary(BigDecimal.valueOf(Double.parseDouble(row[SALARY])))
                        .address(row[ADDRESS])
                        .email(row[EMAIL])
                        .build();
                validate(userDTO);
                userList.add(modelMapper.map(userDTO, User.class));
            }
            userResult = (List<User>)userRepository.saveAll(userList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("");
        }

        if (userResult != null) {
            logger.info("Following entities have been inserted into the User table:");
            userResult.forEach(user -> logger.info(user.toString()));
            logger.info("Amount of records is "+ userResult.size());
            logger.info("");
        }
    }

}

