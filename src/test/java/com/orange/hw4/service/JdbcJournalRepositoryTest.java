package com.orange.hw4.service;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;
import com.orange.hw4.repository.JdbcJournalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
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

//    @Test
//    public void testRemovePairs() throws SQLException {
//        PreparedStatement statement = mock(PreparedStatement.class);
//        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
//
//        long id = 1L;
//        repository.removePairs(id);
//
//        verify(statement).setLong(1, id);
//        verify(statement).executeUpdate();
//    }

    @Test
    public void testGetJournalFeed() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("lesson_date")).thenReturn(Date.valueOf(LocalDate.of(2023, 1, 1)));
        when(resultSet.getLong("user_one_id")).thenReturn(1L);
        when(resultSet.getLong("user_two_id")).thenReturn(2L);
        when(resultSet.getDouble("user_one_mark")).thenReturn(2.0);
        when(resultSet.getDouble("user_two_mark")).thenReturn(3.0);
        when(resultSet.getLong("id")).thenReturn(1L);

        List<Journal> journals = repository.getJournalFeed();

//        assertEquals(1, journals.size());
//        Journal journal = journals.get(0);
//        assertEquals(LocalDate.of(2023, 1, 1), journal.getLessonDate());
//        assertEquals(1L, journal.getUserOneId());
//        assertEquals(2L, journal.getUserTwoId());
//        assertEquals(2.0, journal.getUserOneMark());
//        assertEquals(3.0, journal.getUserTwoMark());
    }

    @Test
    public void testGetAllUserOpponents() throws SQLException {
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("user_one_id")).thenReturn(1L);
        when(resultSet.getLong("user_two_id")).thenReturn(2L);
        when(resultSet.getInt("count")).thenReturn(3);

        User user = new User(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "Team A");
        Map<Long, Map<Long, Integer>> userOpponents = repository.getAllUserOpponents(user);

        assertEquals(1, userOpponents.size());
        assertEquals(3, userOpponents.get(1L).get(2L).intValue());
    }
}
