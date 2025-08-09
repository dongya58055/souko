package com.souko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.souko.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
