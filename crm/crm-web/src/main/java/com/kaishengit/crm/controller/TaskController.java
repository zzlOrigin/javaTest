package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Remind;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.service.RemindService;
import com.kaishengit.web.result.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController extends BasicController{

    @Autowired
    private RemindService remindService;
    @GetMapping("/done/list")
    public String taskList(@RequestParam(required = true,defaultValue = "1") Integer pageNo,
                           HttpSession httpSession, Model model){
        Staff staff = getStaff(httpSession);
        PageInfo<Remind> reminds = remindService.findAllByStaffId(staff,pageNo);
        model.addAttribute("reminds",reminds);
        return "tesk/task";
    }
    @GetMapping("/new")
    public String addtask(){
        return "tesk/newtask";
    }

    @GetMapping("/new/cust/{id:\\d+}")
    public String addCustTask(Model model,@PathVariable Integer id){
        model.addAttribute("custId",id);
        return "tesk/newtask";
    }
    @GetMapping("/new/chance/{id:\\d+}")
    public String addChanceTask(@PathVariable Integer id,Model model){
        model.addAttribute("chanceId",id);
        return "tesk/newtask";
    }
    @PostMapping("/new")
    public String addtask(Remind remind,HttpSession httpSession){
        Staff staff = getStaff(httpSession);
        try{
            remind.setStaffId(staff.getId());
            remindService.saveNewRemind(remind);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/task/done/list";
    }
    @GetMapping("/dele/{id:\\d+}")
    public String deleById(@PathVariable Integer id){
        remindService.deleById(id);
        return "redirect:/task/done/list";
    }
    @GetMapping("/done")
    @ResponseBody
    public AjaxResult doneTesk(Integer id,HttpSession httpSession){
        Staff staff = getStaff(httpSession);
        remindService.doneRemind(id);
        PageInfo<Remind> reminds = remindService.findAllByStaffId(staff,1);
        return AjaxResult.successWithData(reminds);
    }
}
