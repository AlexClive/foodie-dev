package com.imooc.controller;

import com.imooc.pojo.bo.ShopCatBO;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value="购物车接口controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
public class ShopCatController {
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车",httpMethod = "POST")
    @PostMapping("add")
    public IMOOCJSONResult add(
            @ApiParam(name = "userId", value = "用户id", readOnly = false)
            @RequestParam String userId,
            @ApiParam(name = "shopCatBo集合", value = "shopCatBo集合", readOnly = false)
            @RequestBody ShopCatBO shopCatBo,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if(StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("");
        }
        System.out.println(shopCatBo);
        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步到redis缓存
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "购物车删除商品", notes = "购物车删除商品",httpMethod = "POST")
    @PostMapping("del")
    public IMOOCJSONResult del(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId", value = "商品规格id", required = true)
            @RequestParam  String itemSpecId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }
        // TODO 前端用户在登录的情况下，购物车删除商品，会同时在后端同步到redis缓存
        return IMOOCJSONResult.ok();
    }

}
