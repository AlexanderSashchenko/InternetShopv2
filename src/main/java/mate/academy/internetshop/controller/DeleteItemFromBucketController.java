package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class DeleteItemFromBucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    private static Logger LOGGER = Logger.getLogger(DeleteItemFromBucketController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        try {
            bucketService.deleteItem(bucketService.getByUserId(userId), itemService.get(itemId));
            resp.sendRedirect(req.getContextPath() + "/servlet/viewBucket");
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to delete item from bucket");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
