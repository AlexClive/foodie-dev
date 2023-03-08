package com.imooc.pojo.vo;

import com.imooc.pojo.bo.ShopCatBO;

import java.util.List;

public class OrderVO {
    private String orderId;
    private List<ShopCatBO> toBeRemovedShopCatdList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ShopCatBO> getToBeRemovedShopCatdList() {
        return toBeRemovedShopCatdList;
    }

    public void setToBeRemovedShopCatdList(List<ShopCatBO> toBeRemovedShopCatdList) {
        this.toBeRemovedShopCatdList = toBeRemovedShopCatdList;
    }
}
