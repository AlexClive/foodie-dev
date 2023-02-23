package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;

public interface OrderServer {
    /**
     * 创建订单相关信息
     * @param submitOrderBO  创建订单相关信息
     */
    void createOder(SubmitOrderBO submitOrderBO);

}
