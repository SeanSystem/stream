package com.example.stream.test;

import com.example.stream.model.Person;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * s
 *
 * @author Sean
 * @date 2019/5/16 0:56
 */
@NoArgsConstructor
@Builder
public class Child extends Base<Person> {

    public static void main(String[] args) {
        Child.builder().build();
    }
}
