package com.orange.hw4.strategy;

import com.orange.hw4.exception.InvalidAgeException;
import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Slf4j
public class UpdateUserStrategy implements UserActionStrategy {
    private final UserService userService;

    public UpdateUserStrategy(UserService userService) { this.userService = userService; }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
            req.setAttribute("errorMessage", "Error updating user " + e.getMessage());
            String errorMessage = URLEncoder.encode("Error updating user: Invalid user ID.", StandardCharsets.UTF_8);
            resp.sendRedirect(req.getContextPath() + "/user?action=edit&id=" + idString + "&errorMessage=" + errorMessage);
        } catch (IllegalArgumentException | InvalidAgeException e) {
            log.warn("Malformed form");
            req.setAttribute("errorMessage", "Error updating user " + e.getMessage());
            String errorMessage = URLEncoder.encode("Error updating user: " + e.getMessage(), StandardCharsets.UTF_8);
            resp.sendRedirect(req.getContextPath() + "/user?action=edit&id=" + idString + "&errorMessage=" + errorMessage);
        }
    }
}
