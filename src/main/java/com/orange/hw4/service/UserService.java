package com.orange.hw4.service;

import model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void addUser(String name, String surname, LocalDate birthDate);
    void updateUser(String name, String surname, LocalDate birthDate, Long id);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
}
