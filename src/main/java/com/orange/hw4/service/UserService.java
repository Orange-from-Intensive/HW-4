package com.orange.hw4.service;

import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserService {
    void addUser(String name, String surname, LocalDate birthDate, String team) throws Exception;
    void updateUser(String name, String surname, LocalDate birthDate, Long id, String team) throws IllegalArgumentException;
    void deleteUser(UUID id);
    List<User> getAllUsers();
    User getUserById(UUID id);
}
