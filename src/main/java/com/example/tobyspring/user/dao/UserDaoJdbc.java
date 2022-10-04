package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.Level;
import com.example.tobyspring.user.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper =
        new RowMapper<User>() {
            @Override
            public User
            mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLevel(Level.valueOf(rs.getInt("level")));
                user.setLogin(rs.getInt("login"));
                user.setRecommend(rs.getInt("recommend"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user) {
        jdbcTemplate.update(
            "insert into users(id, name, password, level, login, recommend, email) values (?,?,?,?,?,?,?)",
            user.getId(), user.getName(), user.getPassword(),
            user.getLevel().intValue(),
            user.getLogin(), user.getRecommend(), user.getEmail());
    }


    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject(
            "select * from users where id = ?",
            new Object[]{id}, this.userMapper);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",
            this.userMapper);
    }

    public int getCount() {
        return this.jdbcTemplate.query(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                    return connection.prepareStatement(
                        "select count(*) from users");
                }
            }, new ResultSetExtractor<Integer>() {
                @Override
                public Integer extractData(ResultSet resultSet)
                    throws SQLException, DataAccessException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            }
        );
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
            "update users set name = ?, password = ?, level = ?, login = ?, "
                + "recommend = ?, email = ? where id = ?", user.getName(),
            user.getPassword(), user.getLevel().intValue(), user.getLogin(),
            user.getRecommend(), user.getEmail(), user.getId());
    }

}
