package com.example.tobyspring.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filePath) throws IOException {
        return fileReaderTemplate(filePath,
            new BufferedReaderCallback() {
                @Override
                public Integer doSomeThingWithReader(
                    BufferedReader br) throws IOException {
                    int sum = 0;
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sum += Integer.parseInt(line);
                    }
                    return sum;
                }
            });
    }


    public Integer calcMultiply(String filePath) throws IOException {
        return fileReaderTemplate(filePath,
            new BufferedReaderCallback() {
                @Override
                public Integer doSomeThingWithReader(BufferedReader br)
                    throws IOException {
                    int multiply = 1;
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        multiply *= Integer.parseInt(line);
                    }
                    return multiply;
                }
            });
    }

    public Integer fileReaderTemplate(String filePath,
        BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(
                new FileReader(filePath));
            return callback.doSomeThingWithReader(br);
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

