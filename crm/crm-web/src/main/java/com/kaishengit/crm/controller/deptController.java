package com.kaishengit.crm.controller;


import com.kaishengit.crm.exception.DepartmentException;
import com.kaishengit.crm.service.DepartmentService;
import com.kaishengit.web.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class deptController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/dele")
    @ResponseBody
    public AjaxResult deleById(Integer id){

        try {
            departmentService.deleById(id);
            return AjaxResult.success();
        }catch (DepartmentException ex){
            return AjaxResult.error(ex.getMessage());
        }

    }
}
