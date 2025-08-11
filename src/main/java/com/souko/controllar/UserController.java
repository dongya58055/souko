package com.souko.controllar;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.dto.Result;
import com.souko.entity.Menu;
import com.souko.entity.User;
import com.souko.service.MenuService;
import com.souko.service.UserService;
import com.souko.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserServiceImpl userServiceImpl;

	@Autowired
	private UserService userService;

	UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	// 测试
	@GetMapping("/test")
	public Result list() {
		List<User> users = userService.list();
		return Result.success(users, users.size());
	}

	// 新增用户
	@PostMapping("/add")
	public Result save(@RequestBody User user) {
		// return Result.success(userService.save(user));
		return userService.save(user) ? Result.success("保存成功") : Result.fail();
	}

	// 编辑用户
	@PostMapping("/update")
	public Result update(@RequestBody User user) {
		return userService.updateById(user) ? Result.success("更新成功") : Result.fail("更新失败");
	}

	@PostMapping("/listPage")
	public Result listPage(@RequestBody LoginForm loginForm) {
		Page<User> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
		String name = (String) loginForm.getParam().get("name");
		String sex = (String) loginForm.getParam().get("sex");
		String roleId =(String) loginForm.getParam().get("roleId");
		lqw.like(StringUtils.isNotBlank(name), User::getName,name);
		lqw.eq(StringUtils.isNotBlank(sex), User::getSex, sex);
		lqw.eq(StringUtils.isNotBlank(roleId),User::getRoleId,roleId);
		Page<User> page2 = userService.page(iPage, lqw);
		return Result.success(page2.getRecords(), (int) page2.getTotal());
	}

	// id
	// @GetMapping("/findByNo")
	@GetMapping("/findByNo")
	public Result findNo(@RequestParam String no) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(User::getNo, no);
//        User user = userService.getOne(lqw);
		User user = userService.lambdaQuery().eq(User::getNo, no).one();

		if (user == null) {
			return Result.fail("用户不存在");
		}
		return Result.success(user);
	}

	// 删除
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable int id) {
		return userService.removeById(id) ? Result.success("删除成功") : Result.fail("删除失败");

	}

	@Autowired
	private MenuService mService;

	// 登录
	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		User one = userService.lambdaQuery().eq(User::getNo, user.getNo()).eq(User::getPassword, user.getPassword())
				.one();
		List listUser = mService.lambdaQuery().like(Menu::getMenuRight, one.getRoleId()).list();
		HashMap hashMap = new HashMap<>();

		if (one != null && listUser != null) {
			hashMap.put("user", one);
			hashMap.put("menu", listUser);
			return Result.success(hashMap);
		}
//    	if (listUser != null) {
//    		return one==null?Result.fail():Result.success(one);
//		}else {
//			return Result.fail();
//		}
		return Result.fail();
	}

}
