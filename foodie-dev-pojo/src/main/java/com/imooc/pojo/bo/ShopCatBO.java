package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

public class ShopCatBO {
    @ApiModelProperty(value = "商品图片",name="itemImgUrl",example = "imooc",required = true)
    private String itemImgUrl;
    @ApiModelProperty(value = "商品名称",name="itemName",example = "imooc",required = true)
    private String itemName;
    @ApiModelProperty(value = "规格id",name="specId",example = "1",required = true)
    private String specId;
    @ApiModelProperty(value = "规格名称",name="specName",example = "imooc",required = true)
    private String specName;
    @ApiModelProperty(value = "购买计数",name="buyCounts",example = "1",required = true)
    private Integer buyCounts;
    @ApiModelProperty(value = "价格折扣",name="priceDiscount",example = "100",required = true)
    private String priceDiscount;
    @ApiModelProperty(value = "价格正常",name="priceNormal",example = "150",required = true)
    private String priceNormal;

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(String priceNormal) {
        this.priceNormal = priceNormal;
    }

    @Override
    public String toString() {
        return "shopCatBo{" +
                "itemImgUrl='" + itemImgUrl + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specId='" + specId + '\'' +
                ", specName='" + specName + '\'' +
                ", buyCounts=" + buyCounts +
                ", priceDiscount='" + priceDiscount + '\'' +
                ", priceNormal='" + priceNormal + '\'' +
                '}';
    }
}
