package com.souko.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;
    private String name;
    private String store;
    private String goodsType;
    private int count;
    private String reMark;
}
