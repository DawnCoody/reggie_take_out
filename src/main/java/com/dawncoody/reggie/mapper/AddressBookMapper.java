package com.dawncoody.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawncoody.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-31 17:46
 */
@Mapper
public interface AddressBookMapper  extends BaseMapper<AddressBook> {
}
