package ru.innopolis.uni.course3.controller.deprecated.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.service.UserService;
import ru.innopolis.uni.course3.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 *
 */
@Deprecated
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private ConfigurableApplicationContext springContext;
    private UserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        springContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-app.xml", "META-INF/spring/spring-db.xml");
        service = springContext.getBean(UserServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null) {

            logger.info("UserServlet: get all books");

            req.setAttribute("users", service.getAll());
            req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);

        } else if ("delete".equals(action)){

            int id = getId(req);
            logger.info("UserServlet: delete user with id", id);
            try {
                service.delete(id);
                resp.sendRedirect("users");
            } catch (WrongProcessingOfUserException e) {
                resp.sendRedirect("wrong");
            }

        } else if ("create".equals(action) || "update".equals(action) || "signup".equals(action) ){

            try {
                User user = action.equals("create") || "signup".equals(action) ?
                        new User("", "", "", new Date(), false, 0, null) : service.get(getId(req));
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
            } catch (WrongProcessingOfUserException e) {
                resp.sendRedirect("wrong");
            }

        } else if("profile".equals(action)) {

            String paramId = Objects.requireNonNull(req.getParameter("userId"));
            final User user;
            try {
                user = service.get(Integer.valueOf(paramId));
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
            } catch (WrongProcessingOfUserException e) {
                resp.sendRedirect("wrong");
            }

        } else if ("logout".equals(action)){

            HttpSession session = req.getSession();
            session.setAttribute("user", null);
            session.setAttribute("isLibrarian", false);
            resp.sendRedirect("books");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("save".equals(action)) {

            req.setCharacterEncoding("UTF-8");

            String id = req.getParameter("id");
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            User user = new User(id.isEmpty() ? null : Integer.valueOf(id),
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    null,
                    "true".equals(req.getParameter("enabled")) ? true : false,
                    Integer.parseInt(req.getParameter("version")),
                    Role.valueOf(req.getParameter("role")));
            logger.info("UserServlet:  " + (user.isNew() ? "create of" : "update of") + user);
            if (user.isNew()) {
                try {
                    service.add(user);
                } catch (WrongProcessingOfUserException e) {
                    resp.sendRedirect("wrong");
                }
            } else {
                service.update(user);
            }
            HttpSession session = req.getSession();
            if((boolean) session.getAttribute("isLibrarian")) {
                resp.sendRedirect("users");
            } else {
                resp.sendRedirect("books");
            }

        } else if ("signin".equals(action)) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = validateLogin(email, password);
            if (user == null){
                req.getRequestDispatcher("/WEB-INF/views/deprecated-login-error.jsp").forward(req, resp);
            } else {
                logger.info("UserServlet: sing in user with email", email);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("isLibrarian", user.getRoles().contains(Role.ROLE_USER.toString()));
                resp.sendRedirect("books");
            }
        }
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private User validateLogin(String email, String password) {

        // All parameters must be valid
        if (email == null || password == null){
            return null;
        }

        // Get a user by key
        User   user = service.getByEmail(email);
        if (user == null){
            return null;
        }

        // Check if the password is valid
        if (!user.getPassword().equals(password.trim())){
            return null;
        }

        return user;
    }
}
