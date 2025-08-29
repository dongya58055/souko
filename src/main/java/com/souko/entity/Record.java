package com.souko.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Record {
    private Integer id;
    private int goods;
    @TableField("userId")
    @JsonProperty("userId")
    private int userid;
    private int adminId;
    private int count;
    @TableField("createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private LocalDateTime createTime;
    private String remark;
    @TableField(exist = false)
    private int action;
    private String roleId;
}
