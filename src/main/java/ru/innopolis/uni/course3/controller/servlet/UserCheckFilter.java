package ru.innopolis.uni.course3.controller.servlet;

import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 *
 */
public class UserCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String path = ((HttpServletRequest) servletRequest).getServletPath();

        String userId = null;
        if( path.contains("users") && path.contains("profile") ) {
            String[] strings = path.split("/");
            userId = strings[strings.length - 1];
        }

        if ( (path.contains("users")
                && !path.contains("signup")
                && !path.contains("signin")
                && user == null)
            || (path.contains("users")
                && user != null
                && !path.contains("profile")
                && !path.contains("save")
                && !path.contains("logout")
                && !path.contains("signin")
                && !user.getRole().equals(Role.ROLE_LIBRARIAN.toString()))
            || (user == null && userId != null)
            || (user != null && userId != null
                && !user.getId().equals(Integer.valueOf(userId))) ){
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Integer getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
