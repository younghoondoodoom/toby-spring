package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt)
        throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makePreparedStatement(Connection c)
                    throws SQLException {
                    return c.prepareStatement(query);
                }
            });
    }

    public void preparedStatementSql(final String query,
        User... user) throws SQLException {
        workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makePreparedStatement(Connection c)
                    throws SQLException {
                    PreparedStatement ps = c.prepareStatement(query);
                    ps.setString(1, user[0].getId());
                    ps.setString(2, user[0].getName());
                    ps.setString(3, user[0].getPassword());

                    return ps;
                }
            });
    }
}
