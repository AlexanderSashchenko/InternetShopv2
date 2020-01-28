package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class InjectDataController extends HttpServlet {

    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(InjectDataController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User user = new User("user");
        user.setPassword("1");
        user.addRole(Role.of("USER"));
        try {
            userService.create(user);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to inject user(user)");
            resp.sendRedirect(req.getContextPath() + "/error");
        }

        User userAdmin = new User("admin");
        userAdmin.setPassword("admin");
        userAdmin.addRole(Role.of("ADMIN"));
        try {
            userService.create(userAdmin);
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to inject user(admin)");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
