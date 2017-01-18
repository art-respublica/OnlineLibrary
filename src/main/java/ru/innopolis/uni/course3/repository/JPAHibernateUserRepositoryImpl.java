package ru.innopolis.uni.course3.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
public class JPAHibernateUserRepositoryImpl implements UserRepository {

    private EntityManager em;

    public JPAHibernateUserRepositoryImpl() {
    }

    public JPAHibernateUserRepositoryImpl(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        em.getTransaction().begin();
        ru.innopolis.uni.course3.entity.User userEntity = convertUserToUserEntity(user, false);
        try {
            if (!em.contains(userEntity)) {
                em.persist(userEntity);
                em.flush();
            }
        } catch (Exception exception){
            throw new WrongProcessingOfUserException("Some problems with adding of user - duplicate email");
        }
        user.setId(userEntity.getId());
        em.getTransaction().commit();
        return user;
    }

    @Override
    public User update(User user) {
        em.getTransaction().begin();
        ru.innopolis.uni.course3.entity.User userEntity = convertUserToUserEntity(user, true);
        em.merge(userEntity);
        em.flush();
        em.getTransaction().commit();
        return user;
    }

    @Override
    public boolean delete(int id) {
        em.getTransaction().begin();
        boolean result = em.createQuery("DELETE FROM User u WHERE u.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        em.flush();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public User get(int id) {
        return convertUserEntiryToUser(em.find(ru.innopolis.uni.course3.entity.User.class, id));
    }

    @Override
    public List<User> getAll() {
        Query query = em.createQuery("SELECT u FROM User u  ORDER BY u.email");
        List<ru.innopolis.uni.course3.entity.User> resultLits = query.getResultList();
        List<User> users = resultLits.stream()
                .map(JPAHibernateUserRepositoryImpl::convertUserEntiryToUser)
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public User getByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email")
                .setParameter("email", email);
        List resultList = query.getResultList();
        if(resultList.size() == 0){
            return null;
        }
        ru.innopolis.uni.course3.entity.User user =
                ((List<ru.innopolis.uni.course3.entity.User>) resultList).stream().findFirst().get();
        return convertUserEntiryToUser(user);
    }

    private static User convertUserEntiryToUser(ru.innopolis.uni.course3.entity.User u) {
        if(u == null) {
            return null;
        }
        User returnUser = new User(u.getId(), u.getName(), u.getEmail(), u.getPassword(),
                u.getRegistered(), u.isEnabled(), u.getRoles());
        returnUser.setSalt(u.getSalt());
        return returnUser;
    }

    private static ru.innopolis.uni.course3.entity.User convertUserToUserEntity(User user, boolean hasId) {
        ru.innopolis.uni.course3.entity.User userEntity = new ru.innopolis.uni.course3.entity.User();
        if(user == null) {
            return userEntity;
        }
        if(hasId) {
            userEntity.setId(user.getId());
        }
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setSalt(user.getSalt());
        userEntity.setRegistered(user.getRegistered());
        userEntity.setEnabled(user.isEnabled());
        userEntity.setRoles(user.getRoles());
        return userEntity;
    }

}
