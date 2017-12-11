package com.kaishengit.crm.entity;

import java.io.Serializable;

public class StaffDepartment implements Serializable{
    private Integer staffId;

    private Integer departmentId;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}