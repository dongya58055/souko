package com.souko.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
	    private Integer id;
	    private String no;
	    private String name;
	    //序列化：从后端对象转换成 JSON，password 字段被忽略，不会返回给前端。
	    //反序列化：前端发送 JSON 请求体时，password 字段也会被忽略，导致后端接收的 password 是 null。
	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    //传给前端时列化为 JSON 时自动忽略敏感字段
	    private String password;
	    private Integer age;
		//传给前端时要修改成字符串
	    private Integer sex;
	    private String phone;
	    private Integer roleId;
	    private String isvalid;
	    private int isDeleted;
}
