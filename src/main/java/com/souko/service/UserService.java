package com.souko.service;



import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.souko.entity.User;

public interface UserService extends IService<User>{

	 List<User> selectAll();

}
