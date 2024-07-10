package com.orange.hw4.strategy;

import com.orange.hw4.util.UserUtils;
import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import com.orange.hw4.model.Opponents;
import com.orange.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class GeneratePairsStrategy implements UserActionStrategy{

    private final UserService userService;

    public GeneratePairsStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        try {
            List<User> users = userService.getAllUsers();
            Map<String, List<User>> usersByTeams = UserUtils.getUsersByTeam(users);

            List<User> orangeTeam = usersByTeams.get("orangeTeam");
            List<User> pinkTeam = usersByTeams.get("pinkTeam");

            List<Opponents> opponents = generateRandomPairs(orangeTeam, pinkTeam);

            request.setAttribute("opponents", opponents);
            request.getRequestDispatcher("/generatePairs.jsp").forward(request, resp);

        } catch (Exception e) {
            log.warn("Error during generating pairs.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during generating pairs.");
        }
    }

    private List<Opponents> generateRandomPairs(List<User> orangeTeam, List<User> pinkTeam) {
        
    }
}