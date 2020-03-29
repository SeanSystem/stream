package com.example.stream.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试CountDownLatch
 *
 * @author by Sean
 * @date 2020/3/29 16:19
 */
public class CountDownLatchTest {


    /**
     * 请求数量
     */
    private static final int threadCount = 550;

    public static void main(String[] args) throws InterruptedException {
        //创建一个固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    test(threadnum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }

}


