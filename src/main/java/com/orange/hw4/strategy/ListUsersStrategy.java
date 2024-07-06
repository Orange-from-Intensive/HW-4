package com.orange.hw4.strategy;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ListUsersStrategy implements UserActionStrategy {

    private final UserService userService;

    public ListUsersStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<User> userList = userService.getAllUsers();

            List<User> orangeTeam = userList.stream()
                    .filter(user -> "orange".equalsIgnoreCase(user.getTeam()))
                    .collect(Collectors.toList());

            List<User> pinkTeam = userList.stream()
                    .filter(user -> "pink".equalsIgnoreCase(user.getTeam()))
                    .collect(Collectors.toList());

            List<User> noTeam = userList.stream()
                    .filter(user -> "noteam".equalsIgnoreCase(user.getTeam()))
                    .collect(Collectors.toList());

            req.setAttribute("pinkTeam", userList);
            req.setAttribute("orangeTeam", userList);
            req.setAttribute("noTeam", userList);

            req.setAttribute("userList", userList);

            req.getRequestDispatcher("/userlist.jsp").forward(req, resp);

        } catch (Exception e) {
            log.warn("Error during receiving list of users.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during receiving list of users.");
        }
    }
}
