package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class DeleteUserController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    private static Logger LOGGER = Logger.getLogger(DeleteUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            bucketService.clear(bucket);
            bucketService.delete(bucket);
            orderService.deleteAllUserOrders(userId);
            userService.deleteById(userId);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete user");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/allUsers");
    }
}
