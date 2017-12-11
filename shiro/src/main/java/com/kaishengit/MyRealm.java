package com.kaishengit;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {


    @Override
    public String getName() {
        return "my-realm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String name = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        if (!"tom".equals(name)){
            System.out.println("该用户不存在");
            throw new UnknownAccountException();
        }
        if (!"123456".equals(password)){
            System.out.println("账号或者密码错误");
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(name,password,getName());
    }
}
