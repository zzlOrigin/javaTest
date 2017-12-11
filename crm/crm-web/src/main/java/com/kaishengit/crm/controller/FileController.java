package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.Exception.NotFoundException;
import com.kaishengit.crm.entity.File;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.service.FileService;
import com.kaishengit.web.result.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController extends BasicController{

    @Autowired
    private FileService fileService;

    /**
     * 返回文件列表,先根据传过来的id,当做父ID进行查找,当初始进入文件列表时,pId为零.
     * 再判断Pid是否为零,如果不是零,把Pid当做id进行查找文件,当做文件列表的父Id,用来返回上级
     * 如果查找文件列表为空时,根据Id查找,看文件是否存在,是不是file类型的文件,还是空文件夹
     * 当查找的文件列表为空时,要判断
     * @param pid
     * @param model
     * @return
     */
    @GetMapping
    public String file(@RequestParam(required = false,defaultValue = "0") Integer pid, Model model){
        List<File> files = fileService.findAllByPid(pid);
        if (pid != 0){
            File file = fileService.findById(pid);
            model.addAttribute("file",file);
        }
            model.addAttribute("files",files);
        return "file/file";
    }
    @PostMapping("/new/folder")
    @ResponseBody
    public AjaxResult addFolder(@RequestParam(required = false,defaultValue = "0") Integer pid, String name, HttpSession httpSession){
        Staff staff = getStaff(httpSession);
        fileService.addFolder(staff,pid,name);
        List<File> files = fileService.findAllByPid(pid);
        return AjaxResult.successWithData(files);
    }
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(HttpSession httpSession,MultipartFile file,Integer pid) throws IOException {
        if (file.isEmpty()){
            return AjaxResult.error("文件不可缺少");
        }
        Staff staff = getStaff(httpSession);
        //获取文件输入流
        InputStream inputStream = file.getInputStream();
        //获取文件大小
        long fileSize = file.getSize();
        //获取文件真正的名称
        String fileName = file.getOriginalFilename();
        fileService.localNewFile(inputStream,fileSize,fileName,staff,pid);
        List<File> files = fileService.findAllByPid(pid);
        return AjaxResult.successWithData(files);
    }
    @GetMapping("/download")
    public void downLoadFile(@RequestParam Integer id,
                             @RequestParam(required = false,defaultValue = "") String fileName,
                             HttpServletResponse response){
        try{
            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = fileService.downFileById(id);
            if (StringUtils.isNotEmpty(fileName)){
                response.setContentType("application/octet-stream");
                fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
                response.addHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");

            }
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
            throw new NotFoundException("该文件不存在");
        }
    }
    @GetMapping("/dele")
    @ResponseBody
    public AjaxResult deleFile(Integer id){
        File file = fileService.findById(id);
        if ("dir".equals(file.getType())){
            List<File> files = fileService.findAllByPid(id);
            if (files.isEmpty()){
                return AjaxResult.error("文件夹下面有子文件");
            }
        }
        fileService.deleFileById(id);
        List<File> files = fileService.findAllByPid(file.getPid());
        return AjaxResult.successWithData(files);
    }
}
