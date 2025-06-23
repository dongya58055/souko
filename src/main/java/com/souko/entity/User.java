package com.souko.entity;

import lombok.Data;

@Data
public class User {
	    private Integer id;
	    private String no;
	    private String name;
	    private String password;
	    private Integer age;
	    private Integer sex;
	    private String phone;
	    private Integer roleId;
	    private String isvalid;
	    private int isDeleted;
}
