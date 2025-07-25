package com.souko.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.souko.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	List<User> selectAll();

}
