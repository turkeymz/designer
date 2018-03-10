package com.chaimao.designer.controller;

import com.chaimao.designer.config.SysConfig;
import com.chaimao.designer.util.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by turkeymz on 2017/8/6.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    @Autowired
    ServiceControllerImpl serviceController;


    @RequestMapping("/interface.do")
    @ResponseBody
    public String getInterface(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
        // 防止中文乱码
        request.setCharacterEncoding("utf-8");
        // 定义变量  sReturn:返回数据,sJson:收到数据,sToken:秘钥.
        String sReturn = "", sJson = "", sToken = "";
        // 接收请求参数
        sJson = request.getParameter("json").trim();
        sToken = request.getParameter("token");
        log.info("sJson:={} ", sJson);
        log.info("sToken:={} ", sToken);
        sReturn = serviceController.taskDistribution(sToken, sJson);
        log.info("sReturn:={} ", sReturn);
        // 返回
        return sReturn;
    }


}
