package com.souko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.souko.entity.Menu;
import com.souko.mapper.MenuMapper;
import com.souko.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
