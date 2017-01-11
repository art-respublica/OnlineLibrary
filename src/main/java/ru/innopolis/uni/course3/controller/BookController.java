package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.service.BookService;

import java.time.LocalDate;

/**
 *
 */
@Controller
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public String showList(Model model){
        logger.info("Book controller: get all books");
        model.addAttribute("books", service.getAll());
        return "books";
    }

    @GetMapping("/books/read/{bookId}")
    public String readById(Model model, @PathVariable Integer bookId){
        logger.info("Book controller: read book with id", bookId);

        try {
            Book book = service.get(bookId);
            model.addAttribute("author", book.getAuthor());
            model.addAttribute("title", book.getTitle());
            model.addAttribute("text", book.getText());
            return "read";
        } catch (WrongProcessingOfBookException exception) {
            model.addAttribute("message", exception.getMessage());
            return "wrong";
        }
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteById(@PathVariable Integer bookId, Model model){
        logger.info("Book controller: delete book with id", bookId);
        try {
            service.delete(bookId);
            return "redirect:/books";
        } catch (WrongProcessingOfBookException exception) {
            model.addAttribute("message", exception.getMessage());
            return "wrong";
        }
    }

    @GetMapping("/books/update/{bookId}")
    public String updateById(Model model, @PathVariable Integer bookId){
        try {
            Book book = service.get(bookId);
            model.addAttribute("book", book);
            return "book";
        } catch (WrongProcessingOfBookException exception) {
            model.addAttribute("message", exception.getMessage());
            return "wrong";
        }
    }

    @GetMapping("/books/create/new")
    public String add(Model model){
        Book book = new Book("", "", LocalDate.now().getYear(), "");
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("books/*/save")
    public String processEditing(@ModelAttribute("book") Book book) {
        logger.info("Book controller:  " + (book.isNew() ? "create of" : "update of") +  book);
        if(book.isNew() ){
            service.add(book);
        } else {
            service.update(book);
        }
        return "redirect:/books";
    }
}
