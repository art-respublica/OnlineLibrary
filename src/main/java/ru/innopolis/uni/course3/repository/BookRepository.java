package ru.innopolis.uni.course3.repository;

import ru.innopolis.uni.course3.model.Book;

import java.util.List;

/**
 *
 */
public interface BookRepository {

    Book add(Book book);

    Book update(Book book);

    boolean delete(int id);

    Book get(int id);

    List<Book> getAll();
}
