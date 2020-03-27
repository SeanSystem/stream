package com.example.stream.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final long KEEP_ALIVE_TIME = 1L;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                new ArrayBlockingQueue<>(20),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(new MyRunnable("" + i));
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        System.out.println("all thread finished");
    }
}
