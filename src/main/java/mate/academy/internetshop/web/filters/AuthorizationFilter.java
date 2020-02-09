package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final String EMPTY_STRING = "";
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/servlet/addItem", ADMIN);
        protectedUrls.put("/servlet/allUsers", ADMIN);
        protectedUrls.put("/servlet/deleteUser", ADMIN);
        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/viewBucket", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
        protectedUrls.put("/servlet/completeOrder", USER);
        protectedUrls.put("/servlet/getAllUserOrders", USER);
        protectedUrls.put("/servlet/deleteOrder", USER);
        protectedUrls.put("/servlet/deleteItem", ADMIN);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("userId") == null) {
            processUnauthenticated(req, resp);
            return;
        }

        String requestedUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            filterChain.doFilter(req, resp);
        }

        Long userId = (Long) session.getAttribute("userId");
        try {
            Optional<User> user = Optional.ofNullable(userService.get(userId));
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    filterChain.doFilter(req, resp);
                } else {
                    processDenied(req, resp);
                }
            } else {
                processUnauthenticated(req, resp);
            }
        } catch (DataProcessingException e) {
            LOGGER.error("Authorization fail: failed to get user");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }

    @Override
    public void destroy() {

    }

    private void processUnauthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp")
                .forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles()
                .stream()
                .anyMatch(r -> r.getRoleName().equals(roleName));
    }
}
