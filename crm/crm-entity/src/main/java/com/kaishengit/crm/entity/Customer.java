package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    private Integer id;

    /**
     * 顾客姓名
     */
    private String custName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 职业
     */
    private String jobTitle;

    /**
     * 行业
     */
    private String industry;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 来源
     */
    private String source;

    /**
     * 级别
     */
    private String rank;

    /**
     * 客服ID
     */
    private Integer staffId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 建立时间
     */
    private Date setTime;

    /**
     * 最后跟进时间
     */
    private Date followTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 提示信息
     */
    private String reminder;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", industry='" + industry + '\'' +
                ", address='" + address + '\'' +
                ", source='" + source + '\'' +
                ", rank='" + rank + '\'' +
                ", staffId=" + staffId +
                ", remark='" + remark + '\'' +
                ", setTime=" + setTime +
                ", followTime=" + followTime +
                ", updateTime=" + updateTime +
                ", reminder='" + reminder + '\'' +
                '}';
    }
}