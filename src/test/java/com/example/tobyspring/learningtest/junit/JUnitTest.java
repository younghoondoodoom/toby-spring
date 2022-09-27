package com.example.tobyspring.learningtest.junit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class JUnitTest {

    @Autowired
    ApplicationContext context;
    static JUnitTest testObject;
    static ApplicationContext contextObject = null;

    @Test
    public void test1() throws Exception {
        assertThat(this).isNotEqualTo(testObject);
        testObject = this;

        assertThat(
            contextObject == null || contextObject == this.context).isEqualTo(
            true);
        contextObject = this.context;
    }

    @Test
    public void test2() throws Exception {
        assertThat(this).isNotEqualTo(testObject);
        testObject = this;

        assertThat(
            contextObject == null || contextObject == this.context).isEqualTo(
            true);
        contextObject = this.context;
    }

    @Test
    public void test3() throws Exception {
        assertThat(this).isNotEqualTo(testObject);
        testObject = this;

        assertThat(
            contextObject == null || contextObject == this.context).isEqualTo(
            true);
        contextObject = this.context;
    }
}
