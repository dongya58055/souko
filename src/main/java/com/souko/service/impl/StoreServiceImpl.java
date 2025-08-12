package com.souko.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.souko.entity.Store;
import com.souko.mapper.StoreMapper;
import com.souko.service.StoreService;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService{

}
