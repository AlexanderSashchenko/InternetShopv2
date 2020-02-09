package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class DeleteItemController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteItemController.class);
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        try {
            itemService.deleteById(itemId);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete item");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/addItem");
    }
}
