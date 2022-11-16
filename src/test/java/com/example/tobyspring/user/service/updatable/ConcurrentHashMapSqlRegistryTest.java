package com.example.tobyspring.user.service.updatable;

import com.example.tobyspring.exception.SqlNotFoundException;
import com.example.tobyspring.exception.SqlUpdateFailureException;
import com.example.tobyspring.user.sqlService.UpdatableSqlRegistry;
import com.example.tobyspring.user.sqlService.updatable.ConcurrentHashMapSqlRegistry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConcurrentHashMapSqlRegistryTest {
    UpdatableSqlRegistry sqlRegistry;

    @BeforeEach
    public void setUp() {
        sqlRegistry = new ConcurrentHashMapSqlRegistry();
        sqlRegistry.registerSql("KEY1", "SQL1");
        sqlRegistry.registerSql("KEY2", "SQL2");
        sqlRegistry.registerSql("KEY3", "SQL3");
    }

    @Test
    public void find() throws Exception {
        checkFindResult("SQL1", "SQL2", "SQL3");
    }

    private void checkFindResult(String expected1, String expected2,
                                 String expected3) {
        assertThat(sqlRegistry.findSql("KEY1")).isEqualTo(expected1);
        assertThat(sqlRegistry.findSql("KEY2")).isEqualTo(expected2);
        assertThat(sqlRegistry.findSql("KEY3")).isEqualTo(expected3);
    }

    @Test
    public void unknownKey() throws Exception {
        assertThrows(SqlNotFoundException.class, () -> sqlRegistry.findSql(
                "SQL9999!@#$"));
    }

    @Test
    public void updateSingle() throws Exception {
        sqlRegistry.updateSql("KEY2", "Modified2");
        checkFindResult("SQL1", "Modified2", "SQL3");
    }

    @Test
    public void updateMulti() throws Exception {
        HashMap<String, String> sqlmap = new HashMap<>();
        sqlmap.put("KEY1", "Modified1");
        sqlmap.put("KEY3", "Modified3");

        sqlRegistry.updateSql(sqlmap);
        checkFindResult("Modified1", "SQL2", "Modified3");
    }

    @Test
    public void updateWithNotExistingKey() throws Exception {
        assertThrows(SqlUpdateFailureException.class, () ->
                sqlRegistry.updateSql("SQL9999!@#$", "Modified2"));

    }

}
