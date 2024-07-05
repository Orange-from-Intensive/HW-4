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
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/user")
@Slf4j
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "list":
                listUsers(req, resp);
                break;
            case "edit":
                editUserForm(req, resp);
                break;
            case "add":
                req.getRequestDispatcher("/useradd.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addUser(req, resp);
                break;
            case "edit":
                editUser(req, resp);
                break;
            case "remove":
                removeUser(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> userList = userService.getAllUsers();
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("/userlist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.warn("Error during receiving list of users.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during receiving list of users.");
        }
    }

    private void editUserForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void addUser(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String dateOfBirthString = request.getParameter("dateOfBirth");
        log.debug("Received parameters - name: {}, surname: {}, dateOfBirth: {}", name, surname, dateOfBirthString);
        final LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        try {
            userService.addUser(name, surname, dateOfBirth);
            resp.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            log.warn("Error adding user.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding user.");
        }
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthDateString = req.getParameter("birthDate");
        String idString = req.getParameter("id");
        log.debug("Received parameters - name: {}, surname: {}, birthDate: {}, id: {}", name, surname, birthDateString, idString);
        try {
            long id = Long.parseLong(idString);
            LocalDate birthDate = LocalDate.parse(birthDateString);
            userService.updateUser(name, surname, birthDate, id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            log.warn("Error editing user.", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error editing user.");
        }
    }



    private void removeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
