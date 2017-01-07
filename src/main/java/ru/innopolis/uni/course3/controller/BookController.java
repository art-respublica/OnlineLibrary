package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        Book book = service.get(bookId);
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("text", book.getText());
        return "read";
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteById(@PathVariable Integer bookId){
        logger.info("Book controller: delete book with id", bookId);
        service.delete(bookId);
        return "redirect:/books";
    }

    @GetMapping("/books/update/{bookId}")
    public String updateById(Model model, @PathVariable Integer bookId){
        Book book = service.get(bookId);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books/create/new")
    public String add(Model model){
        Book book = new Book("", "", LocalDate.now().getYear(), "");
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("books/*/save")
    public String processEditing(@RequestParam Integer id, @RequestParam String author,
                                 @RequestParam String title, @RequestParam Integer year,
                                 @RequestParam String text) {

        Book book = new Book(id, author, title, year, text);
        logger.info("Book controller:  " + (book.isNew() ? "create of" : "update of") +  book);
        if(book.isNew() ){
            service.add(book);
        } else {
            service.update(book);
        }
        return "redirect:/books";
    }
}
