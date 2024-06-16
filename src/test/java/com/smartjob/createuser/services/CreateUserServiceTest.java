package com.smartjob.createuser.services;

import com.smartjob.createuser.dtos.request.PhoneDto;
import com.smartjob.createuser.dtos.request.UserDto;
import com.smartjob.createuser.dtos.response.CreateUserResponse;
import com.smartjob.createuser.exceptions.CreateUserHttpException;
import com.smartjob.createuser.repository.UserRepository;
import com.smartjob.createuser.repository.model.Phone;
import com.smartjob.createuser.repository.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserService createUserService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    public void setUp() {
        PhoneDto phoneDto = PhoneDto.builder()
                .number("12345")
                .build();

        List<PhoneDto> phoneDtos = new ArrayList<>();
        phoneDtos.add(phoneDto);

        userDto = UserDto.builder()
                .email("test@example.com")
                .name("Test User")
                .password("password")
                .phones(phoneDtos)
                .build();

        Phone phone = Phone.builder()
                .number("12345")
                .build();
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user = User.builder()
                .email("test@example.com")
                .name("Test User")
                .password("password")
                .created(new Timestamp(System.currentTimeMillis()))
                .modified(new Timestamp(System.currentTimeMillis()))
                .lastLogin(new Timestamp(System.currentTimeMillis()))
                .phones(phones)
                .build();
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        userDto = UserDto.builder()
                .email("test..example.com")
                .build();
        CreateUserHttpException exception = assertThrows(CreateUserHttpException.class, () -> {
            createUserService.registerUser(userDto);
        });
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);
        CreateUserHttpException exception = assertThrows(CreateUserHttpException.class, () -> {
            createUserService.registerUser(userDto);
        });
        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    public void testRegisterUser_Exception() {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        CreateUserHttpException exception = assertThrows(CreateUserHttpException.class, () -> {
            createUserService.registerUser(userDto);
        });
        assertEquals("User saved is null", exception.getMessage());
    }

    @Test
    public void testRegisterUser_Success() throws CreateUserHttpException {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        CreateUserResponse response = createUserService.registerUser(userDto);

        assertNotNull(response);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUsers_Success() throws CreateUserHttpException {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        CreateUserResponse actualResponse = createUserService.getUsers();
        assertNotNull(actualResponse);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUsers_Exception() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        CreateUserHttpException exception = assertThrows(CreateUserHttpException.class, () -> {
            createUserService.getUsers();
        });
        assertEquals("Database error", exception.getMessage());
        verify(userRepository, times(1)).findAll();
    }
}
