package ru.innopolis.uni.course3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.UserRepository;

import java.util.List;

/**
 *
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public UserServiceImpl() {
    }

    @Override
    public User add(User user) {
        return repository.add(user);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }
}
