package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;

import java.util.List;

/**
 *
 */
public interface UserService {

    User add(User user) throws WrongProcessingOfUserException;

    User update(User user);

    void delete(int id) throws WrongProcessingOfUserException;

    User get(int id) throws WrongProcessingOfUserException;

    List<User> getAll();

    User getByEmail(String email) throws WrongProcessingOfUserException;
}
