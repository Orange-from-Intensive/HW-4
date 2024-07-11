package com.orange.hw4.repository;

import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {
    void addUser(String name, String surName, LocalDate birthDate, String team);
    void updateUser(String name, String surName, LocalDate birthDate, Long id, String team);
    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserbyId(Long id);
}
