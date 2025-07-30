package com.souko.controllar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.souko.entity.User;
import com.souko.service.UserService;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    private UserService userService;

    // 测试
    @GetMapping("/test")
    public Result list() {
        List<User> users = userService.list();
        return Result.success(users, users.size());
    }

    @PostMapping("/add")
    public Result save(@RequestBody User user) {
        //return Result.success(userService.save(user));
        return userService.save(user) ? Result.success("保存成功") : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody LoginForm loginForm) {
        Page<User> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        String name = (String) loginForm.getParam().get("name");
        String sex = (String) loginForm.getParam().get("sex");
        lqw.like(StringUtils.isNotBlank(name), User::getName, loginForm.getParam().get("name"));
        lqw.eq(StringUtils.isNotBlank(sex), User::getSex, loginForm.getParam().get("sex"));
        Page<User> page2 = userService.page(iPage, lqw);
        return Result.success(page2.getRecords(), (int) page2.getTotal());
    }

    //id
    @GetMapping("/findByNo")
    public Result findNo(@RequestParam String no) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(User::getNo, no);
//        User user = userService.getOne(lqw);
        User user = userService.lambdaQuery().eq(User::getNo,no).one();
        if(user==null){
            return Result.fail("用户不存在");
        }
        return Result.success(user);

    }
}
