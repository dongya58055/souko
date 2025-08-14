package com.souko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.souko.entity.Goods;
import com.souko.mapper.GoodsMapper;
import com.souko.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
