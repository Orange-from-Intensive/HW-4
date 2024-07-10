package com.orange.hw4.service;

import com.orange.hw4.model.User;
import com.orange.hw4.repository.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
public class JdbcUserRepositoryTest {
    private Connection connection;
    private JdbcUserRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        repository = new JdbcUserRepository(connection);
    }

    @Test
    public void testAddUser() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);

        String name = "John";
        String surName = "Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String team = "Team A";

        repository.addUser(name, surName, birthDate, team);

        verify(statement).setString(1, name);
        verify(statement).setString(2, surName);
        verify(statement).setDate(3, Date.valueOf(birthDate));
        verify(statement).setString(4, team);
        verify(statement).executeUpdate();
    }

    @Test
    public void testUpdateUser() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);

        String name = "John";
        String surName = "Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Long id = 1L;
        String team = "Team A";

        repository.updateUser(name, surName, birthDate, id, team);

        verify(statement).setString(1, name);
        verify(statement).setString(2, surName);
        verify(statement).setDate(3, Date.valueOf(birthDate));
        verify(statement).setString(4, team);
        verify(statement).setLong(5, id);
        verify(statement).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);

        Long id = 1L;
        repository.deleteUser(id);

        verify(statement).setLong(1, id);
        verify(statement).executeUpdate();
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn("John");
        when(resultSet.getString("surname")).thenReturn("Doe");
        when(resultSet.getDate("age")).thenReturn(Date.valueOf(LocalDate.of(1990, 1, 1)));
        when(resultSet.getString("team")).thenReturn("Team A");
        when(resultSet.getLong("id")).thenReturn(1L);

        List<User> users = repository.getAllUsers();

        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals(LocalDate.of(1990, 1, 1), user.getBirthDate());
        assertEquals("Team A", user.getTeam());
    }

    @Test
    public void testGetUserById() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("name")).thenReturn("John");
        when(resultSet.getString("surname")).thenReturn("Doe");
        when(resultSet.getDate("age")).thenReturn(Date.valueOf(LocalDate.of(1990, 1, 1)));
        when(resultSet.getString("team")).thenReturn("Team A");
        when(resultSet.getLong("id")).thenReturn(1L);

    }
}
