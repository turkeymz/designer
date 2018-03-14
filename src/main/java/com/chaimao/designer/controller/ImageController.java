package com.chaimao.designer.controller;

import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.service.ArtService;
import com.chaimao.designer.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @Author: cmxu
 * @Description: 图片控制层
 * @Date： create in 21:13 2018/3/11
 * @Modified By:
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    ArtService artService;

    @RequestMapping(value = "/uploadImage.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, String sJson, String sToken) {
        try {
            // 防止中文乱码
            request.setCharacterEncoding("utf-8");

            log.info("sJson:={} ", sJson);
            log.info("sToken:={} ", sToken);

            if ("uploadHead".equals(sToken)) {
                //头像上传
                String result = this.createReturnJson(userInfoService.uploadHead(file, sJson)).toString();
                log.info(result);
                return this.createReturnJson(result);
            } else if ("uploadArt".equals(sToken)) {
                //上传作品
                String result = this.createReturnJson(artService.createArt(file, sJson)).toString();
                log.info(result);
                return this.createReturnJson(result);
            } else {
                throw new RuntimeException("输入有误!");
            }
        } catch (Exception e) {
            JSONObject result_json = new JSONObject();

            e.printStackTrace();
            String data = e.getMessage();// 错误信息
            result_json.put("data", data);
            result_json.put("message", "请求失败!");
            result_json.put("state", "-1");
            return result_json;
        }
    }

    //跳转到上传文件的页面
    @RequestMapping(value = "/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    private JSONObject createReturnJson(Object result) throws Exception {
        String data = "";
        String message = "请求失败!";
        String state = "-1";
        JSONObject result_json = new JSONObject();
        // HashMap<String,Object > result_json = new HashMap<>();
        try {
            message = "请求成功";
            state = "0";
            result_json.put("data", result);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json;
        } catch (Exception e) {
            e.printStackTrace();
            data = e.getMessage();// 错误信息
            result_json.put("data", data);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json;
        }
    }
}
