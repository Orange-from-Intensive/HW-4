package com.orange.hw4.service;

import com.orange.hw4.repository.UserRepository;
import com.orange.hw4.validation.UserValidator;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        verify(userRepository, times(1)).addUser("John", "Doe", LocalDate.of(1990, 5, 15), "PINK");
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L, "John", "Doe", LocalDate.of(1990, 5, 15), "PINK");
        User user2 = new User(2L, "Jane", "Doe", LocalDate.of(1992, 8, 25), "ORANGE");
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        User user = new User(1L, "John", "Doe", LocalDate.of(1990, 5, 15), "PINK");
        when(userRepository.getUserbyId(anyLong())).thenReturn(user);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getName());
        verify(userRepository, times(1)).getUserbyId(1L);
    }

}
