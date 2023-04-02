package com.dawncoody.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawncoody.reggie.entity.Category;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 16:39
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
