package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.entity.OrderDetail;
import com.dawncoody.reggie.mapper.OrderDetailMapper;
import com.dawncoody.reggie.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-04-01 09:30
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
