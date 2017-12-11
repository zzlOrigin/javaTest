package com.kaishengit.crm.service;

import com.kaishengit.crm.Example.StaffExample;
import com.kaishengit.crm.entity.Staff;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScannerRegistrar;

import java.util.List;
import java.util.Map;


public interface StaffService {

    Staff login(String mobile,String password);

    List<Staff> selectByMap(Map<String, Object> map);

    void addNewStaff(String userName, String password, String mobile, Integer[] daptId);

    Long CountByDeptId(Integer deptId);

    void deleByid(Integer id);

    List<Staff> findAllStaff();

    Staff findById(Integer sid);

    Staff findByMobile(String userName);
}
