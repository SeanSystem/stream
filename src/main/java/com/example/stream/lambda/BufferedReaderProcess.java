package com.example.stream.lambda;

import java.io.BufferedReader;

@FunctionalInterface
public interface BufferedReaderProcess {

    String process(BufferedReader bufferedReader) throws Exception;
}
