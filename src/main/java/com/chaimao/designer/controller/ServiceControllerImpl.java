package com.chaimao.designer.controller;



import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.cache.ServiceMethodCache;
import com.chaimao.designer.cache.ServiceMethodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by turkeymz on 2017/8/6.
 */
@Service("serviceControllerImpl")
public class ServiceControllerImpl {

    @Autowired
    private ApplicationContext context;

    private String getService(String jsonStr, String type) throws Exception {
        String result = "";
        ServiceMethodEntity sm = ServiceMethodCache.map.get(type);
        String className = sm.getClassName();
        String methodName = sm.getMethodName();
        //利用spring工具类反射进java
        Object o = context.getBean(className);
        Method m = ReflectionUtils.findMethod(o.getClass(), methodName, String.class);
        result = (String) ReflectionUtils.invokeMethod(m, o, jsonStr);

        return result;
    }

    public String taskDistribution(String type, String jsonStr) {
        String data = "";
        String message = "请求失败!";
        String state = "-1";
        JSONObject result_json = new JSONObject();
        try {
            data = this.getService(jsonStr, type);//开始进行任务分发
            message = "请求成功";
            state = "0";
            result_json.put("data", data);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json.toString();

        } catch (Exception e) {
            e.printStackTrace();
            data = e.getMessage();// 错误信息
            result_json.put("data", data);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json.toString();
        }
    }

}
