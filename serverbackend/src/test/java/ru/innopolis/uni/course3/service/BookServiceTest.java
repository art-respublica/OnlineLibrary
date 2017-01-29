package ru.innopolis.uni.course3.service;

import org.junit.Ignore;
import ru.innopolis.uni.course3.TestData;
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

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@ContextConfiguration({
        "file:./serverbackend/src/main/webapp/META-INF/spring/spring-app.xml",
        "file:./serverbackend/src/main/webapp/META-INF/spring/spring-db.xml"
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
        Book created = TestData.getCreatedBook();
        service.add(created);
        List<Book> expected = Arrays.asList(created, TestData.BOOK1, TestData.BOOK2, TestData.BOOK3, TestData.BOOK4, TestData.BOOK5, TestData.BOOK6, TestData.BOOK7, TestData.BOOK8, TestData.BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of add book haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Test
    public void updateTest() {
        Book updated = TestData.getUpdatedBook();
        service.update(updated);
        List<Book> expected = Arrays.asList(updated, TestData.BOOK2, TestData.BOOK3, TestData.BOOK4, TestData.BOOK5, TestData.BOOK6, TestData.BOOK7, TestData.BOOK8, TestData.BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of update book haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Test
    public void deleteTest() throws WrongProcessingOfBookException {
        service.delete(TestData.BOOK1_ID);
        List<Book> expected = Arrays.asList(TestData.BOOK2, TestData.BOOK3, TestData.BOOK4, TestData.BOOK5, TestData.BOOK6, TestData.BOOK7, TestData.BOOK8, TestData.BOOK9);
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of delete book haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Ignore
    @Test(expected = WrongProcessingOfBookException.class)
    public void deleteNotExistTest() throws Exception {
        service.delete(TestData.READER_ID);
    }

    @Test
    public void getTest() throws WrongProcessingOfBookException  {
        Book actual = service.get(TestData.BOOK1_ID);
        Assert.assertEquals("Test of get book haven't passed", TestData.as(TestData.BOOK1), TestData.as(actual));
    }

    @Test(expected = WrongProcessingOfBookException.class)
    public void getNotExistTest() throws Exception {
        service.get(TestData.READER_ID);
    }

    @Test
    public void getAllTest() {
        List<Book> expected = TestData.BOOKS;
        List<Book> actual = service.getAll();
        Assert.assertEquals("Test of get all books haven't passed", TestData.as(expected), TestData.as(actual));
    }

}
