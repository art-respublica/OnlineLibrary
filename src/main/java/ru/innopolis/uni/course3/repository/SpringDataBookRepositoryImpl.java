package ru.innopolis.uni.course3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.uni.course3.mapper.BookMapper;
import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.repository.springdata.SpringDataBookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class SpringDataBookRepositoryImpl implements BookRepository{

    @Autowired
    private SpringDataBookRepository repository;

    public SpringDataBookRepositoryImpl() {
    }

    @Override
    public Book add(Book book) {
        return BookMapper.INSTANCE.map(repository.save(BookMapper.INSTANCE.map(book)));
    }

    @Override
    public Book update(Book book) {
        return BookMapper.INSTANCE.map(repository.save(BookMapper.INSTANCE.map(book)));
    }

    @Override
    public boolean delete(int id) {
        repository.delete(id);
        return true;
    }

    @Override
    public Book get(int id) {
        return BookMapper.INSTANCE.map(repository.findOne(id));
    }

    @Override
    public List<Book> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }
}
