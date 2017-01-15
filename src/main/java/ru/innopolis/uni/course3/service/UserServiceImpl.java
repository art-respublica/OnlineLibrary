package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.exception.ExceptionUtil;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.UserRepository;

import java.util.List;

/**
 *  Implements UserService methods
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository repository;

    private PasswordAuthentication authentication;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository repository, PasswordAuthentication authentication) {
        this.repository = repository;
        this.authentication = authentication;
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        if(user.getEmail() == null || user.getName() == null ||
            user.getEmail().isEmpty() || user.getName().isEmpty()) {
            throw new WrongProcessingOfUserException("Some problems with adding of user - empty email or name");
        }
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
    public void delete(int id) throws WrongProcessingOfUserException {
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
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }
}
