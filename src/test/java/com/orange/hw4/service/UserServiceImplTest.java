package com.orange.hw4.service;

import com.orange.hw4.repository.UserRepository;
import com.orange.hw4.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUserWithValidTeam() throws Exception {
        doNothing().when(userValidator).validateTeam(anyString());
        doNothing().when(userRepository).addUser(anyString(), anyString(), any(LocalDate.class), anyString());

        userService.addUser("John", "Doe", LocalDate.of(1990, 5, 15), "PINK");

        verify(userValidator, times(1)).validateTeam("PINK");
        verify(userRepository, times(1)).updateUser("John", "Doe", LocalDate.of(1990, 5, 15), 1L, "PINK");


    }

    @Test
    void testGetAllUsers() {

    }

}
