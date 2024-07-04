package com.orange.hw4.service;

import model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    public void addUser(String name, String surname, LocalDate birthDate);
    public void updateUser(String name, String surname, LocalDate birthDate, Long id);
    public void deleteUser(Long id);
    public List<User> getAllUsers();
    public User getUserById(Long id);
}
