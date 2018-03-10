package com.chaimao.designer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @Author: cmxu
 * @Description: 用户表
 * @Date： create in 14:37 2018/3/10
 * @Modified By:
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserInfo {
    //唯一识别
    private Integer id;
    //用户账号
    private String userno;
    //密码
    private String password;
    //用户名
    private String username;
    //年龄
    private Integer age;
    //地区
    private String area;
    //个性签名
    private String remark;
    //头像地址
    private String headurl;
    //电话号码
    private String phoneno;

    public void createUser(String userno, String password, String username, String phoneno) {
        this.userno = userno;
        this.password = password;
        this.username = username;
        this.phoneno = phoneno;
    }
}
