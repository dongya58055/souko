package com.souko.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.RecordResult;
import com.souko.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    IPage pageCC(IPage<Record> page,
                 @Param(Constants.WRAPPER) QueryWrapper<RecordResult> lqw,
                 @Param("name") String name,
                 @Param("goodstype") String goodstype,
                 @Param("store") String store
                 );
}
