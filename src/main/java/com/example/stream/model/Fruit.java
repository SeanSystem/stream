package com.example.stream.model;

/**
 * 测试hash实体
 *
 * @author Sean
 * @date 2020/03/19
 */
public class Fruit {

    private Double price;

    public Fruit(){}

    public Fruit(Double price){
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null){
            return false;
        }

        if(this == obj){
            return true;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }
        Fruit fruit = (Fruit) obj;
        return price.equals(((Fruit) obj).getPrice());
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }
}
