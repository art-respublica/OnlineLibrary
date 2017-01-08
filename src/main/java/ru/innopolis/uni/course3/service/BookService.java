package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;

import java.util.List;

/**
 *
 */
public interface BookService {

    Book add(Book book);

    Book update(Book book);

    void delete(int id) throws WrongProcessingOfBookException;

    Book get(int id) throws WrongProcessingOfBookException;

    List<Book> getAll();
}
