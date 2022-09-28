package com.example.tobyspring.learningtest.template;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calculator;
    String numFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        calculator = new Calculator();
        numFilePath = "src/test/resources/numbers.txt";
    }

    @Test
    public void sumOfNumbers() throws IOException {
        //then
        assertThat(calculator.calcSum(numFilePath)).isEqualTo(10);
    }

    @Test
    public void multiplyOfNumbers() throws Exception {
        //then
        assertThat(calculator.calcMultiply(numFilePath)).isEqualTo(24);

    }

    @Test
    public void concatenateStrings() throws Exception {
        // then
        assertThat(calculator.concatenate(numFilePath)).isEqualTo("1234");
    }
}