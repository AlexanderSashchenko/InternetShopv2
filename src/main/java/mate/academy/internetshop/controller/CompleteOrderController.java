package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class CompleteOrderController extends HttpServlet {

    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(CompleteOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Item> items;
        try {
            items = bucketService.getByUserId(userId).getItems();
            orderService.completeOrder(items, userService.get(userId));
            bucketService.clear(bucketService.getByUserId(userId));
            resp.sendRedirect(req.getContextPath() + "/servlet/getAllUserOrders");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to complete order");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
