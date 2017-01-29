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
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@ContextConfiguration({
        "file:../../../../../../../../ServerBackEnd/src/main/webapp/META-INF/spring/spring-app.xml",
        "file:../../../../../../../../ServerBackEnd/src/main/webapp/META-INF/spring/spring-db.xml"
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
        User created = TestData.getCreatedUser();
        service.add(created);
        List<User> expected = Arrays.asList(created, TestData.READER, TestData.LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of add user haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Ignore
    @Test(expected = WrongProcessingOfUserException.class)
    public void addDuplicateEmailTest() throws Exception {
        User created = TestData.getCreatedUser();
        created.setEmail("reader@onlinelibrary.ru");
        service.add(created);
    }

    @Test
    public void updateTest() {
        User updated = TestData.getUpdatedUser();
        service.update(updated);
        List<User> expected = Arrays.asList(updated, TestData.LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of update user haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Test
    public void deleteTest() throws WrongProcessingOfUserException {
        service.delete(TestData.READER_ID);
        List<User> expected = Arrays.asList(TestData.LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of delete user haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Ignore
    @Test(expected = WrongProcessingOfUserException.class)
    public void deleteNotExistTest() throws Exception {
        service.delete(TestData.BOOK1_ID);
    }

    @Test
    public void getTest() throws WrongProcessingOfUserException  {
        User actual = service.get(TestData.READER_ID);
        Assert.assertEquals("Test of get user haven't passed", TestData.as(TestData.READER), TestData.as(actual));
    }

    @Test(expected = WrongProcessingOfUserException.class)
    public void getNotExistTest() throws Exception {
        service.get(TestData.BOOK1_ID);
    }

    @Test
    public void getAllTest() {
        List<User> expected = Arrays.asList(TestData.READER, TestData.LIBRARIAN);
        List<User> actual = service.getAll();
        Assert.assertEquals("Test of get all users haven't passed", TestData.as(expected), TestData.as(actual));
    }

    @Test
    public void getByEmailTest() {
        User actual = service.getByEmail("reader@onlinelibrary.ru");
        Assert.assertEquals("Test of get user haven't passed", TestData.as(TestData.READER), TestData.as(actual));
    }

}
