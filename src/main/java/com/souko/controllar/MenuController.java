package com.souko.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souko.service.MenuService;


@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService mService;
	
	@GetMapping("/test")
	public String Name() {
		//TODO: process PUT request
		
		return "测试成功";
	}
}
