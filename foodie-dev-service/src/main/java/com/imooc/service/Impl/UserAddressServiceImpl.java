package com.imooc.service.Impl;

import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAddressList(String userId) {
        Example addressExample = new Example(UserAddress.class);
        Example.Criteria addressCriteria = addressExample.createCriteria();
        addressCriteria.andEqualTo("userId", userId);
        List<UserAddress> list = userAddressMapper.selectByExample(addressExample);
        return list;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress createAddress(UserAddress userAddress) {
        userAddressMapper.insert(userAddress);
        return userAddress;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void editorAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddress.setIsDefault(1);
        userAddressMapper.updateByPrimaryKey(userAddress);
    }
}
