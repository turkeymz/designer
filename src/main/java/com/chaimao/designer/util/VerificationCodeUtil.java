package com.chaimao.designer.util;

import java.util.Random;

/**
 * @Author: cmxu
 * @Description: 验证码工具类
 * @Date： create in 16:01 2018/3/10
 * @Modified By:
 */
public class VerificationCodeUtil {

    /**
     * 生成四位随机数验证码
     * @return
     */
    public static String createFourBitCode(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return create(str);
    }

    /**
     * 生成4位数字
     * @return
     */
    public static String createFourNo(){
        String str="0123456789";
        return create(str);
    }

    private static String create(String rule){
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=rule.charAt(new Random().nextInt(rule.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
