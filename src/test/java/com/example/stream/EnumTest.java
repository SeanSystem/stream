package com.example.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {

    @Test
    public void testEnum(){
//        for (MoneyEnum m : MoneyEnum.values()) {
//            System.out.println(m+"->"+"ordinal:"+m.ordinal());
//        }
        String descByValue = MoneyEnum.getDescByValue(0.5);
        System.out.println(descByValue);
    }

/*    @Test
    public void testEnumForeach(){
        MoneyEnum moneyEnum = MoneyEnum.YIBAIYUAN;
        switch (moneyEnum){
            case WUMAO:
                System.out.println("五毛钱");
                break;
            case YIAMO:
                System.out.println("一毛钱");
                break;
            case WUYUAN:
                System.out.println("五元钱");
                break;
            case YIYUAN:
                System.out.println("一元钱");
                break;
            case SHIYUAN:
                System.out.println("十元钱");
                break;
            case ERSHIYUAN:
                System.out.println("二十元钱");
                break;
            case WUSHIYUAN:
                System.out.println("五十元钱");
                break;
            case YIBAIYUAN:
                System.out.println("一百元钱");
                break;
            default:
                System.out.println("不合法币值");
        }
    }*/
}
