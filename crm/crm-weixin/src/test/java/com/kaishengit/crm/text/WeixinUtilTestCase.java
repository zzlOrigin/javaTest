package com.kaishengit.crm.text;

import com.kaishengit.crm.util.WeixinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-weixin.xml")
public class WeixinUtilTestCase {

    @Autowired
    private WeixinUtil weixinUtil;
    @Test
    public void addDept(){

        try {
            weixinUtil.createDept(14,1,"河南研发中心");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void deleDept(){
        weixinUtil.deleDept(14);
    }
    @Test
    public void addUser(){
        weixinUtil.addUser(5, "赵六", "45641545645", Arrays.asList(11));
    }
    @Test
    public void deleUser(){
        weixinUtil.deleUser(5);
    }
    @Test
    public void sendTextMessageToUser(){
        weixinUtil.sendMessage(Arrays.asList(0,11,12),"明天我也不知道放假");
    }
}
