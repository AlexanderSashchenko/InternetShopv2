package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.Logger;

public class GetAllItemsInBucketController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetAllItemsInBucketController.class);
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Item> items = new ArrayList<>();
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            items = bucket.getItems();
        } catch (DataProcessingException e) {
            LOGGER.error("Failed to get bucket items list");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        }
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
