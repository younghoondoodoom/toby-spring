package com.example.tobyspring.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filePath) throws IOException {
        LineCallBack<Integer> sumCallBack =
            new LineCallBack<Integer>() {
                @Override
                public Integer doSomethingWithLine(String line, Integer value) {
                    return value + Integer.parseInt(line);
                }
            };
        return lineReadTemplate(filePath, sumCallBack, 0);
    }


    public Integer calcMultiply(String filePath) throws IOException {
        LineCallBack<Integer> multiplyCallBack =
            new LineCallBack<Integer>() {
                @Override
                public Integer doSomethingWithLine(String line, Integer value) {
                    return value * Integer.parseInt(line);
                }
            };
        return lineReadTemplate(filePath, multiplyCallBack, 1);
    }

    public String concatenate(String filePath) throws IOException {
        LineCallBack<String> concatenateCallBack =
            new LineCallBack<String>() {
                @Override
                public String doSomethingWithLine(String line, String value) {
                    return value + line;
                }
            };
        return lineReadTemplate(filePath, concatenateCallBack, "");
    }

    public <T> T lineReadTemplate(String filePath, LineCallBack<T> callBack,
        T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            T res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callBack.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

