package com.example.stream.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier测试
 *
 * @author by Sean
 * @date 2020/3/29 17:08
 */
public class CyclicBarrierTest {

    private static final int threadCount = 550;
    /**
     * 需要同步的线程数量
     */
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(()->{
                test(threadNum);
            });
        }
    }

    public static void test(int threadNum) {
        System.out.println("threadNum："+ threadNum +"is ready");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("threadNum："+ threadNum + "is finish");
    }
}
