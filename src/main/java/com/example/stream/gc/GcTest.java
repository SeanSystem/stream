package com.example.stream.gc;

/**
 * gc回收器测试
 *
 * @author by Sean
 * @date 2020/3/30 20:13
 */
public class GcTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[58000 * 1024];
        allocation2 = new byte[900*1024];
    }


}
