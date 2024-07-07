package com.orange.hw4.service;

import com.orange.hw4.validation.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private final UserValidator userValidator = new UserValidator();

    @Test
    void testValidateTeamValidTeams() {
        assertDoesNotThrow(() -> userValidator.validateTeam("NOTEAM"));
        assertDoesNotThrow(() -> userValidator.validateTeam("PINK"));

    }

    @Test
    void testValidateTeamInvalidTeam() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userValidator.validateTeam("INVALID"));
        assertEquals("Wrong team", exception.getMessage());
    }
}
