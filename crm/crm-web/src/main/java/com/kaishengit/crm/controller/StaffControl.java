package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.exception.StaffException;
import com.kaishengit.crm.service.StaffService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class StaffControl {

    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @PostMapping("/")
    public String login(Staff staff, RedirectAttributes redirectAttributes,
                        HttpServletRequest request,boolean rememberMe){
        try{
            Subject subject = SecurityUtils.getSubject();
            System.out.println(new Md5Hash(staff.getPassword()).toString());
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                    staff.getMobile(),new Md5Hash(staff.getPassword()).toString(),rememberMe);
            subject.login(usernamePasswordToken);
            /*Staff staff1 = (Staff) subject.getPrincipal();
            Session session = subject.getSession();
            session.setAttribute("staff",staff1);*/
            String url = "/home";
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null){
                url = savedRequest.getRequestUrl();
            }
            return "redirect:"+url;
        }catch (AuthenticationException e){
            redirectAttributes.addFlashAttribute("message","账户名或者密码错误");
            return "redirect:/";
        }

    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/exit")
    public String loginout(HttpSession session,RedirectAttributes redirectAttributes){
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","已安全退出账号");
        return "redirect:/";
    }

}
