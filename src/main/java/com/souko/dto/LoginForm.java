package com.souko.dto;

import java.util.HashMap;

import lombok.Data;

@Data
public class LoginForm {
	private static int PAGE_SIZE=10;
	private static int PAGE_NUM=1;
	private int pageNum=PAGE_NUM;
	private int pageSize=PAGE_SIZE;
	private String name;
	private HashMap<String, Object> param;
}
