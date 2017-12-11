package com.kaishengit.crm.controller.Interceptor;

import com.kaishengit.crm.entity.Staff;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();

        if(requestUrl.startsWith("/static")){
            return true;
        }

        if("".equals(requestUrl) || "/".equals(requestUrl)){
            return true;
        }

        HttpSession httpSession = request.getSession();
        Staff staff = (Staff) httpSession.getAttribute("staff");
        if(staff != null){
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
