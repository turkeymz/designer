package com.chaimao.designer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.mapper.UserInfoMapper;
import com.chaimao.designer.entity.UserInfo;
import com.chaimao.designer.service.UserInfoService;
import com.chaimao.designer.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 15:38 2018/3/10
 * @Modified By:
 */
@Service("userInfoService")
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 用户注册
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo createUser(String jsonStr) throws Exception {
        log.info("【用户注册】{}", jsonStr);
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String phoneno = jsonObject.getString("phoneno");//手机号
        String userno = TimeUtil.getCurrentTime();//用户号(时间戳)
        String password = jsonObject.getString("password");//密码

        //写入数据 //创建用户
        int count = userInfoMapper.createUserInfo(userno, password, userno, phoneno);
        if (count == 0) {
            log.error("【用户注册】{}", "用户注册失败.");
            throw new Exception("用户注册失败.");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserno(userno);
        List<UserInfo> re_User = userInfoMapper.selectUser(userInfo);
        return re_User.get(0);
    }

    /**
     * 登录
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo login(String jsonStr) throws Exception {
        log.info("【用户登录】{}", jsonStr);
        List<UserInfo> re_userList = null;
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String phoneno = (String) jsonObject.get("phoneno");//手机号
        String userno = (String) jsonObject.get("userno");//用户编号
        String password = jsonObject.getString("password");//密码
        //初始化查询对象
        UserInfo userInfo = new UserInfo();
        //设置查询对象
        if (phoneno != null && !"".equals(phoneno)) userInfo.setPhoneno(phoneno);
        if (userno != null && !"".equals(userno)) userInfo.setUserno(userno);
        userInfo.setPassword(password);
        //查询
        re_userList = userInfoMapper.selectUser(userInfo);
        //登录失败
        if (userInfo == null || re_userList.size() == 0) {
            log.error("【用户注册】{}", "登录失败,请确认用户名或密码是否正确.");
            throw new Exception("登录失败,请确认用户名或密码是否正确!");
        }
        //返回
        return re_userList.get(0);
    }

    /**
     * 修改
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String change(String jsonStr) throws Exception {
        log.info("【用户更新】{}", jsonStr);
        List<UserInfo> re_userList = null;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        UserInfo userInfo = JSONObject.parseObject(jsonStr, UserInfo.class);

        int count = userInfoMapper.updateUser(userInfo);
        if (count == 0) {
            log.error("【用户更新】{}", "更新失败.");
            throw new Exception("更新失败");
        }
        return "更新成功";
    }

    /**
     * 用户查询
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo getUser(String jsonStr) throws Exception {
        log.info("【用户查询】{}", jsonStr);
        List<UserInfo> re_userList = null;
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        UserInfo userInfo = JSONObject.parseObject(jsonStr, UserInfo.class);
        //查询用户
        re_userList = userInfoMapper.selectUser(userInfo);
        if (re_userList == null || re_userList.size() == 0) {
            log.error("【用户查询】用户查询是失败");
            throw new Exception("用户查询失败");
        }
        return re_userList.get(0);
    }

}
