package com.kaishengit.crm.controller;


import com.kaishengit.crm.controller.auth.ShiroUtil;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.exception.CustomerException;
import com.kaishengit.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

public abstract class BasicController {

    @Autowired
    private CustomerService customerService;

    public static Staff getStaff(HttpSession httpSession){
        return ShiroUtil.getStaff();

    }
    public Customer findCustomerByStaffId(HttpSession httpSession, Integer id){
        Staff staff = ShiroUtil.getStaff();
        Customer customer = customerService.findById(id);
        if (customer == null){
            throw new CustomerException("该客户不存在");
        }

        if (customer.getStaffId() != 0 && customer.getStaffId() != staff.getId()){
            throw new CustomerException("该客户不属于你");
        }
        return customer;
    }
}
