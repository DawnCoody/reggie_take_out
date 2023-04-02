package com.dawncoody.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawncoody.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-22 22:53
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
