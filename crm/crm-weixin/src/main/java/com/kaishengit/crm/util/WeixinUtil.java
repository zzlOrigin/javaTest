package com.kaishengit.crm.util;


import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kaishengit.crm.util.Exception.WeixinException;
import com.sun.javafx.collections.MappingChange;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class WeixinUtil {

    public static final String ACCESSTOKEN_TYPE_NOPMAL = "normal";
    public static final String ACCESSTOKEN_TYPE_CONTACTS = "contects";

    /**
     * 获取Access_token的值
     */
    private static final String GET_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    /**
     * 获取通讯录里面的Token的值
     */
    private static final String POST_DEPT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=%s";
    /**
     * 删除部门的URL
     */
    private static final String DELE_DEPT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=%s&id=%s";

    /**
     * 创建成员的URL
     */
    private static final String ADD_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=%s";
    /**
     * 删除成员的URL
     */
    private static final String DELE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=%s&userid=%s";
    /**
     * 发送消息
     */
    private static final String MESSAGE_POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";
    @Value("${gropId}")
    private String corpind;
    @Value("${Secret}")
    private String secret;
    @Value("${ApiSecret}")
    private String accessToken;
    @Value("${AgentId}")
    private String agentId;
    /**
     * AccessToken的缓存
     */
    private LoadingCache<String,String> accessTokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(7200, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String type) throws Exception {
                    String url;
                    if (ACCESSTOKEN_TYPE_CONTACTS.equals(type)){
                        url = String.format(GET_ACCESS_TOKEN_URL, corpind,accessToken);
                    } else {
                        url = String.format(GET_ACCESS_TOKEN_URL,corpind,secret);
                    }

                    String resultJson = sendHttpGetRequest(url);
                    Map<String,Object> map = JSON.parseObject(resultJson, HashMap.class);
                    if (map.get("errcode").equals(0)){

                        return (String) map.get("access_token");
                    }
                    throw new WeixinException(resultJson);
                }
            });

    /**
     * 获取AccessToken
     * @param type 获取AccessToken的类型，
     * @return
     */
    public String getAccessToken(String type){
        try {
            return accessTokenCache.get(type);
        }catch (ExecutionException e){
            throw new RuntimeException("获取AccessToken异常",e);
        }
        }
    /**
     * 创建部门
     *
      */
    public void createDept(Integer id,Integer parentId,String name) throws ExecutionException {
        //System.out.println(accessTokenCache.get("type"));
        String url = String.format(POST_DEPT_URL,getAccessToken(ACCESSTOKEN_TYPE_CONTACTS));
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("name",name);
        data.put("parentid",parentId);
        data.put("id",id);

        String resultJson = sendHttpPostRequest(url,JSON.toJSONString(data));
        Map<String,Object> resultmap = JSON.parseObject(resultJson,HashMap.class);
        if (!resultmap.get("errcode").equals(0)){
            throw new WeixinException("创建部门失败:"+resultJson);
        }
    }

    /**
     * 删除部门
     * @param id 传过来部门ID
     */
    public void deleDept(Integer id){
        String url = String.format(DELE_DEPT_URL,getAccessToken(ACCESSTOKEN_TYPE_CONTACTS),id);
        String result = sendHttpGetRequest(url);
        Map<String,Object> resultMap = JSON.parseObject(result,HashMap.class);
        if (!resultMap.get("errcode").equals(0)){
            throw new WeixinException("删除部门失败:"+result);
        }
    }

    /**
     * 创建一名员工
     * @param id 员工ID
     * @param name 员工NAME
     * @param mobile 员工的手机号
     * @param departmentIdList 员工的部门列表
     */
    public void addUser(Integer id, String name, String mobile, List<Integer> departmentIdList){
        String url = String.format(ADD_USER_URL,getAccessToken(ACCESSTOKEN_TYPE_CONTACTS));
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userid",id);
        data.put("name",name);
        data.put("mobile",mobile);
        data.put("department",departmentIdList);

        String resultJson = sendHttpPostRequest(url,JSON.toJSONString(data));
        Map<String,Object> resultMap = JSON.parseObject(resultJson,HashMap.class);
        if (!resultMap.get("errcode").equals(0)){
            throw new WeixinException("创建账号失败:"+resultJson);
        }
    }

    /**
     * 删除员工
     * @param id 员工Id
     */
    public void deleUser(Integer id){
        String url = String.format(DELE_USER_URL,getAccessToken(ACCESSTOKEN_TYPE_CONTACTS),id);
        String result = sendHttpGetRequest(url);
        Map<String,Object> resultMap = JSON.parseObject(result,HashMap.class);
        if (!resultMap.get("errcode").equals(0)){
            throw new WeixinException("删除账号失败:"+result);
        }

    }

    /**
     * 发送文本消息
     * @param id 需要传的员工ID
     * @param message 需要传的消息
     */
    public void sendMessage(List<Integer> id,String message){
        String url = String.format(MESSAGE_POST_URL,getAccessToken(ACCESSTOKEN_TYPE_NOPMAL));
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer userId:id){
            stringBuilder.append(userId).append("|");
        }
        String idString = stringBuilder.toString();
        idString = idString.substring(0,idString.lastIndexOf("|"));
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("touser","@all");
        data.put("msgtype","text");
        data.put("agentid",agentId);

        Map<String,String> messageMap = new HashMap<String, String>();
        messageMap.put("content",message);
        data.put("text",messageMap);

        String resultJson = sendHttpPostRequest(url,JSON.toJSONString(data));

        Map<String,Object> resultMap = JSON.parseObject(resultJson,HashMap.class);
        if (!resultMap.get("errcode").equals(0)){
            throw new WeixinException("发送文本消息失败:"+resultJson);
        }
    }
    /**
     * 发出Http的Get请求
     * okHttp
     */
    private String sendHttpGetRequest(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException ex){
            throw new RuntimeException("Http请求异常",ex);
        }
    }
    private String sendHttpPostRequest(String url,String json){
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e){
            throw new RuntimeException("Http请求异常",e);
        }
    }
}
