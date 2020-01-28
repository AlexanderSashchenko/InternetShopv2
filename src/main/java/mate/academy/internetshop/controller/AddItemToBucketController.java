package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class AddItemToBucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    private static Logger LOGGER = Logger.getLogger(AddItemToBucketController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            String itemId = req.getParameter("item_id");
            Item item = itemService.get(Long.parseLong(itemId));
            bucketService.addItem(bucket, item);
            resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to add item to bucket", e);
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
