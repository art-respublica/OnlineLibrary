package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;

/**
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler({WrongProcessingOfBookException.class})
    public String processingOfBookError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "wrong";
    }

    @ExceptionHandler({WrongProcessingOfUserException.class})
    public String processingOfUserError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "wrong";
    }
}
