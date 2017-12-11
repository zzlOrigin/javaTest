package com.kaishengit.controller;


import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.kaolaType;
import com.kaishengit.servlet.KaolaService;
import com.kaishengit.servlet.KaolaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.MalformedParametersException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class KaolaController {

    @Autowired
    private KaolaService kaolaService;
    @Autowired
    private KaolaTypeService kaolaTypeService;

    @GetMapping
    public String findPage(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                           @RequestParam(required = false,defaultValue = "") String productName,
                           @RequestParam(required = false,defaultValue = "") Integer typeId,Model model){

        Map<String,Object> map = new HashMap<>();
        map.put("pageNo",pageNo);
        map.put("productName",productName);
        map.put("typeId",typeId);
        PageInfo<Kaola> pageInfo = kaolaService.findByAllQueryWithType(map);
        List<kaolaType> kaolaTypes = kaolaTypeService.findAll();
        model.addAttribute("types",kaolaTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }
    @GetMapping("/new")
    public String addProduct(Model model){
        List<kaolaType> kaolaTypes = kaolaTypeService.findAll();
        model.addAttribute("kaolaTypes",kaolaTypes);
        return "product/new";
    }
    @PostMapping("/new")
    public String addProduct(Kaola kaola, RedirectAttributes redirectAttributes){
        kaolaService.save(kaola);
        redirectAttributes.addFlashAttribute("message","新增商品成功");
        return "redirect:/product";
    }
    @GetMapping("/{id:\\d+}")
    public String showProduct(@PathVariable Integer id,Model model){
        Kaola kaola = kaolaService.findById(id);
        model.addAttribute("kaola",kaola);
        return "product/show";

    }
    @GetMapping("/{id:\\d+}/edit")
    public String editProduct(@PathVariable Integer id,Model model){
        List<kaolaType> kaolaTypes = kaolaTypeService.findAll();
        Kaola kaola = kaolaService.findById(id);
        model.addAttribute("kaola",kaola);
        model.addAttribute("types",kaolaTypes);
        return "product/edit";
    }
    @PostMapping("/{id:\\d+}/edit")
    public String editProduct(Kaola kaola,RedirectAttributes redirectAttributes){
        kaolaService.editKaola(kaola);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/product/"+kaola.getId();
    }
    @GetMapping("/{id:\\d+}/delete")
    public String deleProduct(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        kaolaService.deleteByid(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/product";
    }
}
