package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.model.Book;

import java.util.List;

/**
 *
 */
public interface BookService {

    Book add(Book book);

    Book update(Book book);

    void delete(int id);

    Book get(int id);

    List<Book> getAll();
}
