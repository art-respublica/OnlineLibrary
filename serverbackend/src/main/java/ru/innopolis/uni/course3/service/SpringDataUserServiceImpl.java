package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.exception.ExceptionUtil;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.mapper.UserMapper;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.SpringDataUserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
@Service
public class SpringDataUserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(SpringDataUserServiceImpl.class);

    @Autowired
    private SpringDataUserRepository repository;

    @Autowired
    private PasswordAuthentication authentication;

    @Autowired
    private UserMapper mapper;

    public SpringDataUserServiceImpl() {
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        logger.info("Spring Data User service: adding user {}", user);
        if(user.getEmail() == null || user.getName() == null ||
                user.getEmail().isEmpty() || user.getName().isEmpty()) {
            throw new WrongProcessingOfUserException("Some problems with adding of user - empty email or name");
        }
        user.setSalt(authentication.getSalt());
        user.setPassword(authentication.generateStrongPasswordHash(user.getPassword(), user.getSalt()));
        return mapper.map(repository.save(mapper.map(user)));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User update(User user) {
        logger.info("Spring Data User service: updating user {}", user);
        User userWithSalt = mapper.map(repository.findOne(user.getId()));
        user.setSalt(userWithSalt.getSalt());
        user.setPassword(authentication.generateStrongPasswordHash(user.getPassword(), userWithSalt.getSalt()));
        return mapper.map(repository.save(mapper.map(user)));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws WrongProcessingOfUserException {
        logger.info("Spring Data User service: deleting user with id {}", id);
        repository.delete(id);
    }

    @Override
    public User get(int id) throws WrongProcessingOfUserException {
        logger.info("Spring Data User service: getting user with id {}", id);
        return ExceptionUtil.checkUserNotFoundWithId(mapper.map(repository.findOne(id)), id);
    }

    @Override
    public List<User> getAll() {
        logger.info("Spring Data User service: getting all users");
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        logger.info("Spring Data User service: getting user with email {}", email);
        User user = mapper.map(repository.findByEmail(email));
        return user;
    }
}
