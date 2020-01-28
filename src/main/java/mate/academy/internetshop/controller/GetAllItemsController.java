package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class GetAllItemsController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    private static Logger LOGGER = Logger.getLogger(GetAllItemsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Item> items = itemService.getAllEntities();
            req.setAttribute("items", items);
            req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req, resp);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to get items list");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
