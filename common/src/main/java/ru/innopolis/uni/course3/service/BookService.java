package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.exception.WrongProcessingOfBookException;
import ru.innopolis.uni.course3.model.Book;

import java.util.List;

/**
 *  Contains a set of methods, through which can interact with business logic layer relating to the books
 */
public interface BookService {

    /**
     *  Adds the book into the database through repository(DAO) layer.
     *
     *  @param  book  added book
     *  @return Book  book, which was added
     */
    Book add(Book book);

    /**
     *  Modifies the books data and updates it through repository(DAO) layer.
     *
     *  @param  book  updated book
     *  @return Book  book, which was updated
     */
    Book update(Book book);

    /**
     *  Deletes the book from the database through repository(DAO) layer.
     *
     *  @param  id  id of deleting book
     */
    void delete(int id) throws WrongProcessingOfBookException;

    /**
     *  Gets the book from the database by id through repository(DAO) layer.
     *
     *  @param  id      id, which uses for book search
     *  @return Book    book, which was found
     */
    Book get(int id) throws WrongProcessingOfBookException;

    /**
     *  Gets the list of all books from the database through repository(DAO) layer.
     *
     *  @return List<Book>    list of all books, which contained in the database
     */
    List<Book> getAll();
}
