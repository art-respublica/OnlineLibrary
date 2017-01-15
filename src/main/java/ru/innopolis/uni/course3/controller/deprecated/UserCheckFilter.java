package ru.innopolis.uni.course3.controller.deprecated;

import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
@Deprecated
public class UserCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //servletRequest.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String path = ((HttpServletRequest) servletRequest).getServletPath();

        Integer userId = null;
        if( path.contains("users/profile/") ) {
            String[] strings = path.split("/");
            try {
                userId = Integer.parseInt(strings[strings.length - 1]);
            } catch (NumberFormatException exception) {
                userId = null;
            }
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
                && !user.getRoles().contains(Role.ROLE_USER.toString()))
            || (path.contains("books")
                && user != null
                && ( path.contains("update")
                    || path.contains("create")
                    || path.contains("delete") )
                && !user.getRoles().contains(Role.ROLE_USER.toString()))
            || (user == null && userId != null)
            || (user != null && userId != null
                && !userId.equals(user.getId())) ){
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
