package com.kaishengit.crm.mapper;


import com.kaishengit.crm.Example.StaffExample;
import java.util.List;
import java.util.Map;

import com.kaishengit.crm.entity.Staff;
import org.apache.ibatis.annotations.Param;

public interface StaffMapper {
    long countByExample(StaffExample example);

    int deleteByExample(StaffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    int insertSelective(Staff record);

    List<Staff> selectByExample(StaffExample example);

    Staff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> selectByMap(Map<String, Object> map);

    Long CountByDeptId(@Param("deptId") Integer deptId);
}