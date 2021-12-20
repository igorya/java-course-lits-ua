package ua.lits.l20spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import ua.lits.l20spring.dto.UserDTO;
import ua.lits.l20spring.exception.UserNotFoundException;
import ua.lits.l20spring.model.User;
import ua.lits.l20spring.repository.UserRepository;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private final String USER_EMAIL_REAL = "elon@spacex.com";
    private final String USER_EMAIL_NOT_EXIST = "noEmail";
    private final String USER_LAST_NAME_REAL = "Doe";
    private final String USER_LAST_NAME_NOT_EXIST = "noName";

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private ModelMapper modelMapperMock;
    @Mock
    private Validator validatorMock;

    private UserService userService;

    @Before
    public void init() {
        userService = new UserServiceImpl(userRepositoryMock, modelMapperMock, validatorMock);
    }

    @Test
    public void whenUserEmailFoundReturnUser() {
        UserDTO realUserDto = UserDTO.builder().email(USER_EMAIL_REAL).build();
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> realUser = Optional.of(modelMapper.map(realUserDto, User.class));

        when(userRepositoryMock.findByEmail(USER_EMAIL_REAL)).thenReturn(realUser);
        when(modelMapperMock.map(realUser.get(), UserDTO.class)).thenReturn(realUserDto);

        assertEquals(realUserDto, userService.findByEmail(USER_EMAIL_REAL));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenUserEmailNotFoundThrowException() {
        when(userRepositoryMock.findByEmail(USER_EMAIL_NOT_EXIST)).thenReturn(Optional.ofNullable(null));
        userService.findByEmail(USER_EMAIL_NOT_EXIST);
    }

    @Test
    public void whenLastNameFoundReturnCollection() {
        ModelMapper modelMapper = new ModelMapper();

        var userDto1 = UserDTO.builder().firstName("John").lastName("Doe").build();
        var user1 = modelMapper.map(userDto1, User.class);
        var userDto2 = UserDTO.builder().firstName("John3").lastName("Doe").build();
        var user2 = modelMapper.map(userDto2, User.class);
        var userDto3 = UserDTO.builder().firstName("John4").lastName("Doe").build();
        var user3 = modelMapper.map(userDto3, User.class);

        when(userRepositoryMock.findByLastName(USER_LAST_NAME_REAL)).thenReturn(Optional.of(
                Arrays.asList(user1, user2, user3)
        ));

        when(modelMapperMock.map(user1, UserDTO.class)).thenReturn(userDto1);
        when(modelMapperMock.map(user2, UserDTO.class)).thenReturn(userDto2);
        when(modelMapperMock.map(user3, UserDTO.class)).thenReturn(userDto3);

        assertEquals(
                Arrays.asList(userDto1, userDto2, userDto3),
                userService.findByLastName(USER_LAST_NAME_REAL)
        );
        // Or 2nd variant - insensitive to sequence
        assertTrue(
                userService.findByLastName(USER_LAST_NAME_REAL)
                        .containsAll(Arrays.asList(userDto1, userDto2, userDto3))
        );
    }

    @Test(expected = UserNotFoundException.class)
    public void whenLastNameNotFoundThrowException() {
        when(userRepositoryMock.findByLastName(USER_LAST_NAME_NOT_EXIST))
                .thenReturn(Optional.of(new ArrayList<>()));
        userService.findByLastName(USER_LAST_NAME_NOT_EXIST);
    }
}