package com.imooc.service;

import com.imooc.pojo.UserAddress;

import java.util.List;

public interface UserAddressService {
    public List<UserAddress> queryAddressList(String userId);

    public UserAddress createAddress(UserAddress userAddress);
    public void editorAddress(String userId, String addressId);

}
