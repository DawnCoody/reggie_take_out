package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.common.CustomException;
import com.dawncoody.reggie.dto.SetmealDto;
import com.dawncoody.reggie.entity.Setmeal;
import com.dawncoody.reggie.entity.SetmealDish;
import com.dawncoody.reggie.mapper.SetmealMapper;
import com.dawncoody.reggie.service.SetmealDishService;
import com.dawncoody.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 18:12
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        // 保存套餐的基本信息
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        // 保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        // 查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        // 如果不能删除，抛出一个业务异常
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        // 如果可以删除，先删除套餐表中的数据
        this.removeByIds(ids);

        // 删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }

    public void updateWithDish(SetmealDto setmealDto) {
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        Long setmealId = setmealDto.getId();
        // 先根据id把setmealDish表中对应套餐的数据删除了
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealId);
        setmealDishService.remove(queryWrapper);
        // 然后重新添加
        setmealDishes = setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealId);
            return item;
        }).collect(Collectors.toList());
        // 更新套餐数据
        this.updateById(setmealDto);
        // 更新套餐对应菜品数据
        setmealDishService.saveBatch(setmealDishes);
    }
}
