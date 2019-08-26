package com.example.stream.enums;

/**
 * 订单状态类
 *
 * @author Sean
 * 2019/07/13
 */
public enum OrderStatus {
    /**
     * 已发货状态
     */
    SEND(1, "已发货"),
    /**
     * 未发货状态
     */
    NOTSEND(-1, "未发货");

    private int status;
    private String desc;

    OrderStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public String getDesc(int status){
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.getStatus() == status){
                return orderStatus.getDesc();
            }
        }
        return "未知状态";
    }
}
