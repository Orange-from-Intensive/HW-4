package com.orange.hw4.strategy;

import com.orange.hw4.util.UserUtils;
import com.orange.hw4.service.UserService;
import com.orange.hw4.repository.JournalRepository;
import lombok.extern.slf4j.Slf4j;
import com.orange.hw4.model.Opponents;
import com.orange.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class GeneratePairsStrategy implements UserActionStrategy {

    private final UserService userService;
    private final JournalRepository journalRepository;

    public GeneratePairsStrategy(UserService userService, JournalRepository journalRepository) {
        this.userService = userService;
        this.journalRepository = journalRepository;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        try {
            List<User> users = userService.getAllUsers();
            Map<String, List<User>> usersByTeams = UserUtils.getUsersByTeam(users);

            List<User> orangeTeam = usersByTeams.get("orangeTeam");
            List<User> pinkTeam = usersByTeams.get("pinkTeam");

            Map<Long, Map<Long, Integer>> userOpponentsMap = journalRepository.getAllUserOpponents();

            List<Opponents> opponents = generatePairsWithLeastMeetings(orangeTeam, pinkTeam, userOpponentsMap);

            request.setAttribute("opponents", opponents);
            request.getRequestDispatcher("/generatePairs.jsp").forward(request, resp);

        } catch (Exception e) {
            log.warn("Error during generating pairs.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during generating pairs.");
        }
    }

    private List<Opponents> generatePairsWithLeastMeetings(List<User> orangeTeam, List<User> pinkTeam, Map<Long, Map<Long, Integer>> userOpponentsMap) {
        List<Opponents> opponents = new ArrayList<>();
        Set<Long> usedOrangeIds = new HashSet<>();
        Set<Long> usedPinkIds = new HashSet<>();

        for (User orangeUser : orangeTeam) {
            if (usedOrangeIds.contains(orangeUser.getId())) {
                continue;
            }

            Long orangeUserId = orangeUser.getId();
            Map<Long, Integer> opponentsCount = userOpponentsMap.getOrDefault(orangeUserId, new HashMap<>());

            User leastMetPinkUser = pinkTeam.stream()
                    .filter(pinkUser -> !usedPinkIds.contains(pinkUser.getId()))
                    .min(Comparator.comparingInt(pinkUser -> opponentsCount.getOrDefault(pinkUser.getId(), 0)))
                    .orElse(null);

            if (leastMetPinkUser != null) {
                opponents.add(new Opponents(orangeUser, leastMetPinkUser));
                usedOrangeIds.add(orangeUserId);
                usedPinkIds.add(leastMetPinkUser.getId());
            }
        }

        return opponents;
    }
}
