package com.orange.hw4.listener;

import com.orange.hw4.repository.JdbcUserRepository;
import com.orange.hw4.repository.UserRepository;
import com.orange.hw4.service.UserService;
import com.orange.hw4.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
@Slf4j
public class InitializationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final String dbDriver = "org.postgresql.Driver";
        final String username = sce.getServletContext().getInitParameter("db_user");
        final String password = sce.getServletContext().getInitParameter("db_password");
        final String dbUrl = sce.getServletContext().getInitParameter("db_url");

        try {
            Class.forName(dbDriver);
            final Connection connection = DriverManager.getConnection(dbUrl, username, password);

            UserRepository userRepository = new JdbcUserRepository(connection);
            UserService userService = new UserServiceImpl(userRepository);
            sce.getServletContext().setAttribute("userService", userService);
        } catch (Exception e) {
            log.error("Initialization error.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            final Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
