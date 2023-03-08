package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.utils.RedisOperator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

public class BaseController {
    @Autowired
    RedisOperator redisOperator;

    public static final String FOODIE_SHOP_CART = "shopcart";
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";

    public UsersVO conventUsersVO(Users users) {
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(REDIS_USER_TOKEN + ":" + users.getId(), uniqueToken);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users, usersVO);
        System.out.println(usersVO);
        usersVO.setUniqueToken(uniqueToken);
        return usersVO;
    }

}
