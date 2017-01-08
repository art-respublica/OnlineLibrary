package ru.innopolis.uni.course3.repository;

import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.List;

/**
 *
 */
public interface UserRepository {

    User add(User user) throws WrongProcessingOfUserException;

    User update(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();

    // null if not found
    User getByEmail(String email);
}
