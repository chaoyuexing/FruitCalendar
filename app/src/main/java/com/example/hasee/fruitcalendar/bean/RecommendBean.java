package com.example.hasee.fruitcalendar.bean;

/**
 * Created by xing on 2017/8/14.
 */

public class RecommendBean {


    private int id;
    private String endtime;
    private String starttime;
    private String fgoodstype;
    private String isalive;
    private String name;
    private int fgoodsid;
    private String bannerimgurl;
    private String contexturl;
    private GoodsBean goods;

    public GoodsBean getGoodsBean() {
        return goods;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        goods = goodsBean;
    }

    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getFgoodstype() {
        return fgoodstype;
    }

    public void setFgoodstype(String fgoodstype) {
        this.fgoodstype = fgoodstype;
    }

    public String getIsalive() {
        return isalive;
    }

    public void setIsalive(String isalive) {
        this.isalive = isalive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFgoodsid() {
        return fgoodsid;
    }

    public void setFgoodsid(int fgoodsid) {
        this.fgoodsid = fgoodsid;
    }

    public String getBannerimgurl() {
        return bannerimgurl;
    }

    public void setBannerimgurl(String bannerimgurl) {
        this.bannerimgurl = bannerimgurl;
    }

    public String getContexturl() {
        return contexturl;
    }

    public void setContexturl(String contexturl) {
        this.contexturl = contexturl;
    }
}
