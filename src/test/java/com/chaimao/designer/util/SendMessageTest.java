package com.chaimao.designer.util;

import com.chaimao.designer.DesignerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 14:58 2018/3/10
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesignerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SendMessageTest {
    @Autowired
    SendMessage sendMessage;

    @Test
    public void send() throws Exception {
        System.out.println("message: "+sendMessage.send("13702380392","【柴猫科技】5784"));
    }

}