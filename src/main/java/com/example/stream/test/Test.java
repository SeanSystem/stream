package com.example.stream.test;

import com.example.stream.enums.OrderStatus;
import com.example.stream.utils.RandomWords;
import java.util.Scanner;

/**
 * 测试类
 *
 * @author Sean
 * 2019/07/13
 */
public class Test {

    public static void main(String[] args) {
        int status = OrderStatus.SEND.getStatus();
        String desc = OrderStatus.SEND.getDesc(status);
        System.out.println(status+ " "+desc);
        Scanner scanner = new Scanner(new RandomWords(1));
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
