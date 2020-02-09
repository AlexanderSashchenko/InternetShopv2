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

public class AddNewItemController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddNewItemController.class);
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Item> items = itemService.getAllEntities();
            req.setAttribute("items", items);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to add new item");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String itemName = req.getParameter("title");
        String itemPrice = req.getParameter("price");
        Item item = new Item(itemName, itemPrice);
        try {
            itemService.create(item);
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to add new item");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/addItem");
    }
}
