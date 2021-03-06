package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.exception.ExceptionUtil;
import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.mapper.BookMapper;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.repository.SpringDataBookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  Implements BookService methods
 */
@Service
public class SpringDataBookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(SpringDataBookServiceImpl.class);

    @Autowired
    private SpringDataBookRepository repository;

    @Autowired
    private BookMapper mapper;

    public SpringDataBookServiceImpl() {
    }

    @Override
    public Book add(Book book) {
        logger.info("Spring Data Book service: adding book {}", book);
        return mapper.map(repository.save(mapper.map(book)));
    }

    @Override
    public Book update(Book book) {
        logger.info("Spring Data Book service: updating book {}", book);
        return mapper.map(repository.save(mapper.map(book)));
    }

    @Override
    public void delete(int id) throws WrongProcessingOfBookException {
        logger.info("Spring Data Book service: deleting book with id {}", id);
        repository.delete(id);
    }

    @Override
    public Book get(int id) throws WrongProcessingOfBookException {
        logger.info("Spring Data Book service: getting book with id {}", id);
        return ExceptionUtil.checkBookNotFoundWithId(mapper.map(repository.findOne(id)), id);
    }

    @Override
    public List<Book> getAll() {
        logger.info("Spring Data Book service: getting all books");
        List<Book> books = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
        return books;
    }
}
