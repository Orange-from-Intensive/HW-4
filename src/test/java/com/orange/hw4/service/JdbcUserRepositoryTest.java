package com.orange.hw4.service;

import com.orange.hw4.repository.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
