package com.example.hasee.fruitcalendar.bean;

/**
 * Created by xing on 2017/8/11.
 */

public class BannerBean {


    private String endtime;
    private String createtime;
    private String id;
    private String isvalue;
    private String name;
    private String bannerurl;
    private String contexturl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsvalue() {
        return isvalue;
    }

    public void setIsvalue(String isvalue) {
        this.isvalue = isvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }

    public String getContexturl() {
        return contexturl;
    }

    public void setContexturl(String contexturl) {
        this.contexturl = contexturl;
    }
}
