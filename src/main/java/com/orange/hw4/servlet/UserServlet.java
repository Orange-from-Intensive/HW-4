package com.orange.hw4.servlet;

import com.orange.hw4.repository.JournalRepository;
import com.orange.hw4.service.JournalService;
import com.orange.hw4.service.UserService;
import com.orange.hw4.strategy.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
@Slf4j
public class UserServlet extends HttpServlet {

    private Map<String, UserActionStrategy> strategyMap;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserService userService = (UserService) config.getServletContext().getAttribute("userService");
        JournalService journalService = (JournalService) config.getServletContext().getAttribute("journalService");
        JournalRepository journalRepository = (JournalRepository) config.getServletContext().getAttribute("journalRepository");

        strategyMap = new HashMap<>();
        strategyMap.put("list", new ListUsersStrategy(userService));
        strategyMap.put("generatePairs", new GeneratePairsStrategy(userService, journalRepository));
        strategyMap.put("edit", new EditUserStrategy(userService));
        strategyMap.put("addget", (req, resp) -> req.getRequestDispatcher("/useradd.jsp").forward(req, resp));
        strategyMap.put("journal", new JournalStrategy(userService, journalService));
        strategyMap.put("add", new AddUserStrategy(userService));
        strategyMap.put("update", new UpdateUserStrategy(userService));
        strategyMap.put("remove", new RemoveUserStrategy(userService));
        strategyMap.put("setMarks", new SetMarksStrategy(journalService, userService));
        strategyMap.put("updatemarks", new UpdateMarksStrategy(journalService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserActionStrategy strategy = strategyMap.get(action);
        if (strategy != null) {
            strategy.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserActionStrategy strategy = strategyMap.get(action);
        if (strategy != null) {
            strategy.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }
}
