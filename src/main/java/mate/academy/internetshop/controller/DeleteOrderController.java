package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.Logger;

public class DeleteOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteOrderController.class);
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        try {
            orderService.deleteById(orderId);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete order");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllUserOrders");
    }
}
