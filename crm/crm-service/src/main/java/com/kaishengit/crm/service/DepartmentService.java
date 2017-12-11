package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartment();

    void addDept(String deptName);

    void deleById(Integer id);
}
