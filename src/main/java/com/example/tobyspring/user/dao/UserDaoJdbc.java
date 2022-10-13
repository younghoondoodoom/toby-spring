package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.Level;
import com.example.tobyspring.user.domain.User;
import com.example.tobyspring.user.sqlService.SqlService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;
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

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    public void add(final User user) {
        jdbcTemplate.update(
                this.sqlService.getSql("userAdd"),
                user.getId(), user.getName(), user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(), user.getRecommend(), user.getEmail());
    }


    public void deleteAll() {
        jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject(
                this.sqlService.getSql("userGet"),
                new Object[]{id}, this.userMapper);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userGetAll"),
                this.userMapper);
    }

    public int getCount() {
        return this.jdbcTemplate.query(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(
                            Connection connection) throws SQLException {
                        return connection.prepareStatement(
                                sqlService.getSql("userGetCount"));
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
                this.sqlService.getSql("userUpdate"), user.getId(), user.getName(),
                user.getPassword(), user.getLevel().intValue(), user.getLogin(),
                user.getRecommend(), user.getEmail());
    }

}
