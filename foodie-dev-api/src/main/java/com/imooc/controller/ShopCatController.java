package com.imooc.controller;

import com.imooc.pojo.bo.shopCatBo;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value="购物车接口controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopCart")
@RestController
public class ShopCatController {
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车",httpMethod = "POST")
    @PostMapping("add")
    public IMOOCJSONResult add(
            @ApiParam(name = "userId", value = "用户id", readOnly = false)
            @RequestParam String userId,
            @ApiParam(name = "shopCatBo集合", value = "shopCatBo集合", readOnly = false)
            @RequestBody shopCatBo shopCatBo,
            HttpServletRequest request,
            HttpServletResponse response
    ){

        if(StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("");
        }
        System.out.println(shopCatBo);
        // TODO 前端用户在登录的情况下，添加商品到购物车，会同事在后端同步到redis缓存
        return IMOOCJSONResult.ok();

    }

}
