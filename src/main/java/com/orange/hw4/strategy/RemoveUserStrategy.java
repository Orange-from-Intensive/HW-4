package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RemoveUserStrategy implements UserActionStrategy {

    private final UserService userService;

    public RemoveUserStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idString = req.getParameter("id");
        try {
            Long id = Long.parseLong(idString);
            userService.deleteUser(id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            log.warn("Error removing user.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error removing user.");
        }
    }
}
