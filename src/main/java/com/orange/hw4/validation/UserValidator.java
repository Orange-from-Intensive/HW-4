package com.orange.hw4.validation;

import java.util.Arrays;

public class UserValidator {

    public enum TeamType {
        NOTEAM, PINK, ORANGE
    }

    public void validateTeam(String team) throws IllegalArgumentException {
        if (Arrays.stream(TeamType.values()).anyMatch(teamType -> team.equalsIgnoreCase(teamType.toString()))) {
            return;
        }
        throw new IllegalArgumentException("Wrong team");
    }
}

