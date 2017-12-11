package com.kaishengit.controller;

import com.kaishengit.entity.Kaola;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private KaolaService kaolaService;
    @GetMapping("/hello")
    public String findAll(Model model,@RequestParam(required = false,defaultValue = "0") Integer page){
        List<Kaola> kaolas = kaolaService.findAll(page);
        model.addAttribute("kaolas",kaolas);
        return "home";
    }
    @GetMapping("/product/new")
    public String addProduct(){
        return "new";
    }

    @GetMapping("/product/{id:\\d+}")
    public String find(Model model, @PathVariable Integer id){
        Kaola kaola = kaolaService.findById(id);
        model.addAttribute("kaola",kaola);
        return "show";
    }
}
