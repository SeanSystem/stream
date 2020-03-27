package com.example.stream.lambda;

import com.example.stream.model.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * stream规约和汇总
 *
 * @author Sean
 * 2020/03/07
 */
public class CollectorTest{

    public static List<Dish> dishs = Dish.getDishs();

    public static void testCount() {
        long count = dishs.stream().count();
        System.out.println(count);
    }

    public static void testMaxBy() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = dishs.stream().collect(maxBy(dishComparator));
        System.out.println(collect.get());
    }

    public static void testSummingInt() {
        Integer collect = dishs.stream().collect(summingInt(Dish::getCalories));
        System.out.println(collect);
    }

    public static void testAveragingInt() {
        Double collect = dishs.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(collect);
    }

    public static void testSummaringInt() {
        IntSummaryStatistics collect = dishs.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collect);
    }

    /**
     * joining 工厂方法返回的收集器会把对流中每一个对象应用 toString 方法得到的所有字符
     * 串连接成一个字符串
     * joining 在内部使用了 StringBuilder 来把生成的字符串逐个追加起来
     */
    public static void testJoining() {
        String collect = dishs.stream().map(Dish::getName).collect(joining(","));
        System.out.println(collect);
    }

    public static void testReducing() {
        //三个参数分别为 初始值 转换函数 累积函数
        Integer collect = dishs.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect);
    }

    public static void testGroupingBy() {
        Map<Dish.Type, List<Dish>> collect = dishs.stream().collect(groupingBy(Dish::getType));
        System.out.println(collect);
    }

    /**
     * 自定义分组函数
     */
    public static void testGroupingBy2() {
        Map<Dish.CaloricLevel, List<Dish>> collect = dishs.stream().collect(groupingBy(
                d -> {
                    if (d.getCalories() <= 400) {
                        return Dish.CaloricLevel.DIET;
                    } else if (d.getCalories() <= 700) {
                        return Dish.CaloricLevel.NORMAL;
                    } else {
                        return Dish.CaloricLevel.FAT;
                    }
                }));
        System.out.println(collect);
    }

    /**
     * 多级分组
     */
    public static void testGroupingBy3() {
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> collect = dishs.stream().collect(groupingBy(
                Dish::getType,
                groupingBy(d -> {
                    if (d.getCalories() <= 400) {
                        return Dish.CaloricLevel.DIET;
                    } else if (d.getCalories() <= 700) {
                        return Dish.CaloricLevel.NORMAL;
                    } else {
                        return Dish.CaloricLevel.FAT;
                    }
                })
        ));
        System.out.println(collect);
    }

    /**
     * 分组收集
     */
    public static void testGroupingBy4() {
        Map<Dish.Type, Optional<Dish>> collect = dishs.stream().collect(
                groupingBy(
                        Dish::getType,
                        maxBy(Comparator.comparing(Dish::getCalories))
                )
        );
        System.out.println(collect);
    }

    /**
     * 收集器转换类型
     */
    public static void testCollectingAndThen() {
        Map<Dish.Type, Dish> collect = dishs.stream().collect(
                groupingBy(
                        Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparing(Dish::getCalories))
                                , Optional::get)
                )
        );
        System.out.println(collect);
    }

    /**
     * 分组之后映射
     */
    public static void testMapping() {
        Map<Dish.Type, HashSet<Dish.CaloricLevel>> collect = dishs.stream().collect(
                groupingBy(
                        Dish::getType,
                        mapping(
                                d -> {
                                    if (d.getCalories() <= 400) {
                                        return Dish.CaloricLevel.DIET;
                                    } else if (d.getCalories() <= 700) {
                                        return Dish.CaloricLevel.NORMAL;
                                    } else {
                                        return Dish.CaloricLevel.FAT;
                                    }
                                }
                                , toCollection(HashSet::new))
                )
        );
        System.out.println(collect);
    }

    /**
     * 将数据分组两组
     */
    public static void testPartitioningBy() {
        Map<Boolean, List<Dish>> collect = dishs.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(collect);
    }

    /**
     * PartitioningBy多分组
     */
    public static void testPartitioningBy2() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect =
                dishs.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(collect);

    }

    /**
     * 判断是否是质数
     *
     * @param candidate
     * @return
     */
    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(item -> candidate % item == 0);
    }

    /**
     * 区分质数和非质数
     */
    public static void testPartitioningBy3() {
        int n = 100;
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n).boxed().collect(
                partitioningBy(i -> isPrime(i))
        );
        System.out.println(collect);
    }

    public static void main(String[] args) {
        //testCount();
        // testMaxBy();
        // testSummingInt();
        //testAveragingInt();
        // testSummaringInt();
        // testJoining();
        // testReducing();
        //testGroupingBy();
        // testGroupingBy2();
        // testGroupingBy3();
        //testGroupingBy4();
        //testCollectingAndThen();
        // testMapping();
        // testPartitioningBy();
        // testPartitioningBy2();
       // testPartitioningBy3();
    }

}
