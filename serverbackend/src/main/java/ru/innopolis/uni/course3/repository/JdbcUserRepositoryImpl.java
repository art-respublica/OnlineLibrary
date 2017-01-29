package ru.innopolis.uni.course3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
//@Component
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUserRepositoryImpl.class);

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private static final RowMapper<String> ROLE_ROW_MAPPER = new RowMapper<String>() {
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new String(rs.getString("role"));
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    private SimpleJdbcInsert insertRoles;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.insertRoles = new SimpleJdbcInsert(dataSource)
                .withTableName("user_roles");
    }

    @Override
    public User add(User user) throws WrongProcessingOfUserException {
        try {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("id", user.getId())
                    .addValue("name", user.getName())
                    .addValue("email", user.getEmail())
                    .addValue("password", user.getPassword())
                    .addValue("salt", user.getSalt())
                    .addValue("registered", user.getRegistered())
                    .addValue("enabled", user.isEnabled());
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
            insertRoles(user);
            return user;
        } catch (Exception exception) {
            throw new WrongProcessingOfUserException("Some problems with adding of user - duplicate email");
        }
    }

    @Override
    public User update(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());
        namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:name, email=:email, password=:password, enabled=:enabled WHERE id=:id", map);
        deleteRoles(user.getId());
        insertRoles(user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        deleteRoles(id);
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        User user = DataAccessUtils.singleResult(users);

        if(user != null) {
            List<String> rolesFromDB = jdbcTemplate.query("SELECT r.role FROM user_roles r WHERE user_id=?", ROLE_ROW_MAPPER, id);
            Set<Role> roles = rolesFromDB.stream()
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users u WHERE email=?", ROW_MAPPER, email);
        User user = DataAccessUtils.singleResult(users);

        if(user != null) {
            List<String> rolesFromDB = jdbcTemplate.query("SELECT r.role FROM user_roles r WHERE user_id IN (SELECT u.id FROM users u WHERE u.email=?)", ROLE_ROW_MAPPER, email);
            Set<Role> roles = rolesFromDB.stream()
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        return user;
    }

    private void insertRoles(User user) {
        if(user.getRoles().contains(Role.ROLE_ADMIN)) {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("user_id", user.getId())
                    .addValue("email", user.getEmail())
                    .addValue("role", "ROLE_ADMIN");
            insertRoles.execute(map);
            map = new MapSqlParameterSource()
                    .addValue("user_id", user.getId())
                    .addValue("email", user.getEmail())
                    .addValue("role", "ROLE_USER");
            insertRoles.execute(map);
        } else if (user.getRoles().contains(Role.ROLE_USER)) {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("user_id", user.getId())
                    .addValue("email", user.getEmail())
                    .addValue("role", "ROLE_USER");
            insertRoles.execute(map);
        }
    }

    private void deleteRoles(int id) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", id);
    }

}

