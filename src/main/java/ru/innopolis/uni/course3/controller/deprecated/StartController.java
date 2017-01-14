package ru.innopolis.uni.course3.controller.deprecated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Deprecated
//@Controller
public class StartController {

    private static final Logger logger = LoggerFactory.getLogger(StartController.class);

//    @GetMapping("/")
    public String redirectToBooks(){
        logger.info("Start controller: redirect to books");
        return "redirect:/books";
    }
}
