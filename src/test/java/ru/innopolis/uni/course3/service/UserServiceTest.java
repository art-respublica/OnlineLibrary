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
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.Arrays;
import java.util.List;

import static ru.innopolis.uni.course3.TestData.*;

/**
 *
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populatedb_postgres.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService service;

    @Test
    public void addTest() throws Exception {
        logger.info("Test of add user was running");
        User created = getCreatedUser();
        service.add(created);
        List<User> expected = Arrays.asList(created, READER, LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of add user haven't passed", as(expected), as(actual));
    }

    @Test(expected = WrongProcessingOfUserException.class)
    public void addDuplicateEmailTest() throws Exception {
        User created = getCreatedUser();
        created.setEmail("reader@onlinelibrary.ru");
        service.add(created);
    }

    @Test
    public void updateTest() {
        User updated = getUpdatedUser();
        service.update(updated);
        List<User> expected = Arrays.asList(updated, LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of update user haven't passed", as(expected), as(actual));
    }

    @Test
    public void deleteTest() throws WrongProcessingOfUserException {
        service.delete(READER_ID);
        List<User> expected = Arrays.asList(LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of delete user haven't passed", as(expected), as(actual));
    }

    @Test(expected = WrongProcessingOfUserException.class)
    public void deleteNotExistTest() throws Exception {
        service.delete(BOOK1_ID);
    }

    @Test
    public void getTest() throws WrongProcessingOfUserException  {
        User actual = service.get(READER_ID);
        Assert.assertEquals("Test of get user haven't passed", as(READER), as(actual));
    }

    @Test(expected = WrongProcessingOfUserException.class)
    public void getNotExistTest() throws Exception {
        service.get(BOOK1_ID);
    }

    @Test
    public void getAllTest() {
        List<User> expected = Arrays.asList(READER, LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of get all users haven't passed", as(expected), as(actual));
    }

    @Test
    public void getByEmailTest() throws WrongProcessingOfUserException {
        User actual = service.getByEmail("reader@onlinelibrary.ru");
        Assert.assertEquals("Test of get user haven't passed", as(READER), as(actual));
    }

    @Test(expected = WrongProcessingOfUserException.class)
    public void getByNotExistEmailTest() throws Exception {
        service.getByEmail("reader2@onlinelibrary.ru");
    }

}
