package com.dawncoody.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawncoody.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-24 19:54
 */

@Mapper
public interface UserMapper  extends BaseMapper<User> {
}
