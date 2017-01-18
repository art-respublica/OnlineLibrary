package ru.innopolis.uni.course3.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.uni.course3.model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
public class JPAHibernateBookRepositoryImpl implements BookRepository {

    private EntityManager em;

    public JPAHibernateBookRepositoryImpl() {
    }

    public JPAHibernateBookRepositoryImpl(EntityManagerFactory emf) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("onlinelibrarydb");
        this.em = emf.createEntityManager();
    }

    @Override
    public Book add(Book book) {
        em.getTransaction().begin();
        ru.innopolis.uni.course3.entity.Book bookEntity = convertBookToBookEntity(book, false);
        if (!em.contains(bookEntity)) {
            em.persist(bookEntity);
        }
        book.setId(bookEntity.getId());
        em.getTransaction().commit();
        return book;
    }

    @Override
    public Book update(Book book) {
        em.getTransaction().begin();
        ru.innopolis.uni.course3.entity.Book bookEntity = convertBookToBookEntity(book, true);
        em.merge(bookEntity);
        em.flush();
        em.getTransaction().commit();
        return book;
    }

    @Override
    public boolean delete(int id) {
        em.getTransaction().begin();
        boolean result = em.createQuery("DELETE FROM Book b WHERE b.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        em.flush();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Book get(int id) {
        return convertBookEntiryToBook(em.find(ru.innopolis.uni.course3.entity.Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        Query query = em.createQuery("SELECT b FROM Book b ORDER BY b.author, b.title");
        List<ru.innopolis.uni.course3.entity.Book> resultLits = query.getResultList();
        List<Book> books = resultLits.stream()
                .map(JPAHibernateBookRepositoryImpl::convertBookEntiryToBook)
                .collect(Collectors.toList());
        return books;
    }

    private static Book convertBookEntiryToBook(ru.innopolis.uni.course3.entity.Book b) {
        if(b == null) {
            return null;
        }
        return new Book(b.getId(), b.getAuthor(), b.getTitle(), b.getYear(), b.getText());
    }

    private static ru.innopolis.uni.course3.entity.Book convertBookToBookEntity(Book book, boolean hasId) {
        ru.innopolis.uni.course3.entity.Book bookEntity = new ru.innopolis.uni.course3.entity.Book();
        if(book == null) {
            return bookEntity;
        }
        if(hasId) {
            bookEntity.setId(book.getId());
        }
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setYear(book.getYear());
        bookEntity.setText(book.getText());
        return bookEntity;
    }

}