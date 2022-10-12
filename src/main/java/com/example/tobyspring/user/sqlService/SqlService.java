package com.example.tobyspring.user.sqlService;

import com.example.tobyspring.exception.SqlRetrievalFailureException;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
