package com.example.tobyspring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(getConnectionMaker());
    }

    @Bean
    private static DConnectionMaker getConnectionMaker() {
        return new DConnectionMaker();
    }

}
