package com.orange.hw4.repository;

import lombok.extern.slf4j.Slf4j;
import com.orange.hw4.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@FunctionalInterface
interface SQLTransactionFunction {
    void apply(Connection connection) throws SQLException;
}

@Slf4j
public class JdbcUserRepository implements UserRepository {
    private static final String ADD_USER = "INSERT INTO users(name, surname, age, team) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET name=?, surname=?, age=?, team=? WHERE id=? ";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    private void executeWithTransaction(int level, SQLTransactionFunction operation) throws SQLException {
        connection.setAutoCommit(false);
        int transactionIsolation = connection.getTransactionIsolation();
        connection.setTransactionIsolation(level);
        try {
            operation.apply(connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setTransactionIsolation(transactionIsolation);
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void addUser(String name, String surName, LocalDate birthDate, String team) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, name);
            statement.setString(2, surName);
            statement.setDate(3, Date.valueOf(birthDate));
            statement.setString(4, team);
            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn -> {
                statement.executeUpdate();
            });
        } catch (SQLException e) {
            log.error("Record not added to db. "+ e.getMessage());
        }
    }

    @Override
    public void updateUser(String name, String surName, LocalDate birthDate, Long id, String team) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, name);
            statement.setString(2, surName);
            statement.setDate(3, Date.valueOf(birthDate));
            statement.setString(4, team);
            statement.setLong(5, id);
            executeWithTransaction(Connection.TRANSACTION_SERIALIZABLE, conn -> {
                statement.executeUpdate();
            });
        } catch (SQLException e) {
            log.error("Record was not updated" + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            executeWithTransaction(Connection.TRANSACTION_READ_UNCOMMITTED, conn -> {
                statement.executeUpdate();
            });
        } catch (SQLException e) {
            log.error("Record was not removed" + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn -> {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    LocalDate birthDate = resultSet.getDate("age").toLocalDate();
                    String team = resultSet.getString("team");
                    UUID id = resultSet.getObject("id", UUID.class);
                    User user = new User(id, name, surname, birthDate, team);
                    users.add(user);
                }
            });
        } catch (SQLException e) {
            log.error("Records were not retrieved from db{}", e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserbyId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setLong(1, id);
            AtomicReference<User> user = new AtomicReference<>(null);
            executeWithTransaction(Connection.TRANSACTION_SERIALIZABLE, conn -> {
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    log.error("Record not found. Id[{}]", id);
                    throw new SQLException("Record not found. Id[" + id + "]");
                }
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                LocalDate birthDate = resultSet.getDate("age").toLocalDate();
                String team = resultSet.getString("team");
                UUID userId = resultSet.getObject("id", UUID.class);
                user.set(new User(userId, name, surname, birthDate, team));
            });
            return user.get();
        } catch (SQLException e) {
            log.error("Record was not retrieved from db" + e.getMessage());
        }
        return null;
    }
}