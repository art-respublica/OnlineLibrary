package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.model.User;

import java.util.List;

/**
 *
 */
public interface UserService {

    User add(User user);

    User update(User user);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);
}
