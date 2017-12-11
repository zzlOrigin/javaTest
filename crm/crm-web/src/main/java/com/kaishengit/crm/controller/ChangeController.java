package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.Exception.ForbiddenException;
import com.kaishengit.crm.controller.Exception.NotFoundException;
import com.kaishengit.crm.controller.auth.ShiroUtil;
import com.kaishengit.crm.entity.*;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.RecodeService;
import com.kaishengit.crm.service.RemindService;
import com.kaishengit.web.result.AjaxResult;
import com.sun.corba.se.spi.ior.ObjectKey;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recode")
public class ChangeController extends BasicController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RecodeService recodeService;
    @Autowired
    private RemindService remindService;
    @GetMapping("/my")
    public String findAllChange(@RequestParam(required = false,defaultValue = "1") Integer pageNo, HttpSession httpSession, Model model){
        Staff staff = getStaff(httpSession);
        PageInfo<Chance> chancePageInfo = recodeService.findAllByStaffId(staff.getId(),pageNo);
        model.addAttribute("pages",chancePageInfo);
        return "sale/saleList";
    }
    @GetMapping("/findOne/{id:\\d+}")
    public String findOne(@PathVariable Integer id,Model model,HttpSession httpSession){
        Chance chance = verify(id, ShiroUtil.getStaff());
        List<Record> records = recodeService.findSalesChanceRecodeListBySalesId(id);
        List<Remind> reminds = remindService.findAllByChanceId(id);
        model.addAttribute("chance",chance);
        model.addAttribute("records",records);
        model.addAttribute("reminds",reminds   );
        List<String> strings = recodeService.findAllSalesProgress();
        model.addAttribute("sales",recodeService.findAllSalesProgress());
        return "sale/saleOne";
    }
    @GetMapping("/add")
    public String addChance(HttpSession httpSession,Model model){
        Staff staff = getStaff(httpSession);
        List<Customer> customers = customerService.findAllCust(staff.getId());
        model.addAttribute("customers",customers);
        return "sale/addSale";
    }
    @PostMapping("/add")
    public String saveChance(HttpSession httpSession, Chance chance, RedirectAttributes redirectAttributes){
        Staff staff = getStaff(httpSession);
        chance.setStaffId(staff.getId());
        redirectAttributes.addFlashAttribute("state","添加机会成功");

        recodeService.addChance(chance);
        return "redirect:/recode/my";
    }
    @GetMapping("/new/{id:\\d+}")
    public String addCustChance(@PathVariable Integer id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "sale/addSale";
    }
    @PostMapping("/new/{id:\\d+}")
    public String saveCustChance(Chance chance, RedirectAttributes redirectAttributes){
        Staff staff = getStaff(null);
        chance.setStaffId(staff.getId());
        redirectAttributes.addFlashAttribute("state","添加机会成功");
        recodeService.addChance(chance);
        return "redirect:/customer/find/"+chance.getCustId();
    }
    private Chance verify(Integer id,Staff staff){
        Chance chance = recodeService.findById(id);

        if (chance == null){
            throw new NotFoundException("该机会不存在");
        }
        if (chance.getStaffId() != staff.getId()){
            throw new ForbiddenException("该机会不是你的机会");
        }

        return chance;
    }

    @PostMapping("/my/new/record")
    public String newRecode(Integer saleId,String content){
        recodeService.addRecode(saleId,content);
        return "redirect:/recode/findOne/"+saleId;
    }

    @PostMapping("/my/chance/update")
    public String newProgress(Integer id,String progress){
        recodeService.updateProgress(id,progress);
        return "redirect:/recode/findOne/"+id;
    }
    @GetMapping("/dele/{id:\\d+}")
    public String deleById(@PathVariable Integer id){
        recodeService.deleById(id);
        return "redirect:/recode/my";
    }

    @GetMapping("/staticFunnel")
    @ResponseBody
    public AjaxResult staticChance(){
        List<Map<String,Object>> chances = recodeService.findStaticNumber();
        return AjaxResult.successWithData(chances);
    }

}
