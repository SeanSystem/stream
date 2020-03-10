package com.example.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD

/**
 * @author Sean
 * @date 2020/03/02
=======
import java.util.Arrays;
import java.util.List;

/**
 * 菜单实体类
 *
 * @author Sean
 * 2020/03/07
>>>>>>> 80e47818810c2e66d51c92ff659fd2776a6f3487
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    private String name;

    private boolean vegetarian;

    private int calories;

    private Type type;

<<<<<<< HEAD
    public enum Type{MEAT, FISH, OTHER}
=======
    public enum Type {MEAT, FISH, OTHER}

    public enum CaloricLevel{DIET, NORMAL, FAT}

    public static List<Dish> getDishs(){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        return menu;
    }

>>>>>>> 80e47818810c2e66d51c92ff659fd2776a6f3487
}
