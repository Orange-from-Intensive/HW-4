package com.orange.hw4.service;

import com.orange.hw4.repository.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;


import java.sql.Connection;

import java.sql.SQLException;

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
}
