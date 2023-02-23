package com.imooc.controller;

import com.imooc.enums.PayMethod;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.OrderServer;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Api(value = "订单相关", tags = {"订单相关的api"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController{

    @Autowired
    public OrderServer orderServer;


    @ApiOperation(value = "用户下单", tags = "用户下单", httpMethod = "POST")
    @PostMapping("create")
    public IMOOCJSONResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (!Objects.equals(submitOrderBO.getPayMethod(), PayMethod.WEIXIN.type) && !Objects.equals(submitOrderBO.getPayMethod(), PayMethod.ALIPAY.type)) {
            return IMOOCJSONResult.errorMsg("支付方式不支持");
        }
       // System.out.println(submitOrderBO.toString());

        // 1、创建订单
        String orderId = orderServer.createOder(submitOrderBO);
        // 2、创建订单之后，移除购物车中已结算的商品
        // TODO 整合redis之后，完善购物车中已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request,response,FOODIE_SHOP_CART,"",true);
        // 3、向支付中心发送当前订单，用于保存支付中心的商品数据


        return IMOOCJSONResult.ok(orderId);
    }
}
