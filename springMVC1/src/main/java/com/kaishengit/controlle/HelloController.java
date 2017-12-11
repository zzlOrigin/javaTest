package com.kaishengit.controlle;

import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.service.StudentServlet;
import com.kaishengit.service.impl.StudentServletImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
public class HelloController {

    @Autowired
    private StudentServlet studentServlet;

    @GetMapping("/find/{id:\\d+}")
    @ResponseBody
    public Student student(@PathVariable Integer id){
        return studentServlet.findById(id);
    }


    @GetMapping("hello")
    public ModelAndView sayHello(){
        System.out.println("你好,Spring");
        //model.addAttribute("name","张三");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","lfsafhas ");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
    @GetMapping("hi")
    public String sayHe(){

        return "hi";
    }

    @PostMapping("hi")
    public String sayHi(String userName, String password, MultipartFile photo, RedirectAttributes redirectAttributes) {
        if(!photo.isEmpty()){
            String oldName = photo.getOriginalFilename();
            String name = "我的照片"+oldName.substring(oldName.lastIndexOf("."));
            try{
                InputStream input = photo.getInputStream();
                OutputStream output = new FileOutputStream("D:/test/"+name,true);
                byte[] bytes = new byte[1024];
                int len = -1;

                while ((len = input.read(bytes)) != -1){
                    output.write(bytes,0,len);
                }
                output.flush();
                output.close();
                input.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        redirectAttributes.addFlashAttribute("message","success");
        return "redirect:/hi";
    }

}
