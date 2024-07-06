package com.orange.hw4.servlet;

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

    private Map<String, UserActionStrategy> getStrategies;
    private Map<String, UserActionStrategy> postStrategies;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserService userService = (UserService) config.getServletContext().getAttribute("userService");

        getStrategies = new HashMap<>();
        getStrategies.put("list", new ListUsersStrategy(userService));
        getStrategies.put("generatePairs", new GeneratePairsStrategy(userService));
        getStrategies.put("edit", new EditUserStrategy(userService));
        getStrategies.put("add", (req, resp) -> req.getRequestDispatcher("/useradd.jsp").forward(req, resp));

        postStrategies = new HashMap<>();
        postStrategies.put("add", new AddUserStrategy(userService));
        postStrategies.put("update", new UpdateUserStrategy(userService));
        postStrategies.put("remove", new RemoveUserStrategy(userService));
        postStrategies.put("generatePairs", new GeneratePairsStrategy(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserActionStrategy strategy = getStrategies.get(action);
        if (strategy != null) {
            strategy.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserActionStrategy strategy = postStrategies.get(action);
        if (strategy != null) {
            strategy.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }
}
