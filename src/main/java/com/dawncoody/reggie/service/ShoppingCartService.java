package com.dawncoody.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawncoody.reggie.entity.ShoppingCart;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-31 19:40
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    void clean();
}
