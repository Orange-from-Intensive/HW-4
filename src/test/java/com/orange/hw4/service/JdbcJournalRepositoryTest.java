package com.orange.hw4.service;

import com.orange.hw4.model.User;
import com.orange.hw4.repository.JdbcJournalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


import static org.mockito.Mockito.*;
public class JdbcJournalRepositoryTest {
    private Connection connection;
    private JdbcJournalRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        repository = new JdbcJournalRepository(connection);
    }

    @Test
    public void testAddPairs() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);

        User user1 = new User(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "Team A");
        User user2 = new User(2L, "Jane", "Doe", LocalDate.of(1992, 2, 2), "Team B");
        LocalDate date = LocalDate.of(2023, 1, 1);

        repository.addPairs(user1, user2, date);

        verify(statement).setDate(1, Date.valueOf(date));
        verify(statement).setLong(2, user1.getId());
        verify(statement).setLong(3, user2.getId());
        verify(statement).executeUpdate();
    }

    @Test
    public void testRemovePairs() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);

        long id = 1L;
        repository.removePairs(id);

    }
}
