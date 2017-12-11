package com.kaishengit.entity;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */

public class Product implements Serializable {
    /**
     * 商品ID
     */

    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品简介
     */
    private String title;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 秒杀价格
     */
    private BigDecimal nowPrice;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 库存数量
     */
    private Integer productNum;

    /**
     * 商品详情
     */
    private String productList;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public Long getTime(){
        return getStartTime().getTime();
    }

    public boolean isstart(){
        DateTime dateTime = new DateTime(getStartTime().getTime());
        return dateTime.isBeforeNow();
    }

    public boolean isend(){
        DateTime dateTime = new DateTime(getEndTime().getTime());
        return dateTime.isBeforeNow();
    }
}