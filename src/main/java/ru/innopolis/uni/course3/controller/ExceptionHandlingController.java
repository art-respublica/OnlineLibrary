package ru.innopolis.uni.course3.controller;

import org.springframework.stereotype.Controller;
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
