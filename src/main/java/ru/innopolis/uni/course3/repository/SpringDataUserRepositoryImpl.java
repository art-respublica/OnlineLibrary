package ru.innopolis.uni.course3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.mapper.BookMapper;
import ru.innopolis.uni.course3.mapper.UserMapper;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.repository.springdata.SpringDataUserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class SpringDataUserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringDataUserRepository repository;

    public SpringDataUserRepositoryImpl() {
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        return UserMapper.INSTANCE.map(repository.save(UserMapper.INSTANCE.map(user)));
    }

    @Override
    public User update(User user) {
        return UserMapper.INSTANCE.map(repository.save(UserMapper.INSTANCE.map(user)));
    }

    @Override
    public boolean delete(int id) {
        repository.delete(id);
        return true;
    }

    @Override
    public User get(int id) {
        return UserMapper.INSTANCE.map(repository.findOne(id));
    }

    @Override
    public List<User> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(UserMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return UserMapper.INSTANCE.map(repository.findByEmail(email));
    }
}
