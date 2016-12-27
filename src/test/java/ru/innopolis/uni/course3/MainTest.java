package ru.innopolis.uni.course3;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.innopolis.uni.course3.service.BookService;
import ru.innopolis.uni.course3.service.BookServiceImpl;

import java.util.Arrays;

/**
 * Created by Артем on 27.12.2016.
 */
public class MainTest {

    public static void main(String[] args) {

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            BookService bookService = appCtx.getBean(BookServiceImpl.class);
            System.out.println();
        }
    }
}
