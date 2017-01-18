package ru.innopolis.uni.course3.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.mapper.UserMapper;
import ru.innopolis.uni.course3.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
public class JPAHibernateUserRepositoryImpl implements UserRepository {

    private EntityManager em;
    private EntityManagerFactory emf;

    public JPAHibernateUserRepositoryImpl() {
    }

    public JPAHibernateUserRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email")
                .setParameter("email", user.getEmail());
        List resultList = query.getResultList();
        if(resultList.size() > 0) {
            transaction.rollback();
            em.close();
            throw new WrongProcessingOfUserException("Some problems with adding of user - duplicate email");
        } else {
            ru.innopolis.uni.course3.entity.User userEntity = UserMapper.INSTANCE.map(user);
            if (!em.contains(userEntity)) {
                em.persist(userEntity);
//                em.flush();
                user.setId(userEntity.getId());
            }
        }
        transaction.commit();
        em.close();
        return user;
    }

    @Override
    public User update(User user) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        ru.innopolis.uni.course3.entity.User userEntity = UserMapper.INSTANCE.map(user);
        em.merge(userEntity);
//        em.flush();
        transaction.commit();
        em.close();
        return user;
    }

    @Override
    public boolean delete(int id) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        boolean result = em.createQuery("DELETE FROM User u WHERE u.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        transaction.commit();
        em.close();
        return result;
    }

    @Override
    public User get(int id) {
        em = emf.createEntityManager();
        User user = UserMapper.INSTANCE.map(em.find(ru.innopolis.uni.course3.entity.User.class, id));
        em.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u  ORDER BY u.email");
        List<ru.innopolis.uni.course3.entity.User> resultLits = query.getResultList();
        List<User> users = resultLits.stream()
                .map(UserMapper.INSTANCE::map)
                .collect(Collectors.toList());
        em.close();
        return users;
    }

    @Override
    public User getByEmail(String email) {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email")
                .setParameter("email", email);
        List resultList = query.getResultList();
        if(resultList.size() == 0){
            return null;
        }
        ru.innopolis.uni.course3.entity.User userEntity =
                ((List<ru.innopolis.uni.course3.entity.User>) resultList).stream().findFirst().get();
        User user = UserMapper.INSTANCE.map(userEntity);
        em.close();
        return user;
    }

}
