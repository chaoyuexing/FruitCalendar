package com.example.hasee.fruitcalendar.bean;

/**
 * 分类类型列表实体类
 * Created by xing on 2017/8/15.
 */

public class CategoryTypeBean {


    private int id;
    private String icon;
    private String showlevel;
    private String name;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShowlevel() {
        return showlevel;
    }

    public void setShowlevel(String showlevel) {
        this.showlevel = showlevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
