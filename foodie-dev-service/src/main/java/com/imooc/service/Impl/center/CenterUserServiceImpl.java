package com.imooc.service.Impl.center;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CenterUserServiceImpl implements CenterUserService {
    @Autowired
    public UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String UserId) {
        Users users = usersMapper.selectByPrimaryKey(UserId);
        users.setPassword(null);
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {
        Users updateUsers = new Users();
        BeanUtils.copyProperties(centerUserBO, updateUsers);
        updateUsers.setId(userId);
        updateUsers.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(updateUsers);
        return queryUserInfo(userId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserFace(String userId, String faceUrl) {
        Users updateUsers = new Users();
        updateUsers.setId(userId);
        updateUsers.setFace(faceUrl);
        updateUsers.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(updateUsers);
        return queryUserInfo(userId);
    }
}
