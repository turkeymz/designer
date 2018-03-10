package com.chaimao.designer.service;

import com.chaimao.designer.resp.RespRelationsCount;
import com.chaimao.designer.resp.RespUserList;

import java.util.List;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 21:10 2018/3/10
 * @Modified By:
 */
public interface RelationsService {

    RespRelationsCount getCount(String jsonStr) throws Exception;

    List<RespUserList> getFollowUser(String jsonStr) throws Exception;

    List<RespUserList> getFansUser(String jsonStr) throws Exception;

    String follow(String jsonStr) throws Exception;

    String cancelFollow(String jsonStr) throws Exception;
}
