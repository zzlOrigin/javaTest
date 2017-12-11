package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.Example.DepartmentExample;
import com.kaishengit.crm.entity.Department;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.exception.DepartmentException;
import com.kaishengit.crm.mapper.DepartmentMapper;
import com.kaishengit.crm.service.DepartmentService;
import com.kaishengit.crm.util.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private WeixinUtil weixinUtil;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.selectByExample(new DepartmentExample());
    }

    @Override
    @Transactional
    public void addDept(String deptName) throws DepartmentException{
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andDepartNameEqualTo(deptName);
        List<Department> departmentList = departmentMapper.selectByExample(departmentExample);

        Department department = null;
        if (departmentList != null && !departmentList.isEmpty()){
            department = departmentList.get(0);
        }

        if (department == null){
            department = new Department();
            department.setDepartName(deptName);
            department.setPid(1);
            departmentMapper.insert(department);

        try {
            weixinUtil.createDept(department.getId(),1,deptName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        }else{
            throw new DepartmentException("已存在部门");
        }
    }

    @Override
    @Transactional
    public void deleById(Integer id) {
        List<Staff> staffList = departmentMapper.findUseDept(id);
        weixinUtil.deleDept(id);
        if (staffList != null && !staffList.isEmpty()){
            throw new DepartmentException("因存在员工，部门删除失败");
        }else {
            departmentMapper.deleteByPrimaryKey(id);
        }

    }
}
