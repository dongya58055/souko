package com.souko.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.dto.Result;
import com.souko.entity.Store;
import com.souko.service.StoreService;

@RequestMapping("/store")
@RestController
public class StoreController {
	@Autowired
	private StoreService service;
	
	@GetMapping("/test")
	public Result test() {
		return Result.success("测试成功");
	}
	
	@PostMapping("/listPage")
	public Result listPage(@RequestBody LoginForm loginForm) {
		Page<Store> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
		LambdaQueryWrapper<Store> lqw = new LambdaQueryWrapper<>();
		String name = (String) loginForm.getParam().get("name");
		lqw.like(StringUtils.isNotBlank(name), Store::getName,name);
		Page<Store> page2 = service.page(iPage, lqw);
		return Result.success(page2.getRecords(), (int) page2.getTotal());
	}
	
	// 新增用户
	@PostMapping("/add")
	public Result save(@RequestBody Store store) {
		// return Result.success(userService.save(user));
		return service.save(store) ? Result.success("保存成功") : Result.fail();
	}

	// 编辑用户
	@PostMapping("/update")
	public Result update(@RequestBody Store store) {
		return service.updateById(store) ? Result.success("更新成功") : Result.fail("更新失败");
	}
	// 删除
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return service.removeById(id) ? Result.success("删除成功") : Result.fail("删除失败");

	}
}
