package com.example.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApplicationTests {
    List<Person> people = new ArrayList<>();

    @Before
    public void contextLoads() {
        people.add(new Person("sean",1,12));
        people.add(new Person("jhon",2,13));
        people.add(new Person("marry",3,13));
        people.add(new Person("born",4,14));
        people.add(new Person("egd",1,12));
    }

    @Test
    public void testStream(){
        //创建strem串行流的方法
        Stream<Person> stream = people.stream();
        //创建paralleStream并行流的方法
        Stream<Person> personStream = people.parallelStream();
    }

    @Test
    public void testForeach(){
        //串行流输出
       // people.stream().forEach(System.out::println);
        //并行流输出
        //people.parallelStream().forEach(System.out::println);
        //使用并行流顺序执行
       // people.parallelStream().forEachOrdered(System.out::println);
        //遍历输出某个值
        people.stream().forEach(x->System.out.println(x.getAge()));
        //获取10个0-100之间的随机数
        Random random = new Random();
        random.ints(0,100).limit(10).forEach(System.out::println);
    }

    @Test
    public void testMap(){
        //map映射每个元素到对应的结果集  类似于数学中的函数 map中指定想要映射的结果集表达式
       // people.stream().map(Person::getName).collect(Collectors.toList()).forEach(System.out::println);
       // people.stream().map(Person::getAge).collect(Collectors.toList()).forEach(System.out::println);
       // people.stream().map(Person::getClasses).collect(Collectors.toList()).forEach(System.out::println);
      //  people.stream().map(x->x.getAge() > 12).collect(Collectors.toList()).forEach(System.out::println);
        people.stream().map(x->x.getAge()+1).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void testFilter(){
       // people.stream().filter(x->x.getAge()>13).collect(Collectors.toList()).forEach(System.out::println);
        people.stream().filter(x->x.getClasses() != 1).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
     public void testSorted(){
        Random random = new Random();
        //random.ints(0,100).limit(5).sorted().forEach(System.out::println);
      //  people.stream().sorted((x,y)->x.getAge()-y.getAge()).forEach(System.out::println);
       // people.stream().sorted((x,y)->y.getAge()-x.getAge()).forEach(System.out::println);
       // people.stream().sorted((x,y)->x.getAge().compareTo(y.getAge())).forEach(System.out::println);
          people.stream().sorted((x,y)->y.getAge().compareTo(x.getAge())).forEach(System.out::println);
    }

    @Test
    public void testMapTo(){
        people.stream().mapToInt(Person::getAge).forEach(System.out::println);
        people.stream().mapToDouble(Person::getAge).forEach(System.out::println);
        people.stream().mapToLong(Person::getAge).forEach(System.out::println);
    }

    @Test
    public void testMatch(){
        boolean b = people.stream().allMatch(x -> x.getAge() > 13);
        boolean b1 = people.stream().anyMatch(x -> x.getAge() > 13);
        System.out.println(b1);
    }

    @Test
    public void testReduce(){
        Integer age = people.stream().map(Person::getAge).reduce(0, (sum, y) -> sum + y);
        System.out.println(age);
        Integer reduce = people.stream().map(Person::getAge).reduce(0, (x, y) -> x + y, (x, y) -> x * y);
        System.out.println(reduce);
        Integer reduce1 = people.stream().map(Person::getAge).parallel().reduce(0, (x, y) -> x + y, (x, y) -> x * y);
        System.out.println(reduce1);
    }

    @Test
    public void testFind(){
        Person person = people.stream().findFirst().get();
        Person person1 = people.stream().findAny().get();
        System.out.println(person);
        System.out.println(person1);
    }

    class Person{

        private String name;

        private Integer classes;

        private Integer age;

        public Person() {
        }

        public Person(String name, Integer classes, Integer age) {
            this.name = name;
            this.classes = classes;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getClasses() {
            return classes;
        }

        public void setClasses(Integer classes) {
            this.classes = classes;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", classes=" + classes +
                    ", age=" + age +
                    '}';
        }
    }
}
