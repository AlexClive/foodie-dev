package com.imooc.enums;

public enum OderStatuEnum {
    WAII_PAY(10,"待付款"),
    WAII_DELEVER(20,"已付款，待发货"),
    WAII_RECEIVE(30,"已发货，待收货"),
    SUCCESS(40,"待付款"),
    CLOSE(50,"交易关闭");

    public final Integer type;
    public final String value;
    OderStatuEnum(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
