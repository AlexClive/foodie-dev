package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    /**
     * 根据用户id查询用户的收获地址
     * @param userId 用户id
     * @return 用户收获地址list
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     * @param addressBO 收获地址object
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改地址
     * @param addressBO 收获地址object
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户id，地址id 删除地址数据
     * @param userId 用户id
     * @param addressId 地址id
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 用户修改默认收获地址
     * @param userId 用户id
     * @param addressId 地址id
     */
    void updateUseAddressToBeDefault(String userId,String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId 用户id
     * @param addressId 地址id
     * @return 用户地址信息
     */
    UserAddress queryUserAddress(String userId,String addressId);

}
