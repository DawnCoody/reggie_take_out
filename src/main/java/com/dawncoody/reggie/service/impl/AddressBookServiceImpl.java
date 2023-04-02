package com.dawncoody.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawncoody.reggie.entity.AddressBook;
import com.dawncoody.reggie.mapper.AddressBookMapper;
import com.dawncoody.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-31 17:49
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
