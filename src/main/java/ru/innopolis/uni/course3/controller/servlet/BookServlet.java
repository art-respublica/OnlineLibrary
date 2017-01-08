package ru.innopolis.uni.course3.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.service.BookService;
import ru.innopolis.uni.course3.service.BookServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 */
@Deprecated
public class BookServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BookServlet.class);

    private ConfigurableApplicationContext springContext;
    private BookService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        service = springContext.getBean(BookServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null) {

            logger.info("BookServlet: get all books");

            req.setAttribute("books", service.getAll());
            req.getRequestDispatcher("/WEB-INF/views/books.jsp").forward(req, resp);

        } else if ("read".equals(action)){

            int id = getId(req);
            logger.info("BookServlet: read book with id", id);
            try {
                Book book = service.get(id);
                req.setAttribute("author", book.getAuthor());
                req.setAttribute("title",  book.getTitle());
                req.setAttribute("text", book.getText());
                req.getRequestDispatcher("/WEB-INF/views/read.jsp").forward(req, resp);
            } catch (WrongProcessingOfBookException e) {
                resp.sendRedirect("wrong");
            }

        } else if ("delete".equals(action)){

            int id = getId(req);
            logger.info("BookServlet: delete book with id", id);
            try {
                service.delete(id);
                resp.sendRedirect("books");
            } catch (WrongProcessingOfBookException e) {
                resp.sendRedirect("wrong");
            }

        } else if ("create".equals(action) || "update".equals(action)){

            try {
                Book book = action.equals("create") ?
                        new Book("", "", LocalDate.now().getYear(), "") : service.get(getId(req));
                req.setAttribute("book", book);
                req.getRequestDispatcher("/WEB-INF/views/book.jsp").forward(req, resp);
            } catch (WrongProcessingOfBookException e) {
                resp.sendRedirect("wrong");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        Book book = new Book(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("author"),
                req.getParameter("title"),
                Integer.valueOf(req.getParameter("year")),
                req.getParameter("text"));
        logger.info("BookServlet:  " + (book.isNew() ? "create of" : "update of") +  book);
        if(book.isNew() ){
            service.add(book);
        } else {
            service.update(book);
        }
        resp.sendRedirect("books");
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
