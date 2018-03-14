package com.chaimao.designer.service;

import com.chaimao.designer.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 15:04 2018/3/10
 * @Modified By:
 */
public interface UserInfoService {
    //创建用户
    UserInfo createUser(String jsonStr) throws Exception;

    //登录
    UserInfo login(String jsonStr) throws Exception;

    //修改用户信息
    String change(String jsonStr) throws Exception;

    //用户查询
    UserInfo getUser(String jsonStr) throws Exception;

    //更改头像
    String uploadHead(MultipartFile file,String jsonStr)throws Exception;
}
