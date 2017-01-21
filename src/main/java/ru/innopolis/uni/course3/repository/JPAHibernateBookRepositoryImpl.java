package ru.innopolis.uni.course3.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.mapper.BookMapper;
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
    private EntityManagerFactory emf;
    private BookMapper mapper;

    public JPAHibernateBookRepositoryImpl() {
    }

    public JPAHibernateBookRepositoryImpl(EntityManagerFactory emf, BookMapper mapper) {
        this.emf = emf;
        this.mapper = mapper;
    }

    @Override
    public Book add(Book book) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        ru.innopolis.uni.course3.entity.Book bookEntity = mapper.map(book);
        if (!em.contains(bookEntity)) {
            em.persist(bookEntity);
//            em.flush();
        }
        book.setId(bookEntity.getId());
        transaction.commit();
        em.close();
        return book;
    }

    @Override
    public Book update(Book book) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        ru.innopolis.uni.course3.entity.Book bookEntity = mapper.map(book);
        em.merge(bookEntity);
//        em.flush();
        transaction.commit();
        em.close();
        return book;
    }

    @Override
    public boolean delete(int id) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        boolean result = em.createQuery("DELETE FROM Book b WHERE b.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        transaction.commit();
        em.close();
        return result;
    }

    @Override
    public Book get(int id) {
        em = emf.createEntityManager();
        Book book = mapper.map(em.find(ru.innopolis.uni.course3.entity.Book.class, id));
        em.close();
        return book;
    }

    @Override
    public List<Book> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Book b ORDER BY b.author, b.title");
        List<ru.innopolis.uni.course3.entity.Book> resultLits = query.getResultList();
        List<Book> books = resultLits.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
        em.close();
        return books;
    }

}