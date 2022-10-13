package com.example.tobyspring.learningtest.oxm;

import com.example.tobyspring.user.sqlService.jaxb.SqlType;
import com.example.tobyspring.user.sqlService.jaxb.Sqlmap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.transform.stream.StreamSource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(name = "/OxmTest-context.xml")
public class OxmTest {
    @Autowired
    Unmarshaller unmarshaller;

    @Test
    public void unmarshallSqlMap() throws Exception {
        StreamSource xmlSource =
                new StreamSource(getClass().getResourceAsStream("sqlmap.xml"));
        Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(xmlSource);

        List<SqlType> sqlList = sqlmap.getSql();
        Assertions.assertThat(sqlList.size()).isEqualTo(3);
        Assertions.assertThat(sqlList.get(0).getKey()).isEqualTo("add");
        Assertions.assertThat(sqlList.get(0).getValue()).isEqualTo("insert");
        Assertions.assertThat(sqlList.get(1).getKey()).isEqualTo("get");
        Assertions.assertThat(sqlList.get(1).getValue()).isEqualTo("select");
        Assertions.assertThat(sqlList.get(2).getKey()).isEqualTo("delete");
        Assertions.assertThat(sqlList.get(2).getValue()).isEqualTo("delete");

    }
}
