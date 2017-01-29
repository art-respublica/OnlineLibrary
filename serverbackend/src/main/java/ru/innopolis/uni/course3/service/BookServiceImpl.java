package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.exception.ExceptionUtil;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.repository.BookRepository;

import java.util.List;

/**
 *  Implements BookService methods
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository repository;

    public BookServiceImpl() {
    }

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
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
    public void delete(int id) throws WrongProcessingOfBookException {
        ExceptionUtil.checkBookNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Book get(int id) throws WrongProcessingOfBookException {
        return ExceptionUtil.checkBookNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }
}
