package com.orange.hw4.repository;

import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
    void addUser(String name, String surName, LocalDate birthDate, String team);
    void updateUser(String name, String surName, LocalDate birthDate, Long id, String team);
    void deleteUser(UUID id);

    List<User> getAllUsers();

    User getUserbyId(UUID id);
}
