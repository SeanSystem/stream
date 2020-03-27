package com.example.stream.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cas ABA问题
 */
public class CasABA {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void testABA() {
        Thread coreThread = new Thread(() -> {
            int currentValue = atomicInteger.get();
            System.out.println(Thread.currentThread().getName() + " currentValue:" + currentValue);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName() + " expect：" + currentValue +
                    " update:" + atomicInteger.get() +
                    " result:" + result);
        });
        coreThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread other = new Thread(() -> {
            int curr = atomicInteger.get();
            boolean b = atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName() + " except：" + curr +
                    " update:" + atomicInteger.get() +
                    " result:" + b);
            int curr2 = atomicInteger.get();
            boolean b2 = atomicInteger.compareAndSet(2, 1);
            System.out.println(Thread.currentThread().getName() + " except：" + curr2 +
                    " update:" + atomicInteger.get() +
                    " result:" + b2);
        });
        other.start();
    }

    public static void main(String[] args) {
        testABA();
    }
}
