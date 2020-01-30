package mate.academy.internetshop.web.filters;

import java.io.IOException;
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
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

    @Inject
    private static UserService userService;

    private static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

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
        Long userId = (Long) session.getAttribute("userId");
        try {
            Optional<User> user = Optional.ofNullable(userService.get(userId));
            if (user.isPresent()) {
                filterChain.doFilter(req, resp);
            } else {
                processUnauthenticated(req, resp);
            }
        } catch (DataProcessingException e) {
            LOGGER.error("Authentication fail: failed to get user");
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }

    private void processUnauthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
