package com.chaimao.designer.util;

import com.chaimao.designer.config.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author: cmxu
 * @Description: 短信发送接口
 * @Date： create in 14:25 2018/3/10
 * @Modified By:
 */
@Component
@Slf4j
public class SendMessage {
    @Autowired
    SysConfig sysConfig;

    /**
     *
     * @param phoneNo 电话号码
     * @param content 短信内容
     * @return success:成功/其他:失败
     */
    public String send(String phoneNo, String content) {
        log.info("【短信发送】发送号码：{},发送内容：",phoneNo,content);
        HashMap<String, String> msmConfig = sysConfig.getMessage();
        //String time="2016-09-06 17:48:22";//定时信息所需参数时间格式为yyyy-MM-dd HH:mm:ss
        String xh = "";
        String tkey = TimeUtil.getNowTime("yyyyMMddHHmmss");

        try {
            content = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param = "url=" + msmConfig.get("url") + "&username=" + msmConfig.get("username") +
                "&password=" + MD5Gen.getMD5(MD5Gen.getMD5(msmConfig.get("password")) + tkey) +
                "&tkey=" + tkey + "&mobile=" + phoneNo + "&content=" + content + "&xh=" + xh;
        //String param="url="+url+"&username="+username+"&password="+MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&time="+time+"&mobile="+mobile+"&content="+content+"&xh="+xh;//定时信息url输出
        String result = HttpRequest.sendPost(msmConfig.get("url"), param);//定时信息只可为post方式提交

        if(!result.split(",")[0].equals("1")){
            return result.split(",")[1];
        }

        return "success";
    }
}
