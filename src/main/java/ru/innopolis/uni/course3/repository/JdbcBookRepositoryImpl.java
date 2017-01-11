package ru.innopolis.uni.course3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.model.Book;

import javax.sql.DataSource;
import java.util.List;

/**
 *  Implements BookRepository methods for the jdbc connection
 */
@Component
public class JdbcBookRepositoryImpl implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcBookRepositoryImpl.class);

    private static final BeanPropertyRowMapper<Book> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Book.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcBookRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("books")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Book add(Book book) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("author", book.getAuthor())
                .addValue("title", book.getTitle())
                .addValue("year", book.getYear())
                .addValue("text", book.getText());
        Number newKey = insertUser.executeAndReturnKey(map);
        book.setId(newKey.intValue());
        return book;
    }

    @Override
    public Book update(Book book) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("author", book.getAuthor())
                .addValue("title", book.getTitle())
                .addValue("year", book.getYear())
                .addValue("text", book.getText());
        namedParameterJdbcTemplate.update(
                "UPDATE books SET author=:author, title=:title, year=:year, text=:text WHERE id=:id", map);
        return book;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM books WHERE id=?", id) != 0;
    }

    @Override
    public Book get(int id) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(books);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY author, title", ROW_MAPPER);
    }
}
