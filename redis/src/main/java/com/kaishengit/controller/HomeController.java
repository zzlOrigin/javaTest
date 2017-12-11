package com.kaishengit.controller;

import com.kaishengit.entity.Product;
import com.kaishengit.service.Exception.ServiceException;
import com.kaishengit.service.ProductService;
import com.kaishengit.util.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductService productService;
    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("products",productList);
        return "home";
    }

    @GetMapping("/new")
    public String add(){
        return "new";
    }
    @PostMapping("/new")
    public String addProduct(Product product,MultipartFile tupian, String sTime, String eTime){
        //Stirng类型转换时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime startTime = DateTime.parse(sTime,dateTimeFormatter);
        DateTime endTime = DateTime.parse(eTime,dateTimeFormatter);

        product.setStartTime(startTime.toDate());
        product.setEndTime(endTime.toDate());
        if (tupian == null && tupian.isEmpty()){
            productService.addNewProduct(product,null);
        }else{
            try {
                productService.addNewProduct(product,tupian.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }
    @GetMapping("/product/{id:\\d+}")
    public String findProduct(@PathVariable Integer id,Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product";
    }
    @GetMapping("/product/seckill/{id:\\d+}")
    @ResponseBody
    public AjaxResult killProduct(@PathVariable Integer id){
        try {
            productService.killProduct(id);
            return AjaxResult.success();
        }catch (ServiceException e){
            return AjaxResult.errorWithMessage(e.getMessage());
        }
    }

    @GetMapping("/image")
    public void image(String name, HttpServletResponse response){
        File file = new File("d:/test",name);
        try {
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
