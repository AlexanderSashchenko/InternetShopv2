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

    @Inject
    private static OrderService orderService;

    private static Logger LOGGER = Logger.getLogger(DeleteOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        try {
            orderService.deleteById(orderId);
            resp.sendRedirect(req.getContextPath() + "/servlet/getAllUserOrders");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete order");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
