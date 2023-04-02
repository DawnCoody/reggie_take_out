package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.entity.User;
import com.dawncoody.reggie.mapper.UserMapper;
import com.dawncoody.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-24 19:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
