package com.orange.hw4.service;

import com.orange.hw4.repository.JdbcJournalRepository;
import org.junit.jupiter.api.BeforeEach;


import java.sql.Connection;

import java.sql.SQLException;



import static org.mockito.Mockito.*;
public class JdbcJournalRepositoryTest {
    private Connection connection;
    private JdbcJournalRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        repository = new JdbcJournalRepository(connection);
    }
}
