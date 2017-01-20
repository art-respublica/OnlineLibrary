package ru.innopolis.uni.course3;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.innopolis.uni.course3.service.SpringDataBookServiceImpl;

import java.util.Arrays;

/**
 *
 */
public class SpringContextStartTest {

    public static void main(String[] args) {

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("file:src/main/webapp/META-INF/spring/spring-app.xml",
                    "file:src/main/webapp/META-INF/spring/spring-db.xml",
                    "file:src/main/webapp/META-INF/spring/spring-security.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            //BookService bookService = appCtx.getBean(BookServiceImpl.class);
            SpringDataBookServiceImpl bookService = appCtx.getBean(SpringDataBookServiceImpl.class);
            System.out.println(bookService);
        }
    }
}
