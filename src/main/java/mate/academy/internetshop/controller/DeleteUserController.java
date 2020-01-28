package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class DeleteUserController extends HttpServlet {

    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(DeleteUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        try {
            userService.deleteById(userId);
            resp.sendRedirect(req.getContextPath() + "/servlet/allUsers");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete user");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
