package com.dawncoody.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawncoody.reggie.entity.OrderDetail;
import com.dawncoody.reggie.entity.Orders;

import java.util.List;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-04-01 09:29
 */
public interface OrderService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    void submit(Orders orders);
    public List<OrderDetail> getOrderDetailListByOrderId(Long orderId);

}
