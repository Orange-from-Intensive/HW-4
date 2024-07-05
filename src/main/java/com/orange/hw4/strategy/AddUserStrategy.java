package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class AddUserStrategy implements UserActionStrategy {

    private final UserService userService;

    public AddUserStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        try {
            userService.addUser(name, surname, dateOfBirth);
            resp.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            log.warn("Error adding user.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding user.");
        }
    }
}
