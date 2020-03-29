package com.example.stream.atomics;

import com.example.stream.model.Apple;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.*;

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

    public static void testAtomicReference() {
        AtomicReference<Apple> atomicReference = new AtomicReference<>();
        Apple apple = new Apple(10);
        atomicReference.set(apple);
        System.out.println(atomicReference.get());
        Apple apple1 = new Apple(20);
        atomicReference.compareAndSet(apple, apple1);
        System.out.println(atomicReference.get());
    }

    public static void testAtomicStampedReference() {
        int initRef = 0, initStamp = 0;
        AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initRef, initStamp);
        System.out.println("currentValue:" + asr.getReference() + " currentStamp:" + asr.getStamp());
        //compare and set
        int newRef = 10, newStamp = 5;
        boolean b = asr.compareAndSet(initRef, newRef, initStamp, newStamp);
        System.out.println("currentValue:" + asr.getReference() + " currentStamp:" + asr.getStamp() + " result:" + b);
        //获取值
        int[] value = new int[1];
        Integer currentValue = asr.get(value);
        System.out.println("currentValue" + currentValue + " currentStamp:" + value[0]);
        //单独设置stamp值
        asr.attemptStamp(10, 20);
        System.out.println("currentValue:" + asr.getReference() + " currentStamp:" + asr.getStamp());
        //从新设置值
        asr.set(initRef, initStamp);
        System.out.println("currentValue:" + asr.getReference() + " currentStamp:" + asr.getStamp());
    }

    public static void testAtomicMarkableReference() {
        // 实例化、取当前值和 mark 值
        final Boolean initialRef = null, initialMark = false;
        final AtomicMarkableReference<Boolean> amr = new AtomicMarkableReference<>(initialRef, initialMark);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());

        // compare and set
        final Boolean newReference1 = true, newMark1 = true;
        final boolean casResult = amr.compareAndSet(initialRef, newReference1, initialMark, newMark1);
        System.out.println("currentValue=" + amr.getReference()
                + ", currentMark=" + amr.isMarked()
                + ", casResult=" + casResult);

        // 获取当前的值和当前的 mark 值
        boolean[] arr = new boolean[1];
        final Boolean currentValue = amr.get(arr);
        final boolean currentMark = arr[0];
        System.out.println("currentValue=" + currentValue + ", currentMark=" + currentMark);

        // 单独设置 mark 值
        final boolean attemptMarkResult = amr.attemptMark(newReference1, false);
        System.out.println("currentValue=" + amr.getReference()
                + ", currentMark=" + amr.isMarked()
                + ", attemptMarkResult=" + attemptMarkResult);

        // 重新设置当前值和 mark 值
        amr.set(initialRef, initialMark);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());

        // [不推荐使用，除非搞清楚注释的意思了] weak compare and set
        // 困惑！weakCompareAndSet 这个方法最终还是调用 compareAndSet 方法。[版本: jdk-8u191]
        // 但是注释上写着 "May fail spuriously and does not provide ordering guarantees,
        // so is only rarely an appropriate alternative to compareAndSet."
        // todo 感觉有可能是 jvm 通过方法名在 native 方法里面做了转发
        final boolean wCasResult = amr.weakCompareAndSet(initialRef, newReference1, initialMark, newMark1);
        System.out.println("currentValue=" + amr.getReference()
                + ", currentMark=" + amr.isMarked()
                + ", wCasResult=" + wCasResult);

    }

    /**
     * 只能修改由public 声明的int类型属性值
     */
    public static void testAtomicIntegerFieldUpdater() {
        AtomicIntegerFieldUpdater<Apple> aifu = AtomicIntegerFieldUpdater.newUpdater(Apple.class, "weight");
        Apple apple = new Apple(10);
        System.out.println(aifu.getAndIncrement(apple));
        System.out.println(aifu.get(apple));
    }

    public static void main(String[] args) {
        //testAtomicInteger();
        //testAtomicArray();
        //testAtomicReference();
        //testAtomicStampedReference();
        // testAtomicMarkableReference();
        testAtomicIntegerFieldUpdater();
    }
}
