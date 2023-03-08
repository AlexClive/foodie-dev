package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.ShopCatBO;
import com.imooc.pojo.bo.UserBO;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UserService;
import com.imooc.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(value = "注册登录", tags = {"用户注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        // 判读用户名不能为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        // 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        // 请求成功
        return IMOOCJSONResult.ok();

    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO
            , HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 1、判断用户名密码不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        // 2、查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        // 3、密码长度不能少于6位

        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能小于6");
        }
        // 4、判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        // 5、 实现注册
        Users users = userService.createUser(userBO);
        //users = setNullProperty(users);

        // 生成用户token，存入redis会话
        // 实现用户的redis会话
        UsersVO usersVO = conventUsersVO(users);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(usersVO), true);
        // 同步购物车数据
        synchShipCartData(usersVO.getId(), request, response);
        return IMOOCJSONResult.ok(usersVO);

    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 1、判断用户名密码不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }


        // 2、 实现登录
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }
        userResult = setNullProperty(userResult);
        // 生成用户token，存入redis会话
        UsersVO usersVO = conventUsersVO(userResult);
        // 设置cookie
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(usersVO), true);

        // 同步购物车数据
        synchShipCartData(usersVO.getId(), request, response);
        return IMOOCJSONResult.ok(usersVO);
    }

    private void synchShipCartData(String userId, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 1.redis 中无数据 如果cookie为空，不做处理
         *                   如果cookie不为空 直接放入redis
         * 2、redis中有数据 如果cookie为空，redis数据直接放入cookie
         */
        String shopCartJsonRedis = redisOperator.get(FOODIE_SHOP_CART + ":" + userId);
        String shopCarStrCookie = CookieUtils.getCookieValue(request, FOODIE_SHOP_CART, true);

        if (StringUtils.isBlank(shopCartJsonRedis)) {
            // redis为空 cookie不为空
            if (StringUtils.isNotBlank(shopCarStrCookie)) {
                redisOperator.set(FOODIE_SHOP_CART + ":" + userId, shopCarStrCookie);
            }
        } else {
            // redis不为空 cookie不为空 合并数据
            if (StringUtils.isNotBlank(shopCarStrCookie)) {
                /*
                 * 1、已经存在的，把cookie中对应的数量，覆盖redis
                 * 2、该商品标记为待删除，统一放入一个待删除的list
                 * 3、从cookie中清理所有的待删除数据
                 * 4、合并redis和cookie中的数据
                 * 5、更新redis和cookie中
                 * */
                List<ShopCatBO> shopCatBOListRedis = JsonUtils.jsonToList(shopCartJsonRedis, ShopCatBO.class);
                List<ShopCatBO> shopCatBOListCookie = JsonUtils.jsonToList(shopCarStrCookie, ShopCatBO.class);

                // 待删除list
                List<ShopCatBO> pendingDeleteList = new ArrayList<>();
                for (ShopCatBO redisShopCart : shopCatBOListRedis) {
                    String redisSpecId = redisShopCart.getSpecId();

                    for (ShopCatBO cookieShopCart : shopCatBOListCookie) {
                        String cookieSpecId = cookieShopCart.getSpecId();

                        if (redisSpecId.equals(cookieSpecId)) {
                            // 覆盖购买数量不累加
                            redisShopCart.setBuyCounts(cookieShopCart.getBuyCounts());
                            // 把cookieShopCart放入待删除list中
                            pendingDeleteList.add(cookieShopCart);
                        }
                    }

                }
                // 从现有的cookie中删除对应的覆盖过的商品数据
                shopCatBOListCookie.removeAll(pendingDeleteList);
                // 合并两个list
                shopCatBOListRedis.addAll(shopCatBOListCookie);
                // 更新redis和cookie
                CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, JsonUtils.objectToJson(shopCatBOListRedis), true);
                redisOperator.set(FOODIE_SHOP_CART + ":" + userId, JsonUtils.objectToJson(shopCatBOListRedis));
            } else {
                // redis不为空 cookie为空 redis覆盖cookie
                CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, shopCartJsonRedis, true);
            }
        }


    }


    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        // 清除用户相关的信息cookie
        CookieUtils.deleteCookie(request, response, "user");

        // 用户退出登录，清除购物车
        CookieUtils.deleteCookie(request, response, FOODIE_SHOP_CART);
        // 分布式会话中需要清除用户数据
        redisOperator.del(REDIS_USER_TOKEN + ":" + userId);
        return IMOOCJSONResult.ok();
    }

}
