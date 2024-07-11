package com.orange.hw4.repository;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class JdbcJournalRepository implements JournalRepository {

    private static final String ADD_PAIRS = "INSERT INTO marks(lesson_date, user_one_id, user_two_id) VALUES (?,?,?)";
    private static final String ADD_MARKS_TO_PAIRS = "UPDATE marks SET user_one_mark=?, user_two_mark=? WHERE id=?";
    private static final String DELETE_PAIRS = "DELETE FROM marks WHERE id=?";
    private static final String GET_ALL_JOURNALS = "SELECT * FROM marks";
    private static final String GET_USER_OPPONENTS = "SELECT user_one_id, user_two_id,COUNT(*) AS count FROM marks GROUP BY user_one_id, user_two_id";
    private static final String GET_JOURNAL_BY_ID = "SELECT * FROM marks WHERE id=?";
    private final Connection connection;

    public JdbcJournalRepository(Connection connection){
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
    public void addPairs(User user1, User user2, LocalDate date) {
        try(PreparedStatement statement = connection.prepareStatement(ADD_PAIRS)){
            statement.setDate(1, Date.valueOf(date));
            statement.setLong(2,user1.getId());
            statement.setLong(3,user2.getId());

            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn ->{
                statement.executeUpdate();
            });
        }catch (SQLException e){
            log.error("Record was not add to DB", e);
        }
    }

    @Override
    public void removePairs(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PAIRS)) {
            statement.setLong(1, id);

            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn -> statement.executeUpdate());
        } catch (SQLException e) {
            log.error("Record was not removed from DB", e);
        }
    }

    @Override
    public void editPairs(Long journal, Double mark1, Double mark2) {
        try(PreparedStatement statement = connection.prepareStatement(ADD_MARKS_TO_PAIRS)){
            statement.setDouble(1, mark1);
            statement.setDouble(2, mark2);
            statement.setLong(3,journal);

            executeWithTransaction(Connection.TRANSACTION_SERIALIZABLE, conn -> {
                statement.executeUpdate();
            });
        }catch (SQLException e){
            log.error("Record was not add to DB", e);
        }
    }

    @Override
    public List<Journal> getJournalFeed() {
        List<Journal> journals = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_JOURNALS)){
            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn ->{
                try(ResultSet resultSet = statement.executeQuery()){

                    while (resultSet.next()){
                        LocalDate lessonDate = resultSet.getDate("lesson_date").toLocalDate();
                        Long userOneId = resultSet.getLong("user_one_id");
                        Long userTwoId = resultSet.getLong("user_two_id");
                        Double userOneMark = resultSet.getDouble("user_one_mark");
                        Double userTwoMark = resultSet.getDouble("user_two_mark");
                        Long id = resultSet.getLong("id");
                        Journal journal = new Journal(id,lessonDate, userOneId, userOneMark, userTwoId, userTwoMark);
                        journals.add(journal);
                    }
                }
            });
        } catch (SQLException e) {
            log.error("Error retrieving Journal List", e);
        }
        return journals;
    }

    @Override
    public Journal getJournalById(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_JOURNAL_BY_ID)){
            statement.setLong(1,id);

            AtomicReference<Journal> journal = new AtomicReference<>(null);
            executeWithTransaction(Connection.TRANSACTION_SERIALIZABLE, conn ->{
                try(ResultSet resultSet = statement.executeQuery()){
                    if(!resultSet.next()){
                        log.error("Record not found, id="+id);
                        throw new SQLException("Record not found. Id[" + id + "]");
                    }

                    LocalDate lessonDate = resultSet.getDate("lesson_date").toLocalDate();
                    Long userOneId = resultSet.getLong("user_one_id");
                    Long userTwoId = resultSet.getLong("user_two_id");
                    Double userOneMark = resultSet.getDouble("user_one_mark");
                    Double userTwoMark = resultSet.getDouble("user_two_mark");
                    Long journalId = resultSet.getLong("id");
                    journal.set(new Journal(journalId, lessonDate,userOneId,userOneMark,userTwoId,userTwoMark));
                }
            });

            return journal.get();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Long, Map<Long,Integer>> getAllUserOpponents(User user) {
        Map<Long, Map<Long, Integer>> userMap = new HashMap<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_USER_OPPONENTS)){
            executeWithTransaction(Connection.TRANSACTION_READ_COMMITTED, conn ->{

                try(ResultSet resultSet = statement.executeQuery()){

                    while(resultSet.next()){
                        HashMap<Long, Integer> map = new HashMap<>();

                        Long id1 = resultSet.getLong("user_one_id");
                        Long id2 = resultSet.getLong("user_two_id");
                        Integer count = resultSet.getInt("count");

                        map.put(id2, count);
                        userMap.put(id1, map);
                    }
                }
            });

        }catch (SQLException e){
            log.error("Exception in retrieving allUserOpponents",e);
        }
        return userMap;
    }

    @Override
    public Map<Long, Map<Long, Integer>> getAllUserOpponents() {
        Map<Long, Map<Long, Integer>> userOpponentsMap = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(GET_USER_OPPONENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                long userId = rs.getLong("user_id");
                long opponentId = rs.getLong("opponent_id");
                int meetingCount = rs.getInt("meeting_count");

                userOpponentsMap
                        .computeIfAbsent(userId, k -> new HashMap<>())
                        .put(opponentId, meetingCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userOpponentsMap;
    }

}
