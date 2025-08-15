package com.souko.controllar;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.dto.Result;
import com.souko.entity.Goodstype;
import com.souko.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodstype")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping("/test")
    public Result test() {
        return Result.success("测试成功");
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody LoginForm loginForm) {
        Page<Goodstype> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
        LambdaQueryWrapper<Goodstype> lqw = new LambdaQueryWrapper<>();
        String name = (String) loginForm.getParam().get("name");
        lqw.like(StringUtils.isNotBlank(name), Goodstype::getName,name);
        Page<Goodstype> page2 = goodsTypeService.page(iPage, lqw);
        return Result.success(page2.getRecords(), (int) page2.getTotal());
    }

    // 新增用户
    @PostMapping("/add")
    public Result save(@RequestBody Goodstype store) {
        // return Result.success(userService.save(user));
        return goodsTypeService.save(store) ? Result.success("保存成功") : Result.fail();
    }

    // 编辑用户
    @PostMapping("/update")
    public Result update(@RequestBody Goodstype store) {
        return goodsTypeService.updateById(store) ? Result.success("更新成功") : Result.fail("更新失败");
    }
    // 删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return goodsTypeService.removeById(id) ? Result.success("删除成功") : Result.fail("删除失败");

    }
    @GetMapping("/list")
    public Result list(){
        List<Goodstype> list = goodsTypeService.list();
        return Result.success(list);
    }
}
