package com.souko.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
	private int code;
	private String msg;
	private Integer total;
	private T data;

	public static <T> Result<T> fail() {
		return new Result<>(400, "失败", 0, null);
	}
	public static <T> Result<T> fail(String msg) {
		return new Result<>(400, msg, 0, null);
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(200, "成功", null, data);
	}

	public static <T> Result<T> success( T data,Integer total) {
		return new Result<>(200, "成功", total, data);
	}

	
}