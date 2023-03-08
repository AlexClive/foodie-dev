package com.imooc.service;

import com.imooc.pojo.bo.ShopCatBO;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

import java.util.List;

public interface OrderServer {
    /**
     * 创建订单相关信息
     * @param submitOrderBO  创建订单相关信息
     */
    OrderVO createOder(List<ShopCatBO> shopCatBOList, SubmitOrderBO submitOrderBO);

}
