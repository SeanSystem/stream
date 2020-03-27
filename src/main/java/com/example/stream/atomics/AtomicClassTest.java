package com.example.stream.atomics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子类测试
 */
public class AtomicClassTest {

    public static void testAtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        int i = atomicInteger.get();
        System.out.println(i);
        boolean b = atomicInteger.compareAndSet(1, 2);
        System.out.println(atomicInteger.get() + "  " + b);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.get());
    }

    public static void testAtomicArray() {
        int[] arr = {1, 2, 3, 4, 5};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        int i = atomicIntegerArray.get(0);
        System.out.println(i);
        boolean b = atomicIntegerArray.compareAndSet(0, 1, 5);
        System.out.println(atomicIntegerArray + "" + b);

    }

    public static void main(String[] args) {
        //testAtomicInteger();
        testAtomicArray();
    }
}
