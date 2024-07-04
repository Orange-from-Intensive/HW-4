package com.orange.hw4.servlet;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/users/add")
@Slf4j
public class AddUser extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/useradd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final LocalDate birthDate = LocalDate.parse(request.getParameter("dateOfBirth"));
        try {
            userService.addUser(name, surname, birthDate);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            log.error("Error message.", e);
        }
    }
}
