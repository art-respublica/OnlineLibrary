package ru.innopolis.uni.course3.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;

import static ru.innopolis.uni.course3.TestData.*;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populatedb_postgres.sql", config = @SqlConfig(encoding = "UTF-8"))
public class BookServiceTest {

    private static Logger logger = LoggerFactory.getLogger(BookServiceTest.class);

    @Autowired
    private BookService service;

    @Test
    public void addTest() {
        logger.info("Test of add book was running");
        Book created = getCreatedBook();
        service.add(created);
        List<Book> expected = Arrays.asList(created, BOOK1, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8, BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of add book haven't passed", as(expected), as(actual));
    }

    @Test
    public void updateTest() {
        Book updated = getUpdatedBook();
        service.update(updated);
        List<Book> expected = Arrays.asList(updated, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8, BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of update book haven't passed", as(expected), as(actual));
    }

    @Test
    public void deleteTest() throws WrongProcessingOfBookException {
        service.delete(BOOK1_ID);
        List<Book> expected = Arrays.asList(BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8, BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of delete book haven't passed", as(expected), as(actual));
    }

    @Test(expected = WrongProcessingOfBookException.class)
    public void deleteNotExistTest() throws Exception {
        service.delete(READER_ID);
    }

    @Test
    public void getTest() throws WrongProcessingOfBookException  {
        Book actual = service.get(BOOK1_ID);
        Assert.assertEquals("Test of get book haven't passed", as(BOOK1), as(actual));
    }

    @Test(expected = WrongProcessingOfBookException.class)
    public void getNotExistTest() throws Exception {
        service.get(READER_ID);
    }

    @Test
    public void getAllTest() {
        List<Book> expected = BOOKS;
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of get all books haven't passed", as(expected), as(actual));
    }

}
