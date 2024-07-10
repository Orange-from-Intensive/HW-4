package com.orange.hw4.service;

import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserService {
    void addUser(String name, String surname, LocalDate birthDate, String team) throws Exception;
    void updateUser(String name, String surname, LocalDate birthDate, Long id, String team) throws IllegalArgumentException;
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
    Map<Integer, List<Integer>> getUsersByOpponents();
}
