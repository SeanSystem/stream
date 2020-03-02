package com.example.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sean
 * @date 2020/03/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    private String name;

    private boolean vegetarian;

    private int calories;

    private Type type;

    public enum Type{MEAT, FISH, OTHER}
}
