package com.chaimao.designer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.entity.UserInfo;
import com.chaimao.designer.mapper.RelationsMapper;
import com.chaimao.designer.mapper.UserInfoMapper;
import com.chaimao.designer.resp.RespRelationsCount;
import com.chaimao.designer.resp.RespUserList;
import com.chaimao.designer.service.RelationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cmxu
 * @Description: 关注粉丝逻辑
 * @Date： create in 21:17 2018/3/10
 * @Modified By:
 */
@Service("relationService")
@Slf4j
public class RelationsServiceImpl implements RelationsService{
    @Autowired
    RelationsMapper relationsMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     *  获取关注与粉丝数
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public RespRelationsCount getCount(String jsonStr) throws Exception {
        log.info("【获取关注与粉丝数】{}",jsonStr);
        //获取用户编号
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");
        //查询关注用户编号列表
        List<String> followNoList = relationsMapper.getFollow(userno);
        //查询粉丝用户编号列表
        List<String> fansNoList = relationsMapper.getFans(userno);
        RespRelationsCount respRelationsCount = new RespRelationsCount(followNoList.size(),fansNoList.size());
        return respRelationsCount;
    }

    /**
     * 获取关注用户列表
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public List<RespUserList> getFollowUser(String jsonStr) throws Exception {
        log.info("【获取关注用户列表】{}",jsonStr);
        //获取用户编号
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");
        //查询关注用户编号列表
        List<String> followNoList = relationsMapper.getFollow(userno);
        //查询用户列表详情
        List<UserInfo> userInfoList = userInfoMapper.selectUserByUsernoList(followNoList);
        //转换返回
        List<RespUserList> respUserList = userInfoList.stream().map(n -> new RespUserList(n.getUserno(),n.getUsername(),n.getHeadurl()))
                .collect(Collectors.toList());
        return respUserList;
    }

    /**
     * 获取粉丝列表
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public List<RespUserList> getFansUser(String jsonStr) throws Exception {
        log.info("【获取粉丝用户列表】{}",jsonStr);
        //获取用户编号
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");
        //查询关注用户编号列表
        List<String> followNoList = relationsMapper.getFans(userno);
        //查询用户列表详情
        List<UserInfo> userInfoList = userInfoMapper.selectUserByUsernoList(followNoList);
        //转换返回
        List<RespUserList> respUserList = userInfoList.stream().map(n -> new RespUserList(n.getUserno(),n.getUsername(),n.getHeadurl()))
                .collect(Collectors.toList());
        return respUserList;
    }

    /**
     * 关注
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String follow(String jsonStr) throws Exception {
        log.info("【关注用户】{}",jsonStr);
        //获取用户编号与关注号
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");
        String followno = jsonObject.getString("followno");
        //插入关联
        int count = relationsMapper.follow(followno,userno);

        if(count == 0){//关注失败
            log.error("【关注用户】{}", "关注用户失败.");
            throw new Exception("关注用户失败.");
        }
        return "关注成功";
    }

    /**
     * 取消关注
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String cancelFollow(String jsonStr) throws Exception {
        log.info("【取消关注用户】{}",jsonStr);
        //获取用户编号与关注号
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");
        String followno = jsonObject.getString("followno");
        //插入关联
        int count = relationsMapper.cancelFollow(followno,userno);

        if(count == 0){//关注失败
            log.error("【取消关注用户】{}", "取消关注用户失败.");
            throw new RuntimeException("取消关注用户失败.");
        }
        return "取消关注成功";
    }
}
