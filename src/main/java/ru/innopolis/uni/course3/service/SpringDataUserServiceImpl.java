package ru.innopolis.uni.course3.service;

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
        User userWithSalt = mapper.map(repository.findOne(user.getId()));
        user.setSalt(userWithSalt.getSalt());
        user.setPassword(authentication.generateStrongPasswordHash(user.getPassword(), userWithSalt.getSalt()));
        return mapper.map(repository.save(mapper.map(user)));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws WrongProcessingOfUserException {
        repository.delete(id);
    }

    @Override
    public User get(int id) throws WrongProcessingOfUserException {
        return ExceptionUtil.checkUserNotFoundWithId(mapper.map(repository.findOne(id)), id);
    }

    @Override
    public List<User> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return mapper.map(repository.findByEmail(email));
    }
}
