package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于新增或修改地址的BO
 */
public class AddressBO {
    private String addressId;
    @ApiModelProperty(value = "用户id",name="userId",example = "2302178S06KDZWPH",required = true)
    private String userId;
    @ApiModelProperty(value = "姓名",name="receiver",example = "imooc",required = true)
    private String receiver;
    @ApiModelProperty(value = "用户手机",name="mobile",example = "13000000000",required = true)
    private String mobile;
    @ApiModelProperty(value = "省",name="province",example = "北京",required = true)
    private String province;
    @ApiModelProperty(value = "市",name="city",example = "北京",required = true)
    private String city;
    @ApiModelProperty(value = "区",name="district",example = "西城区",required = true)
    private String district;
    @ApiModelProperty(value = "详细地址",name="detail",example = "西城区xxxx小区xxx栋xxx单元xxx",required = true)
    private String detail;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
