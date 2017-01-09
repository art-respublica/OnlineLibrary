package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.exception.ExceptionUtil;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.UserRepository;

import java.util.List;

/**
 *
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordAuthentication authentication;

    public UserServiceImpl() {
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        user.setSalt(authentication.getSalt());
        user.setPassword(authentication.generateStrongPasswordHash(user.getPassword(), user.getSalt()));
        return repository.add(user);
    }

    @Override
    public User update(User user) {
        User userSalt = repository.get(user.getId());
        user.setPassword(authentication.generateStrongPasswordHash(user.getPassword(), userSalt.getSalt()));
        return repository.update(user);
    }

    @Override
    public void delete(int id) throws WrongProcessingOfUserException{
        ExceptionUtil.checkUserNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws WrongProcessingOfUserException {
        return ExceptionUtil.checkUserNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getByEmail(String email) throws WrongProcessingOfUserException{
        return ExceptionUtil.checkUserNotFound(repository.getByEmail(email), "email=" + email);
    }
}
