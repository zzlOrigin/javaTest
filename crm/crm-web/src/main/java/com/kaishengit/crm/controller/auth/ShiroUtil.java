package com.kaishengit.crm.controller.auth;

import com.kaishengit.crm.entity.Staff;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro的工具类
 */
public class ShiroUtil {

    public static Staff getStaff(){
        Subject subject = SecurityUtils.getSubject();
        return (Staff) subject.getPrincipal();
    }
}
