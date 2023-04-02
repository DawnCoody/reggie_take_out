package com.dawncoody.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawncoody.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-04-01 09:26
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
