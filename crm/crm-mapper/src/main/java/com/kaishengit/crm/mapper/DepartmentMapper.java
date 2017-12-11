package com.kaishengit.crm.mapper;

import com.kaishengit.crm.Example.DepartmentExample;
import com.kaishengit.crm.entity.Department;
import java.util.List;

import com.kaishengit.crm.entity.Staff;
import org.apache.ibatis.annotations.Param;


public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Staff> findUseDept(@Param("id") Integer id);
}