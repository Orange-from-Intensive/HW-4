package com.orange.hw4.repository;

import lombok.extern.slf4j.Slf4j;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcUserRepository extends JdbcUserRepositoryTemplateMethod implements UserRepository {
    private static final String ADD_USER = "INSERT INTO users(name, surname, age) VALUES (?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET name=?, surname=?, age=? WHERE id=? ";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(String name, String surName, LocalDate birthDate) {

        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            startTransaction(connection);

            statement.setString(1, name);
            statement.setString(2, surName);
            statement.setDate(3, Date.valueOf(birthDate));
            statement.execute();

            endTransaction(connection);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error trying to rollback transaction", ex);
            }
            log.error("Record not added to db. Name[{}], Surname[{}], Date[{}]. SQL exception{}", name, surName, birthDate, e);
        }
    }

    @Override
    public void updateUser(String name, String surName, LocalDate birthDate, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            startTransaction(connection);

            statement.setString(1, name);
            statement.setString(2, surName);
            statement.setDate(3, Date.valueOf(birthDate));
            statement.setLong(4, id);
            statement.execute();

            endTransaction(connection);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error trying to rollback transaction", ex);
            }
            log.error("Record was not updated. Id[{}].  SQL exception[{}]", id, e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            startTransaction(connection);

            statement.setLong(1, id);
            statement.executeUpdate();

            endTransaction(connection);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error trying to rollback transaction", ex);
            }
            log.error("Record not removed from db. Id[{}].  SQL exception[{}]", id, e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            startTransaction(connection);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                LocalDate birthDate = resultSet.getDate("age").toLocalDate();
                Long id = resultSet.getLong("id");
                User user = new User(id, name, surname, birthDate);
                users.add(user);
            }

            endTransaction(connection);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error trying to rollback transaction", ex);
            }
            log.error("Records not retrieved from db. SQL exception[{}]", e);
        }
        return users;
    }

    @Override
    public User getUserbyId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            startTransaction(connection);

            if (!resultSet.next()) {
                log.error("Record not found. Id[{}]", id);
                return null;
            }
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = resultSet.getDate("age").toLocalDate();
            Long userId = resultSet.getLong("id");

            endTransaction(connection);

            return new User(userId, name, surname, birthDate);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Error trying to rollback transaction", ex);
            }
            log.error("Record not retrieved from db. SQL exception[{}]", id, e);
        }
        return null;
    }
}
