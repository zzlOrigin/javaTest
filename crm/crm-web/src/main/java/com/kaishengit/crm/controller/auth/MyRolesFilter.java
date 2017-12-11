package com.kaishengit.crm.controller.auth;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyRolesFilter extends RolesAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        //获得当前登录的对象
        Subject subject = getSubject(request,response);
        //获取用户的配置项
        String[] roleNames = (String[]) mappedValue;
        if (roleNames == null || roleNames.length == 0){
            //所有角色都可以访问
            return true;
        }
        for (String roleName : roleNames){
            if (subject.hasRole(roleName)){
                return true;
            }
        }
        return false;
    }
}
