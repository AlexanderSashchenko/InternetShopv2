package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class GetAllUserOrdersController extends HttpServlet {

    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(GetAllUserOrdersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            List<Order> orders = orderService.getUserOrders(userService.get(userId));
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/WEB-INF/views/allUserOrders.jsp").forward(req, resp);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to get user orders list");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
