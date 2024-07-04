package com.orange.hw4.service;

import com.orange.hw4.repository.UserRepository;
import model.User;

import java.time.LocalDate;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String name, String surname, LocalDate birthDate) {
        userRepository.addUser(name, surname, birthDate);
    }

    @Override
    public void updateUser(String name, String surname, LocalDate birthDate, Long id) {
        userRepository.updateUser(name, surname,birthDate, id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserbyId(id);
    }
}
