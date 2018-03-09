package com.chaimao.designer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by turkeymz on 2017/8/6.
 */
@Controller
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

    /**
     * 验证码生成
     *
     * @param request
     * @param response
     * @throws Exception
     */
/*    @RequestMapping("/vcode.do")
    @ResponseBody
    public void getVcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);

        Calendar calendar = Calendar.getInstance();
        String key = CommonUtil.getUUID();
        String code = vCode.getCode().toLowerCase();
        long times = calendar.getTimeInMillis();
        System.out.println("key为：" + key + "新验证码为" + vCode.getCode().toLowerCase() + "时间为：" + calendar.getTimeInMillis());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        vCode.write(baos);
        byte[] bytes = baos.toByteArray();
        String imgString = Base64.encodeBase64String(bytes);

        String sReturn = "{s:1,key:'" + key + "',img:'" + imgString + "'}";
        response.getOutputStream().write((CommonUtil.encrypt(sReturn, CommonUtil.getIV())).getBytes());
    }*/


}
