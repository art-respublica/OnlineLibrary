package ru.innopolis.uni.course3.repository;

import ru.innopolis.uni.course3.model.Book;

import java.util.List;

/**
 *  Contains a set of methods, through which can interact with DBMS relating to the books
 */
public interface BookRepository {

    /**
     *  Adds the book into the database
     *
     *  @param  book  added book
     *  @return Book  book, which was added
     */
    Book add(Book book);

    /**
     *  Updates the book data in the database
     *
     *  @param  book  updated book
     *  @return Book  book, which was updated
     */
    Book update(Book book);

    /**
     *  Deletes the book in the database
     *
     *  @param  id  id of deleting book
     *  @return boolean  true - if deleting was successful, false - otherwise
     */
    boolean delete(int id);

    /**
     *  Gets the book from the database by id
     *
     *  @param  id      id, which uses for book search
     *  @return Book    book, which was found or null if not found
     */
    Book get(int id);

    /**
     *  Gets the list of all books from the database
     *  @return List<Book>    list of all books, which contained in the database
     */
    List<Book> getAll();
}
