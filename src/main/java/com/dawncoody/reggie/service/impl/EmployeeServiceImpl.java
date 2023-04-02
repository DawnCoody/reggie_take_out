package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.entity.Employee;
import com.dawncoody.reggie.mapper.EmployeeMapper;
import com.dawncoody.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-22 22:55
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
