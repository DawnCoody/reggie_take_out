package com.dawncoody.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawncoody.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 18:06
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
