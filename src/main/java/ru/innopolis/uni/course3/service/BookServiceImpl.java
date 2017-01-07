package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.repository.BookRepository;

import java.util.List;

/**
 *
 */
@Component
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository repository;

    public BookServiceImpl() {
    }

    @Override
    public Book add(Book book) {
        return repository.add(book);
    }

    @Override
    public Book update(Book book) {
        return repository.update(book);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Book get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }
}
