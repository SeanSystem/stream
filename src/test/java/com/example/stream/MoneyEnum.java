package com.example.stream;

public enum MoneyEnum {
    YIAMO(0.1,"一毛"),
    WUMAO(0.5,"五毛"),
    YIYUAN(1.0,"一元"),
    WUYUAN(5.0,"五元"),
    SHIYUAN(10.0,"十元"),
    ERSHIYUAN(20.0,"二十元"),
    WUSHIYUAN(50.0,"五十元"),
    YIBAIYUAN(100.0,"一百元");

    private double value;
    private String desc;

    MoneyEnum(double value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescByValue(Double value){
        for (MoneyEnum moneyEnum : MoneyEnum.values()) {
            if(moneyEnum.getValue() == value){
                return moneyEnum.getDesc();
            }
        }
        return null;
    }
}
