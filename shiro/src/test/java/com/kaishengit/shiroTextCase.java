package com.kaishengit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class shiroTextCase {

    @Test
    public void textCase(){
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tom","123456");
    try {
        subject.login(token);
        System.out.println("管理员权限"+subject.hasRole("admin"));
        System.out.println("CTO权限"+subject.hasRole("cto"));
    }catch (UnknownAccountException e){
        e.printStackTrace();
        System.out.println("该用户不存在");
    }catch (LockedAccountException e){
        e.printStackTrace();
        System.out.println("账户被冻结了");
    }catch (IncorrectCredentialsException e){
        System.out.println("账号或者密码错误");
    }catch (AuthenticationException e){
        System.out.println("认证异常");
    }
    }
}
