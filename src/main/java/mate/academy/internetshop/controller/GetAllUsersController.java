package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class GetAllUsersController extends HttpServlet {

    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(GetAllUsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users;
        try {
            List<User> users = userService.getAllEntities();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/views/allUsers.jsp").forward(req, resp);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to get users list");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
