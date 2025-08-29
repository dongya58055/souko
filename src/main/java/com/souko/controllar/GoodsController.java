package com.souko.controllar;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.dto.Result;
import com.souko.entity.Goods;
import com.souko.entity.Goodstype;
import com.souko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/test")
    public Result test() {
        return Result.success("成功");
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody LoginForm loginForm) {
        Page<Goods> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
        LambdaQueryWrapper<Goods> lqw = new LambdaQueryWrapper<>();
        String name = (String) loginForm.getParam().get("name");
        //在数字转成字符串时，用valeof不会报错
        String goodstype = String.valueOf(loginForm.getParam().get("goodstype"));
        String store = String.valueOf(loginForm.getParam().get("store"));
        lqw.like(StringUtils.isNotBlank(name), Goods::getName, name);
        lqw.eq(StringUtils.isNotBlank(goodstype), Goods::getGoodsType, goodstype);
        lqw.eq(StringUtils.isNotBlank(store), Goods::getStore, store);
        Page<Goods> page2 = goodsService.page(iPage, lqw);
        return Result.success(page2.getRecords(), (int) page2.getTotal());
    }

    // 新增用户
    @PostMapping("/add")
    public Result save(@RequestBody Goods store) {
        // return Result.success(userService.save(user));
        return goodsService.save(store) ? Result.success("保存成功") : Result.fail();
    }

    // 编辑用户
    @PostMapping("/update")
    public Result update(@RequestBody Goods store) {
        return goodsService.updateById(store) ? Result.success("更新成功") : Result.fail("更新失败");
    }

    // 删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return goodsService.removeById(id) ? Result.success("删除成功") : Result.fail("删除失败");

    }
}
