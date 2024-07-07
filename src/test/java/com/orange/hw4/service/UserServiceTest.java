package com.orange.hw4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserService userService = mock(UserServiceImpl.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddUser() throws Exception {
        userService.addUser("John", "Doe", LocalDate.of(1990, 5, 15), "TeamA");

        verify(userService, times(1)).addUser("John", "Doe", LocalDate.of(1990, 5, 15), "TeamA");
    }
}