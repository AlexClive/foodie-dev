package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.bo.SubmitOrderBO;

import java.util.List;

public interface OrderServer {
    /**
     * 创建订单相关信息
     * @param submitOrderBO
     */
    public void createOder(SubmitOrderBO submitOrderBO);

}
