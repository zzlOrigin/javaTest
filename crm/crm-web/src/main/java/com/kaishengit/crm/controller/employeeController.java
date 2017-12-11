package com.kaishengit.crm.controller;

import com.kaishengit.crm.Example.StaffExample;
import com.kaishengit.crm.entity.Department;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.exception.DepartmentException;
import com.kaishengit.crm.service.DepartmentService;
import com.kaishengit.crm.service.StaffService;
import com.kaishengit.web.result.AjaxResult;
import com.kaishengit.web.result.DataTablesResult;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class employeeController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;


    @GetMapping
    public String employ(){
        return "employee/list";
    }

    @GetMapping("/dept.json")
    @ResponseBody
    public List<Department> findAllDept(){
        return departmentService.findAllDepartment();
    }

    @PostMapping("/dept/new")
    @ResponseBody
    public AjaxResult addDept(String deptName){
        try {
            departmentService.addDept(deptName);
            return AjaxResult.success();
        }catch (DepartmentException e){
            return AjaxResult.error(e.getMessage());
        }

    }

    @GetMapping("/load.json")
    @ResponseBody
    public DataTablesResult<Staff> staffLoad(
            Integer draw, Integer start, Integer length,
            Integer deptId, HttpServletRequest request){
            String keyword = request.getParameter("search[value]");
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        map.put("keyword",keyword);
        map.put("deptId",deptId);
        List<Staff> staffList = staffService.selectByMap(map);
        Long total = staffService.CountByDeptId(deptId);
        return new DataTablesResult<Staff>(draw,total.intValue(), staffList);
    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult addStaff(String userName,String password,
                               String mobile,Integer[] deptId){
        try{
            staffService.addNewStaff(userName,password,mobile,deptId);
            return AjaxResult.success();
        }catch (DepartmentException ex){
            return AjaxResult.error(ex.getMessage());
        }

    }
    @PostMapping("/{id:\\d+}/delete")
    @ResponseBody
    public AjaxResult deleById(@PathVariable Integer id){
        staffService.deleByid(id);
        return AjaxResult.success();
    }
}
