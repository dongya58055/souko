package com.souko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.souko.dto.RecordResult;
import com.souko.entity.Record;

import java.util.List;

public interface RecordService extends IService<Record> {
    //Page<RecordResult> selectRecordsWithUser(int pageNum,int pageSize);
    IPage pageCC(IPage<Record> page, QueryWrapper<RecordResult> lqw,String name,String goodstype,
                 String store,int roleId,int userId);
}
