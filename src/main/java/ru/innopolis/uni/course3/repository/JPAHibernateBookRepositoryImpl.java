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

    public JPAHibernateBookRepositoryImpl() {
    }

    public JPAHibernateBookRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book add(Book book) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        ru.innopolis.uni.course3.entity.Book bookEntity = BookMapper.INSTANCE.map(book);
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
        ru.innopolis.uni.course3.entity.Book bookEntity = BookMapper.INSTANCE.map(book);
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
        Book book = BookMapper.INSTANCE.map(em.find(ru.innopolis.uni.course3.entity.Book.class, id));
        em.close();
        return book;
    }

    @Override
    public List<Book> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Book b ORDER BY b.author, b.title");
        List<ru.innopolis.uni.course3.entity.Book> resultLits = query.getResultList();
        List<Book> books = resultLits.stream()
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
        em.close();
        return books;
    }

}