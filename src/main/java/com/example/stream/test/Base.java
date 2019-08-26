package com.example.stream.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author Sean
 * @date 2019/5/16 0:56
 */
public class Base<T> {

    Class clazz;

    public Base() {
        Class<? extends Base> clz = this.getClass();
        Type genericSuperclass = clz.getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType)genericSuperclass;
            Type[] types = type.getActualTypeArguments();
            clazz = (Class) types[0];
        }
        System.out.println(clazz);
    }
}
