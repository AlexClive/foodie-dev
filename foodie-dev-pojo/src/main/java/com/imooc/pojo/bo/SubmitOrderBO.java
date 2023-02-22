package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

/**
 * y用于创建订单的bo对象
 */
public class SubmitOrderBO {
    @ApiModelProperty(value = "用户id",name="userId;",example = "2302178S06KDZWPH",required = true)
    private String userId;
    @ApiModelProperty(value = "商品规格id",name="itemSpecIds;",example = "7",required = true)
    private String itemSpecIds;
    @ApiModelProperty(value = "收货地址id",name="addressId;",example = "230221A7AR5R9F3C",required = true)
    private String addressId;
    @ApiModelProperty(value = "支付方式",name="payMethod;",example = "1",required = true)
    private Integer payMethod;
    @ApiModelProperty(value = "买家留言",name="leftMsg;",example = "",required = false)
    private String leftMsg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemSpecIds() {
        return itemSpecIds;
    }

    public void setItemSpecIds(String itemSpecIds) {
        this.itemSpecIds = itemSpecIds;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getLeftMsg() {
        return leftMsg;
    }

    public void setLeftMsg(String leftMsg) {
        this.leftMsg = leftMsg;
    }

    @Override
    public String toString() {
        return "SubmitOrderBO{" +
                "userId='" + userId + '\'' +
                ", itemSpecIds='" + itemSpecIds + '\'' +
                ", addressId='" + addressId + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", leftMsg='" + leftMsg + '\'' +
                '}';
    }
}
