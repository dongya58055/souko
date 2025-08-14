package com.souko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.souko.entity.Goodstype;
import com.souko.mapper.GoodsTypeMapper;
import com.souko.service.GoodsTypeService;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, Goodstype> implements GoodsTypeService {
}
