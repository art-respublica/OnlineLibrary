package ru.innopolis.uni.course3.repository;

import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.List;

/**
 *
 */
public interface UserRepository {

    /**
     *  Adds the user into the database
     *
     *  @param  user  added user
     *  @return User  user, which was added
     */
    User add(User user) throws WrongProcessingOfUserException;

    /**
     *  Updates the user data in the database
     *
     *  @param  user  updated user
     *  @return User  user, which was updated
     */
    User update(User user);

    /**
     *  Deletes the user in the database
     *
     *  @param  id  id of deleting user
     *  @return boolean  true - if deleting was successful, false - otherwise
     */
    boolean delete(int id);

    /**
     *  Gets the user from the database by id
     *
     *  @param  id      id, which uses for user search
     *  @return User    user, which was found or null if not found
     */
    User get(int id);

    /**
     *  Gets the list of all users from the database
     *  @return List<User>    list of all users, which contained in the database
     */
    List<User> getAll();

    /**
     *  Gets the user from the database by email
     *
     *  @param  email   id, which uses for user search
     *  @return User    user, which was found or null if not found
     */
    User getByEmail(String email);
}
