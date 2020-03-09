package com.example.stream.lambda;

import com.example.stream.model.Trader;
import com.example.stream.model.Transaction;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sean
 * 2020/03/03
 */
public class Answes {

    public static List<Transaction> getList() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(brian, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }

    public static void a1() {
        List<Transaction> list = getList();
       list.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
    }

    public static void a2(){
        List<Transaction> list = getList();
        list.stream()
                .map(t->t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    public static void a3(){
        List<Transaction> list = getList();
        list.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getTrader())
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    public static void a4(){
        List<Transaction> list = getList();
        Optional<String> reduce = list.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((t1, t2) -> t1 + t2);
        System.out.println(reduce.get());
    }

    public static void a41(){
        List<Transaction> list = getList();
        String collect = list.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .collect(Collectors.joining());
        System.out.println(collect);
    }

    public static void a5(){
        List<Transaction> list = getList();
        boolean milan = list.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
    }

    public static void a6(){
        List<Transaction> list = getList();
        Optional<Integer> cambridge = list.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(cambridge.get());
    }

    public static void a7(){
        List<Transaction> list = getList();
        Optional<Integer> reduce = list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(reduce.get());
    }

    public static void a8(){
        List<Transaction> list = getList();
        Optional<Integer> reduce = list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(reduce.get());
    }

    public static void main(String[] args) {
      //  a1();
      //  a2();
      //  a3();
      //  a4();
        a41();
      //  a5();
       // a6();
        //a7();
       // a8();
    }
}
