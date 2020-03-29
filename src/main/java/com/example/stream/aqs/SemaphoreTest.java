package com.example.stream.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试Semaphore
 * Semaphore 经常用于限制获取某种资源的线程数量。
 */
public class SemaphoreTest {

    /**
     * 请求数量
     */
    private static final int threadCount = 550;

    public static void main(String[] args) {
        //创建一个固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        //一次允许执行线程的数量
        Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    //获取一个许可
                    semaphore.acquire(1);
                    test(threadnum);
                    //释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }

}
