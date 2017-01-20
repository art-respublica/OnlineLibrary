package ru.innopolis.uni.course3.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    @ExceptionHandler({ConstraintViolationException.class})
    public String processingOfSpringDataErrorConstraintViolation(Model model, Exception exception) {
        model.addAttribute("message", "Some problems with adding of user - duplicate email");
        return "wrong";
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public String processingOfSpringDataErrorEmptyResultDataAccess(Model model, Exception exception) {
        model.addAttribute("message", "Rather, the unit can not be found");
        return "wrong";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Exception exception)   {
        return "404";
    }

}
