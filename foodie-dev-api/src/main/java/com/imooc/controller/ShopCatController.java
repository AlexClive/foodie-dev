package com.imooc.controller;

import com.imooc.pojo.bo.ShopCatBO;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(value="购物车接口controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
public class ShopCatController extends BaseController{

    @Autowired
    private RedisOperator redisOperator;

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
        // 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步到redis缓存
        // 需要判断当前购物车中包含已经存在的商品，如果存在则累加
        String shopCartJson = redisOperator.get(FOODIE_SHOP_CART+":"+userId);
        List<ShopCatBO> shopCatBOList = null;
        if(StringUtils.isNoneBlank(shopCartJson)){
            // redis中已经存在购物车了
            shopCatBOList = JsonUtils.jsonToList(shopCartJson,ShopCatBO.class);
            // 判断购物车中是否存在商品，如果有counts累加
            boolean isHaving = false;
            for(ShopCatBO sc:shopCatBOList){
                String temSpecId = sc.getSpecId();
                if(temSpecId.equals(shopCatBo.getSpecId())){
                    sc.setBuyCounts(sc.getBuyCounts()+shopCatBo.getBuyCounts());
                    isHaving = true;
                }
            }
            if(!isHaving){
                shopCatBOList.add(shopCatBo);
            }
        }else {
            shopCatBOList = new ArrayList<>();
            shopCatBOList.add(shopCatBo);
        }
        redisOperator.set(FOODIE_SHOP_CART+":"+userId,JsonUtils.objectToJson(shopCatBOList));
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
        //  前端用户在登录的情况下，购物车删除商品，会同时在后端同步到redis缓存

        String shopCartJson = redisOperator.get(FOODIE_SHOP_CART+":"+userId);
        if(StringUtils.isNoneBlank(shopCartJson)){
            // redis中已经有购物车了
            List<ShopCatBO> shopCatBOList = JsonUtils.jsonToList(shopCartJson,ShopCatBO.class);
            for(ShopCatBO sc: shopCatBOList){
                String tmpSpecId = sc.getSpecId();
                if(tmpSpecId.equals(itemSpecId)){
                    shopCatBOList.remove(sc);
                    break;
                }
            }
            // 覆盖现有的redis
            redisOperator.set(FOODIE_SHOP_CART+":"+userId,JsonUtils.objectToJson(shopCatBOList));
        }
        return IMOOCJSONResult.ok();
    }

}
