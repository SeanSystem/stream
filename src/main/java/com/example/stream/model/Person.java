package com.example.stream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person class
 * @author Sean
 * @date 2019/5/17 22:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String name;

    private Integer age;

    private String sex;
}
