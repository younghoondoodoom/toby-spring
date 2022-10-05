package com.example.tobyspring.learningtest.proxyFactoryBean;

import com.example.tobyspring.learningtest.proxy.Hello;
import com.example.tobyspring.learningtest.proxy.HelloTarget;
import com.example.tobyspring.learningtest.proxy.UpperCaseHandler;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class DynamicProxyTest {

    @Test
    public void simpleProxy() throws Exception {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
            getClass().getClassLoader(), new Class[]{Hello.class},
            new UpperCaseHandler(new HelloTarget())
        );


    }
}