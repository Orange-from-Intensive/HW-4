package com.orange.hw4.repository;

import model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {
    void addUser(String name, String surName, LocalDate birthDate);
    void updateUser(String name, String surName, LocalDate birthDate, Long id);
    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserbyId(Long id);
}
