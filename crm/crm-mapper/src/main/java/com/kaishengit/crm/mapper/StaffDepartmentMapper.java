package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.StaffDepartment;
import com.kaishengit.crm.Example.StaffDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaffDepartmentMapper {
    long countByExample(StaffDepartmentExample example);

    int deleteByExample(StaffDepartmentExample example);

    int insert(StaffDepartment record);

    int insertSelective(StaffDepartment record);

    List<StaffDepartment> selectByExample(StaffDepartmentExample example);

    int updateByExampleSelective(@Param("record") StaffDepartment record, @Param("example") StaffDepartmentExample example);

    int updateByExample(@Param("record") StaffDepartment record, @Param("example") StaffDepartmentExample example);
}