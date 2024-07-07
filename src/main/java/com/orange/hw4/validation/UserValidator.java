package com.orange.hw4.validation;

import com.orange.hw4.exception.InvalidAgeException;
import com.orange.hw4.util.UserUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void validateAge(LocalDate birthDate) throws InvalidAgeException {
        int age = UserUtils.getAge(birthDate);
        if (age < 100) {
            if (age >= 18) {
                return;
            }
            throw new InvalidAgeException("Too young");
        }
        throw new InvalidAgeException("You can not be so old!");
    }

    public void validateName(String name) {
        String regex = "^[A-Za-z]{5,100}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            return;
        } else {
            throw new IllegalArgumentException("Wrong characters");
        }
    }
}

