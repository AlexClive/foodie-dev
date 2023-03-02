package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

public interface CenterUserService {

    /**
     * @param UserId 用户id
     * @return 用户信息
     */
    Users queryUserInfo(String UserId);

    /**
     *  修改用户数据
     * @param userId 用户id
     * @param centerUserBO 修改用户实体类
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     *
     * @param userId 用户id
     * @param faceUrl 头像地址
     * @return 新的用户数据
     */
    Users updateUserFace(String userId, String faceUrl);




}
