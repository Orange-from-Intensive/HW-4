package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class UpdateUserStrategy implements UserActionStrategy {
    private final UserService userService;

    public UpdateUserStrategy(UserService userService) { this.userService = userService; }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthDate = req.getParameter("birthDate");
        String idString = req.getParameter("id");
        String team = req.getParameter("team");
        try {
            long id;
            id = Long.parseLong(idString);
            userService.updateUser(name, surname, LocalDate.parse(birthDate), id, team);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (NumberFormatException e) {
            log.warn("Malformed id: {}", idString);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Malformed id.");
        }
    }
}
