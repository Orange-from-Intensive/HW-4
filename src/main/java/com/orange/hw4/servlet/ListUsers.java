package com.orange.hw4.servlet;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users")
@Slf4j
public class ListUsers  extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> userList = userService.getAllUsers();
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("/userlist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.warn("Error during receiving list of users.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during receiving list of users.");
        }
    }
}
