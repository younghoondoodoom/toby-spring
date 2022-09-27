package com.example.tobyspring.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {

    Integer doSomeThingWithReader(BufferedReader bufferedReader)
        throws IOException;
}
