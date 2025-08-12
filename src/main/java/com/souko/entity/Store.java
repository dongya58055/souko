package com.souko.entity;

import lombok.Data;

@Data
public class Store {
	private Integer id;
	private String name;
	private String reMark;
	private int isDeleted;
}
