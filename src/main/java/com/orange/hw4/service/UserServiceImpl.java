package com.orange.hw4.service;

import com.orange.hw4.repository.UserRepository;
import com.orange.hw4.validation.UserValidator;
import model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public void addUser(String name, String surname, LocalDate birthDate, String team) throws Exception {
        userValidator.validateName(name);
        userValidator.validateName(surname);
        userValidator.validateAge(birthDate);

        if (Objects.nonNull(team)) {
            userValidator.validateTeam(team);
        } else {
            team = "NOTEAM";
        }

        userRepository.addUser(name, surname, birthDate, team);
    }

    @Override
    public void updateUser(String name, String surname, LocalDate birthDate, Long id, String team) throws IllegalArgumentException {
        userValidator.validateTeam(team);
        userValidator.validateName(name);
        userValidator.validateName(surname);
        userValidator.validateAge(birthDate);

        userRepository.updateUser(name, surname,birthDate, id, team);
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
