package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.common.BaseContext;
import com.dawncoody.reggie.entity.ShoppingCart;
import com.dawncoody.reggie.mapper.ShoppingCartMapper;
import com.dawncoody.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-31 19:46
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    public void clean() {
        // SQL:delete from shopping_cart where user_id=?
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        this.remove(queryWrapper);
    }
}
