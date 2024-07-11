package com.orange.hw4.service;

import com.orange.hw4.repository.UserRepository;
import com.orange.hw4.validation.UserValidator;
import com.orange.hw4.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    void testAddUserWithNoTeam() throws Exception {
        doNothing().when(userRepository).addUser(anyString(), anyString(), any(LocalDate.class), anyString());

        userService.addUser("Jane", "Doe", LocalDate.of(1992, 8, 25), null);

        verify(userRepository, times(1)).addUser("Jane", "Doe", LocalDate.of(1992, 8, 25), "NOTEAM");
    }

    @Test
    void testUpdateUserWithValidTeam() {
        doNothing().when(userValidator).validateTeam(anyString());
        doNothing().when(userRepository).updateUser(anyString(), anyString(), any(LocalDate.class), anyLong(), anyString());

        userService.updateUser("John", "Doe", LocalDate.of(1990, 5, 15), 1L, "PINK");

        verify(userValidator, times(1)).validateTeam("PINK");
        verify(userRepository, times(1)).updateUser("John", "Doe", LocalDate.of(1990, 5, 15), 1L, "PINK");
    }

    @Test
    void testUpdateUserWithInvalidTeam() {
        doThrow(new IllegalArgumentException("Wrong team")).when(userValidator).validateTeam(anyString());

        assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser("John", "Doe", LocalDate.of(1990, 5, 15), 1L, "INVALID");
        });

        verify(userValidator, times(1)).validateTeam("INVALID");
        verify(userRepository, times(0)).updateUser(anyString(), anyString(), any(LocalDate.class), anyLong(), anyString());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteUser(any());

        userService.deleteUser(new UUID(1,2));

        verify(userRepository, times(1)).deleteUser(new UUID(1,2));
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(new UUID(1,2), "John", "Doe", LocalDate.of(1990, 5, 15), "PINK");
        User user2 = new User(new UUID(3,4), "Jane", "Doe", LocalDate.of(1992, 8, 25), "ORANGE");
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        User user = new User(new UUID(1,2), "John", "Doe", LocalDate.of(1990, 5, 15), "PINK");
        when(userRepository.getUserbyId(any())).thenReturn(user);

        User result = userService.getUserById(new UUID(1,2));

        assertNotNull(result);
        assertEquals(new UUID(1,2), result.getId());
        assertEquals("John", result.getName());
        verify(userRepository, times(1)).getUserbyId(new UUID(1,2));
    }
}
