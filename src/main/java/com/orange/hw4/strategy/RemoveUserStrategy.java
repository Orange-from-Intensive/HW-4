package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

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
            UUID id = UUID.fromString(idString);
            userService.deleteUser(id);
        } catch (Exception e) {
            log.warn("Error removing user.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error removing user.");
        }
    }
}
