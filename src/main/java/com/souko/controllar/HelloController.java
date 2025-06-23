package com.souko.controllar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.entity.User;
import com.souko.service.UserService;

@RestController
@RequestMapping()
public class HelloController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/test")
	public List<User> list() {
		//return userService.list();
		return userService.selectAll();
	}
	
	@PostMapping("/listPage")
	public List<User> listPage(@RequestBody LoginForm loginForm){
		Page<User> iPage = new Page<>(loginForm.getPageNum(),loginForm.getPageSize());
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
		//lqw.like(User::getName, loginForm.getName());
		lqw.like(User::getName, loginForm.getParam().get("name"));
		Page<User> page2 = userService.page(iPage, lqw);
		return page2.getRecords();
		
	}
}
