package com.imooc.enums;

public enum CateType {

    oneClass(1, "一类"),
    categoryII(2, "二类"),
    threeCategories(3, "三类");

    public final Integer type;
    public final String value;

    CateType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
