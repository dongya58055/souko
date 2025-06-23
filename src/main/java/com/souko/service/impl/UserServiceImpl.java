package com.souko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.souko.entity.User;
import com.souko.mapper.UserMapper;
import com.souko.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

}
