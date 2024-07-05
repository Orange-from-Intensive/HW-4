package com.orange.hw4.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public abstract class UserRepositoryTemplateMethod {

    public void startTransaction(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

    public void endTransaction(Connection connection) throws SQLException {

        connection.commit();
        connection.setAutoCommit(true);
        connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
    }

}
