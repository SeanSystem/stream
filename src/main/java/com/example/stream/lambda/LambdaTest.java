package com.example.stream.lambda;

import com.example.stream.model.Apple;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Comparator.comparing;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * lambda表达式应用
 *
 * @author Sean
 * @date 2020/02/25
 */
public class LambdaTest {

    public static void testFunctionalInterface(){
        Runnable r1 = () -> System.out.println("r1 run");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r2 run");
            }
        };
        process(r1);
        process(r2);
    }

    public static void testFunctionalInterface2() throws Exception{
        String s = processFile((BufferedReader br) -> br.readLine());
        System.out.println(s);
        String s1 = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(s1);
    }

    public static String processFile(BufferedReaderProcess bp) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\服务器.txt"))){
           return bp.process(br);
        }
    }

    public static <T>  List<T> filterList(List<T> list, Predicate<T> predicate){
        ArrayList<T> arr = new ArrayList<>();
        for(T s : list){
            if(predicate.test(s)){
                arr.add(s);
            }
        }
        return arr;
    }

    public static void testPredicate(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("adfdf");
        strings.add("sfs");
        strings.add("dag");
        Predicate<String> p = (String s) -> s.contains("a");
        List<String> strings1 = filterList(strings, p);
        System.out.println(strings1);
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer){
        for(T t : list){
            consumer.accept(t);
        }
    }

    public static void testConsumer(){
        List<String> list = Arrays.asList("1", "2", "3");
        forEach(list, (String s)-> System.out.println(s));
    }

    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(f.apply(t));
        }
        return result;
    }

    public static void testFunction(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("adfdf");
        strings.add("sfs");
        strings.add("dag");
        List<Integer> map = map(strings, (String s) -> s.length());
        System.out.println(map);
    }


    public static void process(Runnable runnable){
        runnable.run();
    }

    /**
     * 方法引用
     */
    public static void testMethodRefer(){
        List<String> list = Arrays.asList("a", "b", "A", "B");
        list.sort(String::compareToIgnoreCase);
        System.out.println(list);
    }

    public static List<Apple> createApple(List<Integer> list, Function<Integer, Apple> function){
        ArrayList<Apple> apples = new ArrayList<>();
        for(Integer i : list){
            apples.add(function.apply(i));
        }
        return apples;
    }

    public static List<Apple> testConstructorRefer(){
        Function<Integer, Apple> function = Apple::new;
        List<Integer> integers = Arrays.asList(3, 4, 2, 1);
        List<Apple> apple = createApple(integers, function);
        System.out.println(apple);
        return apple;
    }

    public static void testAndThen(){
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> result = f.andThen(g);
        Integer apply = result.apply(1);
        System.out.println(apply);
    }

    public static void testComprator(List<Apple> list){
        list.sort(comparing(Apple::getWeight));
        System.out.println(list);
    }

    public static void testConvert2IntStream(){
        List<Integer> integers = Arrays.asList(3, 4, 2, 1);
        int sum = integers.stream()
                .mapToInt(t -> t)
                .sum();
        System.out.println(sum);
        Stream<Integer> boxed = integers.stream()
                .mapToInt(t -> t)
                .boxed();
    }

    public static void testIntStreamRangeColse(){
        //包含结束位置数
        IntStream intStream = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(intStream.count());
    }

    public static void testIntStreamRange(){
        IntStream intStream = IntStream.range(1, 100)
                .filter(t -> t % 2 == 0);
        System.out.println(intStream.count());
    }

    /**
     * 生成勾股数
     */
    public static void gGs(){
        Stream<double[]> all = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );
        all.forEach(t->System.out.println((int)t[0]+","+(int)t[1]+","+(int)t[2]));
    }

    public static void main(String[] args) throws Exception {
        //testFunctionalInterface();
        //testFunctionalInterface2();
        //new InputStreamReader(new FileInputStream(""));
        //testPredicate();
        //testConsumer();
        //testFunction();
        //testMethodRefer();
        //List<Apple> apples = testConstructorRefer();
        //testComprator(apples);
       // testAndThen();
        //testConvert2IntStream();
       // testIntStreamRangeColse();
        //testIntStreamRange();
        gGs();
    }
}
