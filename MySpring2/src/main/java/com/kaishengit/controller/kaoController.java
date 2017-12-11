package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kaola")
public class kaoController {

    @GetMapping
    public String sayHello(){
        System.out.println("你好,我是周志龙");
        return "login";
    }

}
