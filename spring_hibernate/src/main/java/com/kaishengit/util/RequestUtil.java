package com.kaishengit.util;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RequestUtil {

    private String requestName;

    private String method;

    private Object value;

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static List<RequestUtil> doUrl(HttpServletRequest httpServletRequest){
        List<RequestUtil> requestUtils = new ArrayList<>();
        Enumeration<String> enumeration =httpServletRequest.getParameterNames();
        while (enumeration.hasMoreElements()){
            //p_eq_bd_price_or_marketPrice
            String urlKey = enumeration.nextElement();
            String urlvalue = httpServletRequest.getParameter(urlKey);
            if (!urlKey.startsWith("p_") || "".equals(urlvalue) || urlvalue == null){

            }else{
                String[] keys = urlKey.split("_",4);
                if (keys == null || keys.length < 4){
                    throw new RuntimeException("查询条件异常");
                }
                RequestUtil requestUtil = new RequestUtil();
                requestUtil.setRequestName(keys[3]);
                requestUtil.setMethod(keys[1]);
                requestUtil.setValue(tranValue(keys[2],urlvalue));
                requestUtils.add(requestUtil);
            }

        }
        return requestUtils;
    }

    public static Object tranValue(String valueType,String value){
        if ("s".equals(valueType)){
            return value;
        }else if ("bd".equals(valueType)){
            return new BigDecimal(value);
        }else if ("in".equals(valueType)){
            return Integer.valueOf(value);
        }else if ("f".equals(valueType)){
            return Float.valueOf(value);
        }else if ("d".equals(valueType)){
            return Double.valueOf(value);
        }
        return null;
    }
}
