package com.example.stream.lambda;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流测试
 *
 * @author Sean
 * 2020/03/11
 */
public class ParallelStream {

    /**
     * 使用iterate需要自动装箱和拆箱操作，且无法分块操作
     *
     * @param n
     */
    public static void testByParallel(long n) {
        long start = System.currentTimeMillis();
        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("Iterate并行流结果：" + reduce + "耗时：" + (end - start));
    }

    /**
     * 使用LongStream避免了自动装箱和拆箱，且rangeColse可以分块
     *
     * @param n
     */
    public static void testByRangeClose(long n) {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("LongStream流结果：" + sum + "耗时：" + (end - start));
    }

    /**
     * 使用LongStream避免了自动装箱和拆箱，且rangeColse可以分块
     *
     * @param n
     */
    public static void testByParallel2(long n) {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("LongStream并行流结果：" + sum + "耗时：" + (end - start));
    }

    public static void testNormal(long n) {
        long start = System.currentTimeMillis();
        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("Tterate非并行流结果：" + reduce + "耗时：" + (end - start));
    }

    public static void testFor(long n) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("for结果：" + sum + "耗时：" + (end - start));
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        long n = 10000000;
       // testNormal(n);
        //testByParallel(n);
        //testByRangeClose(n);
        testFor(n);
        testByParallel2(n);
    }
}
