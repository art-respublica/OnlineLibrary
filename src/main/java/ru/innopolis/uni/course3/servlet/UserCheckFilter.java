package ru.innopolis.uni.course3.servlet;

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
        String stringId = req.getParameter("userId");

        if (stringId != null && user != null && !user.getId().equals(Integer.valueOf(stringId))){
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
