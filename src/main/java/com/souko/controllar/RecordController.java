package com.souko.controllar;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.souko.entity.Goods;
import com.souko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.souko.dto.LoginForm;
import com.souko.dto.RecordResult;
import com.souko.dto.Result;
import com.souko.entity.Record;
import com.souko.service.RecordService;

import javax.naming.spi.ResolveResult;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public Result test(){
        List<Record> list = recordService.list();
        return Result.success(list);
    }
    
    @PostMapping("/listPage")
    public Result listPage(@RequestBody LoginForm loginForm) {
        Page<Record> iPage = new Page<>(loginForm.getPageNum(), loginForm.getPageSize());
        //LambdaQueryWrapper 不能直接用 DTO 字段
        //
        //lqw.like(RecordResult::getGoodsName, name) 会报错，因为 goodsName 没有对应数据库列。
        //
        //所以 不能用 Lambda 表达式，必须用列名字符串或者 XML 动态条件。
        QueryWrapper<RecordResult> qw = new QueryWrapper<>();
        //在数字转成字符串时，用valeof不会报错
        //商品id
        String name = String.valueOf(loginForm.getParam().get("name"));
        //仓库和类型 并且都是ID查询
        String goodstype = String.valueOf(loginForm.getParam().get("goodstype"));
        String store = String.valueOf(loginForm.getParam().get("store"));
        qw.like(StringUtils.isNotBlank(name),"g.name",name);
        qw.eq(StringUtils.isNotBlank(goodstype), "gt.id",goodstype);
        qw.eq(StringUtils.isNotBlank(store),"s.id",store);
        //Page<RecordResult> page2 = recordService.selectRecordsWithUser();
        IPage result = recordService.pageCC(iPage,qw,name,goodstype,store);
        return Result.success(result.getRecords(), (int) result.getTotal());
       // return Result.success(page2.getRecords(), (int) page2.getTotal());
    }

    //表单的保存
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        //添加记录
        //更改先获取原来的id
        Goods goods = goodsService.getById(record.getGoods());
        int oldCount = record.getCount();
        //对于count进行更改
       // System.out.println(record.getAction());
        if (record.getAction()==2){
            //出库
            oldCount=-oldCount;
            record.setCount(oldCount);
        }
        int newCount = goods.getCount()+oldCount;
        goods.setCount(newCount);
        goodsService.updateById(goods);
       // System.out.println(record);
        return recordService.save(record)?Result.success("保存成功"):Result.fail("保存失败");


    }

}
