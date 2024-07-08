package com.orange.hw4.util;

import model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserUtils {

    public static Map<String, List<User>> getUsersByTeam(List<User> users) {

        return users.stream().collect(Collectors.groupingBy(user -> {
            if ("orange".equalsIgnoreCase(user.getTeam())) {
                return "orangeTeam";
            } else if ("pink".equalsIgnoreCase(user.getTeam())) {
                return "pinkTeam";
            } else {
                return "noTeam";
            }
        }));
    }

    public static int getAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}