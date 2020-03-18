package com.example.stream.lambda;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 分支合并框架计算
 *
 * @author Sean
 * @date 2020/03/18
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;

    private final int start;

    private final int end;

    private static final long THRESHOLD = 10_000;

    private ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially(numbers);
        }
        ForkJoinSumCalculator leftArray = new ForkJoinSumCalculator(numbers, start, length / 2);
        leftArray.fork();
        ForkJoinSumCalculator rightArray = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightSum = rightArray.compute();
        Long leftSum = leftArray.join();
        return rightSum + leftSum;
    }

    private Long computeSequentially(long[] numbers) {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long forEach(long n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
       // long sum = forkJoinSum(100_000);
        long sum = forEach(100_000);
        long end = System.currentTimeMillis();
        System.out.println(sum+"耗时:"+(end - start));
    }
}
