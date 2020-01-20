package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User("user");
        user.setPassword("1");
        user.addRole(Role.of("USER"));
        userService.create(user);

        User userAdmin = new User("admin");
        userAdmin.setPassword("admin");
        userAdmin.addRole(Role.of("ADMIN"));
        userService.create(userAdmin);
        resp.sendRedirect(req.getContextPath() + "/login");

        Item smartphone = new Item("Smartphone", "199.99");
        itemService.create(smartphone);
        Item laptop = new Item("Laptop", "999.99");
        itemService.create(laptop);
    }
}
