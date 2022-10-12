package com.example.tobyspring.user.sqlService;

import com.example.tobyspring.exception.SqlNotFoundException;

public interface SqlRegistry {
    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFoundException;
}
