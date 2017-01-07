package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
@Controller
public class StartController {

    private static final Logger logger = LoggerFactory.getLogger(StartController.class);

    @GetMapping("/")
    public String redirectToBooks(){
        logger.info("Start controller: redirect to books");
        return "redirect:/books";
    }
}
