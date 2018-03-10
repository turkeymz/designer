package com.chaimao.designer.service;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 15:56 2018/3/10
 * @Modified By:
 */
public interface MessageService {
    String sendVerificationCode(String jsonStr) throws Exception;
}
