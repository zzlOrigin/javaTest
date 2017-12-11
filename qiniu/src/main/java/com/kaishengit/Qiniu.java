package com.kaishengit;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

@Controller
public class Qiniu {

    @GetMapping("/")
    public String upload(Model model){
        String accessKey = "VtqbLMMNWNEI7pb0oXTA4_RVZr_NKrLAFu9kO-5o";
        String secretKey = "e0qvDHV7W0Ix-d5KHt_Ewn_oT8V30urjEBX7H3dI";

        String bucket = "zzllong";

        Auth auth = Auth.create(accessKey,secretKey);

        StringMap stringMap = new StringMap();
        stringMap.put("returnUrl","http://localhost:8080/upload/callback");

        String upToken = auth.uploadToken(bucket,null,3600,stringMap);
        model.addAttribute("upToken",upToken);

        return "upload";
    }
    @GetMapping("/upload/callback")
    public String uploadCallBack(String upload_ret){

        String jsonString = new String(Base64.getDecoder().decode(upload_ret));
        Map<String,Object> map = new HashMap<>();
        map = JSON.parseObject(jsonString,Map.class);
        System.out.println(jsonString);
        System.out.println("hash的值为--------->"+map.get("hash"));
        System.out.println("key的值为----------->"+map.get("key"));
        //生成Base64密码.要进行解密
       // System.out.println(upload_ret);
        return "redirect:/";
    }
}
