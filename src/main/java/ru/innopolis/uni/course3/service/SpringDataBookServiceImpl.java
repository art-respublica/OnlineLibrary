package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private SpringDataBookRepository repository;

    @Autowired
    private BookMapper mapper;

    public SpringDataBookServiceImpl() {
    }

    @Override
    public Book add(Book book) {
        return mapper.map(repository.save(mapper.map(book)));
    }

    @Override
    public Book update(Book book) {
        return mapper.map(repository.save(mapper.map(book)));
    }

    @Override
    public void delete(int id) throws WrongProcessingOfBookException {
        repository.delete(id);
    }

    @Override
    public Book get(int id) throws WrongProcessingOfBookException {
        return ExceptionUtil.checkBookNotFoundWithId(mapper.map(repository.findOne(id)), id);
    }

    @Override
    public List<Book> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }
}
