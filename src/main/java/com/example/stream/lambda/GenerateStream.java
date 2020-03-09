package com.example.stream.lambda;

import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 生成stream流
 *
 * @author Sean
 * 2020/03/07
 */
public class GenerateStream {

    /**
     * 由值创建流
     */
    public static void byValue() {
        Stream<String> stream = Stream.of("Java 8", "Stream", "Lambda");
        stream.forEach(System.out::println);
        //创建一个空流
        Stream<String> emptyStream = Stream.empty();
    }

    /**
     * 通过数组创建流
     */
    public static void byArray() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    /**
     * 由文件生成流
     *
     * @throws IOException
     */
    public static void byFile() throws IOException {
        //获取resource目录下的文件
        ClassPathResource classPathResource = new ClassPathResource("files/test.txt");
        File file = classPathResource.getFile();
        String path = file.getPath();
        //Files.lines会返回一个由指定文件中的各行构成的字符串流
        try (Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset())) {
            long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过函数生成流：创建无限流
     */
    public static void byFunction() {
        //0为初值，n->n+2为值的生成规则
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * 生成斐波那契数列
     */
    public static void generaFb() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    /**
     * 通过Stream.generate生成斐波那契数列
     */
    public static void generaFbByGen(){
        IntStream generate = IntStream.generate(new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        });
        generate.limit(10).forEach(System.out::println);
    }


    public static void main(String[] args) throws IOException {
        //byValue();
        // byArray();
        //  byFile();
     // byFunction();
      //  generaFb();
        generaFbByGen();
    }
}
