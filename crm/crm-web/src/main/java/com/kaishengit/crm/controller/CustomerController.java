package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.auth.ShiroUtil;
import com.kaishengit.crm.entity.*;
import com.kaishengit.crm.exception.CustomerException;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.RecodeService;
import com.kaishengit.crm.service.RemindService;
import com.kaishengit.crm.service.StaffService;


import com.kaishengit.web.result.AjaxResult;
import com.sun.javafx.collections.MappingChange;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BasicController{

    @Autowired
    private CustomerService customerService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private RecodeService recodeService;
    @Autowired
    private RemindService remindService;
    @GetMapping("/my")
    public String myCust(@RequestParam(required = false,defaultValue = "1") Integer pageNo,
                         Model model,HttpSession httpSession){
        Staff staff = getStaff(httpSession);
        PageInfo<Customer> customers = customerService.findAllCust(staff,pageNo);
        model.addAttribute("customers",customers);
        return "customer/mycustomer";
    }

    @GetMapping("/add")
    public String addNewCust(){
        return "customer/addCustomer";
    }

    @PostMapping("/add")
    public String saveCust(HttpSession httpSession, Customer customer, RedirectAttributes redirectAttributes){
        Staff staff = getStaff(httpSession);
        customer.setStaffId(staff.getId());
        customer.setSetTime(new Date());
        customerService.saveCust(customer);
        redirectAttributes.addFlashAttribute("state","添加客户成功");
        return "redirect:/customer/my";
    }

    @GetMapping("/addMy/{id:\\d+}")
    public String publicToMyCustomer(@PathVariable Integer id){
        Customer customer = findCustomerByStaffId(null,id);
        Staff staff = ShiroUtil.getStaff();
        customer.setStaffId(staff.getId());
        customer.setReminder(customer.getReminder()+"-->"+staff.getUserName()+"把"+customer.getCustName()+"客户从公海捞起");
        String arg = staff.getUserName()+"把"+customer.getCustName()+"从公海捞起";
        String type = "意向";
        doChance(staff,customer,arg,type);
        customerService.updateCust(customer);
        return "redirect:/customer/my";
    }
    @GetMapping("/find/{id}")
    public String findOne(@PathVariable Integer id,HttpSession httpSession,Model model){
        Customer customer = findCustomerByStaffId(httpSession,id);
        List<Staff> staffList = staffService.findAllStaff();
        List<Chance> chances = recodeService.findAllChance(id);
        List<Remind> reminds = remindService.findAllByCustId(id);
        model.addAttribute("reminds",reminds);
        model.addAttribute("chances",chances);
        model.addAttribute("staffs",staffList);
        model.addAttribute("customer",customer);
        return "/customer/customer";
    }
    @GetMapping("/public/find/{id:\\d+}")
    public String findPulicOne(@PathVariable Integer id, Model model){
        Customer customer = findCustomerByStaffId(null,id);
        List<Chance> chances = recodeService.findAllChance(id);
        List<Remind> reminds = remindService.findAllByCustId(id);
        model.addAttribute("reminds",reminds);
        model.addAttribute("chances",chances);
        model.addAttribute("customer",customer);
        return "/customer/publicCustomer";
    }

    @GetMapping("/public")
    public String pulicCusts(@RequestParam(required = false,defaultValue = "1") Integer pageNo,Model model){
        PageInfo<Customer> customers = customerService.findAllPublic(pageNo);
        model.addAttribute("pages",customers);
        return "customer/publicCustomerList";
    }
    @GetMapping("/my/{id:\\d+}/public")
    public String publicToCust(@PathVariable Integer id,HttpSession httpSession,RedirectAttributes redirectAttributes){
        Customer customer = findCustomerByStaffId(httpSession,id);
        customer.setStaffId(0);
        Staff staff = ShiroUtil.getStaff();
        String arg = staff.getUserName()+"把"+customer.getCustName()+"放入公海";
        String type = "暂时搁置";
        doChance(staff,customer,arg,type);
        customerService.updateCust(customer);
        redirectAttributes.addFlashAttribute("state","客户放入公海成功");
        return "redirect:/customer/my";
    }

    @GetMapping("/edit/{id:\\d+}")
    public String editOne(@PathVariable Integer id,Model model,HttpSession httpSession){
        Customer customer = findCustomerByStaffId(httpSession,id);
        model.addAttribute("customer",customer);
        return "customer/editCustomer";
    }

    @GetMapping("/my/{Cid:\\d+}/tran/{Sid:\\d+}")
    public String tranCust(@PathVariable Integer Cid,@PathVariable Integer sid,HttpSession httpSession,RedirectAttributes redirectAttributes){
        Customer customer = findCustomerByStaffId(httpSession,Cid);
        customerService.tranCust(customer,sid);
        redirectAttributes.addFlashAttribute("state","修改客户成功");
        return "redirect:/customer/my";
    }
    @PostMapping("edit")
    public String editOneById(Customer customer,HttpSession httpSession){
        Customer customer1 = findCustomerByStaffId(httpSession,customer.getId());
        customerService.updateCust(customer);
        return "redirect:/customer/my";
    }


    @GetMapping("/dele/{id:\\d+}")
    public String deleById(@PathVariable Integer id,HttpSession httpSession,RedirectAttributes redirectAttributes){
        Customer customer = findCustomerByStaffId(httpSession,id);
        customerService.deleCust(customer);
        redirectAttributes.addFlashAttribute("state","删除客户成功");
        return "redirect:/customer/my";
    }

    @GetMapping("/my/export.csv")
    public void exportCsv(HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException{
        Staff staff = getStaff(httpSession);

        httpServletResponse.setContentType("text/csv;charset=GBK");
        String fileName = new String("我的客户.csv".getBytes("UTF-8"),"ISO8859-1");
        httpServletResponse.addHeader("Content-Disposition","attachment; fileName=\""+fileName+"\"");

        OutputStream outputStream = httpServletResponse.getOutputStream();
        customerService.exportCsvFileToOutputStream(outputStream,staff);
    }
    @GetMapping("/my/export.xls")
    public void exportxls(HttpServletResponse response,HttpSession httpSession) throws IOException{
        Staff staff = getStaff(httpSession);
        response.setContentType("application/vnd.ms-excel");
        String fileName = new String("我的客户.xls".getBytes("UTF-8"),"ISO8859-1");
        response.addHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
        OutputStream outputStream = response.getOutputStream();
        customerService.exportXlsFileToOutputStream(outputStream,staff);
    }

    @GetMapping("/staticCustNum")
    @ResponseBody
    public AjaxResult addCustNum(HttpSession httpSession){
        Staff staff = getStaff(httpSession);
        List<Map<String,Object>> custNum = customerService.findAllCustForEch(staff);
        return AjaxResult.successWithData(custNum);
    }

    public void doChance(Staff staff,Customer customer,String arg,String type){
        Chance chance = new Chance();
        chance.setName(arg);
        chance.setProgress(type);
        chance.setCreation(new Date());
        chance.setFollow(new Date());
        chance.setId(customer.getId());
        chance.setStaffId(staff.getId());
        recodeService.addChance(chance);
    }
}
