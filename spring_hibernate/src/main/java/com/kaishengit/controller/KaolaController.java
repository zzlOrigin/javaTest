package com.kaishengit.controller;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.service.KaolaService;
import com.kaishengit.util.PageHelp;
import com.kaishengit.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class KaolaController {

    @Autowired
    private KaolaService kaolaService;
    @GetMapping("/")
    public String list(Model model, HttpServletRequest httpServletRequest,@RequestParam(required = false,defaultValue = "1") Integer pageNo){
        PageHelp<Kaola> kaolas = kaolaService.findByRequest(RequestUtil.doUrl(httpServletRequest),pageNo);
        model.addAttribute("pages",kaolas);
        return "list";
    }
   @GetMapping("/new")
    public String add(){
        return "new";
   }
   @PostMapping("/new")
   public String addAfter(Kaola kaola){
        kaolaService.save(kaola);
        return "redirect:/";
   }

   @GetMapping("/kaola/{id:\\d+}")
   public String findById(@PathVariable Integer id,Model model){
       Kaola kaola = kaolaService.findById(id);
       model.addAttribute("kaola",kaola);
       return "kaola";
   }
   @GetMapping("/edit/{id:\\d+}")
   public String edit(@PathVariable Integer id,Model model){
        Kaola kaola = kaolaService.findById(id);
        model.addAttribute("kaola",kaola);
        return "edit";
   }
   @PostMapping("/edit")
   public String edit(Kaola kaola){
        kaolaService.update(kaola);
        return "redirect:/kaola/"+kaola.getId();
   }
   @GetMapping("/dele/{id:\\d+}")
   public String dele(@PathVariable Integer id){
       kaolaService.deleById(id);
       return "redirect:/";
   }
}
