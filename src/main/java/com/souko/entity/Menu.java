package com.souko.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String menuCode;
    private String name;
    private String level;
    private String parentCOde;
    private String click;
    private String right;
    private String compoent;
    private String icon;
}
