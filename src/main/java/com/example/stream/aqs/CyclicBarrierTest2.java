package com.example.stream.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试包含barrierAction的CyclicBarrier
 *
 * @author by Sean
 * @date 2020/3/29 17:18
 */
public class CyclicBarrierTest2 {

    private static final int threadCount = 550;
    /**
     * 需要同步的线程数量
     */
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        System.out.println("到达屏障时优先执行该方法！");
    });

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
