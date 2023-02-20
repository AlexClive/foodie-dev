package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

public class shopCatBo {
    @ApiModelProperty(value = "商品图片地址",name="itemImgUrl",example = "imooc",required = true)
    private String itemImgUrl;
    @ApiModelProperty(value = "商品名称",name="itemName",example = "imooc",required = true)
    private String itemName;
    @ApiModelProperty(value = "商品id",name="specId",example = "imooc",required = true)
    private String specId;
    @ApiModelProperty(value = "specName",name="specName",example = "imooc",required = true)
    private String specName;
    @ApiModelProperty(value = "商品id",name="buyCounts",example = "1",required = true)
    private Integer buyCounts;
    @ApiModelProperty(value = "priceDiscount",name="priceDiscount",example = "priceDiscount",required = true)
    private String priceDiscount;
    @ApiModelProperty(value = "priceNormal",name="priceNormal",example = "imooc",required = true)
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
