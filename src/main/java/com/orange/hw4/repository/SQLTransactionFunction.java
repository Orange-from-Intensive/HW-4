package com.orange.hw4.repository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
interface SQLTransactionFunction {
    void apply(Connection connection) throws SQLException;
}