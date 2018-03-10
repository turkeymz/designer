package com.chaimao.designer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.config.SysConfig;
import com.chaimao.designer.service.MessageService;
import com.chaimao.designer.util.SendMessage;
import com.chaimao.designer.util.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 15:56 2018/3/10
 * @Modified By:
 */
@Service("messageService")
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    SendMessage sendMessage;
    @Autowired
    SysConfig sysConfig;

    /**
     * 发送验证码
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String sendVerificationCode(String jsonStr) throws Exception {
        log.info("【短信发送】{}", jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        try {
            //初始化参数
            String phoneno = jsonObject.getString("phoneno");
            //初始化短信内容
            String code = VerificationCodeUtil.createFourBitCode();//生成验证码
            String content = sysConfig.getMessage().get("exampleStr").replace("#code#", code);
            //发送短信
            String result = sendMessage.send(phoneno, content);
            //判断成功与失败
            if (result.equals("success")) {
                //发送成功
                return code;
            } else {
                //发送失败
                log.error("发送失败，失败原因：" + result);
                throw new Exception("发送失败，失败原因：" + result);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
