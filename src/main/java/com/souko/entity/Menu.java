package com.souko.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String menuCode;
    private String menuName;
    private String menuLevel;
    private String menuParentCode;
    private String menuClick;
    private String MenuRight;
    private String menuComponent;
    private String menuIcon;
}
