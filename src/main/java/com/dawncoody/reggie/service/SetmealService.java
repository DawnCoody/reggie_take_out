package com.dawncoody.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawncoody.reggie.dto.SetmealDto;
import com.dawncoody.reggie.entity.Setmeal;
import com.dawncoody.reggie.entity.SetmealDish;

import java.util.List;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 18:09
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    void removeWithDish(List<Long> ids);

    void updateWithDish(SetmealDto setmealDto);
}
