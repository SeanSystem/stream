package com.example.stream.lambda;

import com.example.stream.model.Fruit;
import java.util.HashSet;

/**
 * 测试hash
 *
 * @author Sean
 * @date 2020/03/19
 */
public class HashTest {

    /**
     * 1.不重写Fruit类的equals方法时，使用的是Object的默认方法，equals方法等价于 == ,比较的是两个类对象的内存地址，
     * 判断两个对象是不是同一个对象
     * <p>
     * 2.重写Fruit类的equals方法不重写hashcode方法时，equals方法比较两个对象的内容（属性值）是否相同，
     * 相同则返回true，但是hashcode可能并不相同，使用HashSet等hash散列表时不能达到去重的作用
     * <p>
     * 3.重写equals和HashSet时可以在使用HashSet等hash散列表时达到去重效果
     * <p>
     * 总结：使用HashSet等集合时如果重写了equals方法一定要重写HashCode方法
     */
    public static void testEqualsAndHash() {
        Fruit fruit = new Fruit(100.0);
        Fruit fruit1 = new Fruit(100.0);
        HashSet<Fruit> sets = new HashSet<>();
        sets.add(fruit);
        sets.add(fruit1);
        System.out.println(sets);
        System.out.println(fruit.equals(fruit1));
        System.out.println("f1(" + fruit.hashCode() + ")" + " " + "f2(" + fruit1.hashCode() + ")");
    }

    public static void main(String[] args) {
        testEqualsAndHash();
    }
}
