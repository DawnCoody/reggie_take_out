package com.dawncoody.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawncoody.reggie.dto.DishDto;
import com.dawncoody.reggie.entity.Dish;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 18:09
 */
public interface DishService extends IService<Dish> {
    // 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，同时更新口味信息
    void updateWithFlavor(DishDto dishDto);
}
