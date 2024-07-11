package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import com.orange.hw4.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class EditUserStrategy implements UserActionStrategy {

    private final UserService userService;

    public EditUserStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idString = req.getParameter("id");
        try {
            UUID id = UUID.fromString(idString);
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/useredit.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            log.warn("Malformed id: {}", idString);
            req.setAttribute("error", "Invalid user ID format.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
