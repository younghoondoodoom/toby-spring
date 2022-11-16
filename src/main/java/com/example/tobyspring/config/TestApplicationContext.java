package com.example.tobyspring.config;

import com.example.tobyspring.user.dao.UserDao;
import com.example.tobyspring.user.service.DummyMailSender;
import com.example.tobyspring.user.service.UserService;
import com.example.tobyspring.user.service.UserServiceImpl;
import javax.sql.DataSource;
import org.mariadb.jdbc.Driver;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example.tobyspring.user"})
public class TestApplicationContext {

    @Autowired
    UserDao userDao;

    @Bean
    public DataSource dataSource() {
        DefaultListableBeanFactory
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mariadb://localhost:3306/testdb");
        dataSource.setUsername("doodoom");
        dataSource.setPassword("dudgns2684");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
