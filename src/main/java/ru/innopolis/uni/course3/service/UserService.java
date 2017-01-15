package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.List;

/**
 *  Contains a set of methods, through which can interact with business logic layer relating to the users
 */
public interface UserService {

    /**
     *  Adds the user into the database through repository(DAO) layer.
     *
     *  @param  user  added user
     *  @return User  user, which was added
     */
    User add(User user) throws WrongProcessingOfUserException;

    /**
     *  Modifies the user data (encoding password) and updates it through repository(DAO) layer.
     *
     *  @param  user  updated user
     *  @return User  user, which was updated
     */
    User update(User user);

    /**
     *  Deletes the user from the database through repository(DAO) layer.
     *
     *  @param  id  id of deleting user
     */
    void delete(int id) throws WrongProcessingOfUserException;

    /**
     *  Gets the user from the database by id through repository(DAO) layer.
     *
     *  @param  id      id, which uses for user search
     *  @return User    user, which was found or null if not found
     */
    User get(int id) throws WrongProcessingOfUserException;

    /**
     *  Gets the list of all users from the database through repository(DAO) layer.
     *
     *  @return List<User>  list of all users, which contained in the database
     */
    List<User> getAll();

    /**
     *  Gets the user from the database by email through repository(DAO) layer.
     *
     *  @param  email   email, which uses for user search
     *  @return User    user, which was found or null if not found
     */
    User getByEmail(String email);
}
