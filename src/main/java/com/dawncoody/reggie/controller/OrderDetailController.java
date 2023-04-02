package com.dawncoody.reggie.controller;

import com.dawncoody.reggie.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 订单详细
 * @author: dawncoody
 * @create: 2023-04-01 09:34
 */
@RestController
@RequestMapping
@Slf4j
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
