package com.kaishengit.crm.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
@Component
public class WeixinConsumer {
    @Autowired
    private WeixinUtil weixinUtil;

    @JmsListener(destination = "test-message")
    public void sendMessageToUser(String json){
        Map<String,Object> map = JSON.parseObject(json, HashMap.class);
        weixinUtil.sendMessage(Arrays.asList((Integer)map.get("id")),(String) map.get("message"));
    }
}
