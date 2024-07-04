package com.orange.hw4.servlet;

import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@WebServlet(urlPatterns = "/user/edit", initParams = {
        @WebInitParam(name="id", value = "0")
})
public class EditUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        try {
            Long id = Long.parseLong(idString);
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/useredit.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            log.warn("Malformed id: {}", idString);
            req.getRequestDispatcher("/noSuchId.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthDate = req.getParameter("birthDate");
        String idString = req.getParameter("id");
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            log.warn("Malformed id: {}", idString);
            req.getRequestDispatcher("/noSuchId.jsp").forward(req, resp);
            return;
        }
        userService.updateUser(name,surname, LocalDate.parse(birthDate),id);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
