package com.chaimao.designer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chaimao.designer.config.SysConfig;
import com.chaimao.designer.entity.Art;
import com.chaimao.designer.mapper.ArtMapper;
import com.chaimao.designer.mapper.LikeMapper;
import com.chaimao.designer.service.ArtService;
import com.chaimao.designer.util.FileUtil;
import com.chaimao.designer.util.TimeUtil;
import com.chaimao.designer.util.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author: cmxu
 * @Description: 作品业务逻辑
 * @Date： create in 20:40 2018/3/11
 * @Modified By:
 */
@Service("artService")
@Slf4j
public class ArtServiceImpl implements ArtService {

    @Autowired
    ArtMapper artMapper;
    @Autowired
    SysConfig sysConfig;
    

    /**
     * 新增作品
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Art createArt(MultipartFile file, String jsonStr) throws Exception {
        log.info("【作品上传】{}",jsonStr);
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        String artName = jsonObject.getString("artname");//作品名称
        String recommend = (String) jsonObject.get("recommend");//作品简介
        String userno = jsonObject.getString("userno");//用户编号
        String username = jsonObject.getString("username");//用户名称
        //初始化变量
        String artno = TimeUtil.getCurrentTime() + "_" + userno;//作品号
        Date createTime = new Date();//创建时间
        //创建文件名称
        String fileName = "art_" + artno + "_" + userno + ".jpg";

        //初始化对象
        Art art = new Art();
        art.setArtno(artno);
        art.setArtname(artName);
        art.setRecommend(recommend);
        art.setUserno(userno);
        art.setUsername(username);
        art.setArturl(sysConfig.getHeadurl() + fileName);
        art.setCreatetime(createTime);
        //保存作品
        Art re_art = artMapper.createArt(art);
        if(re_art == null){
            log.error("【作品上传】作品上传失败.");
            throw new RuntimeException("【作品上传】作品上传失败.");
        }
        //上传文件至服务器
        FileUtil.uploadFile(file.getBytes(), sysConfig.getHeadurl(), fileName);

        return re_art;
    }

    /**
     * 删除作品
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String deleteArt(String jsonStr) throws Exception {
        log.info("【作品删除】{}", jsonStr);
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String artno = jsonObject.getString("artno");//作品号

        int count = artMapper.deleteArt(artno);
        if(count == 0){
            log.error("【作品删除】作品删除失败.");
            throw new RuntimeException("【作品删除】作品删除失败.");
        }

        return "删除成功.";
    }

    /**
     * 更改作品信息
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public String updateArt(String jsonStr) throws Exception {
        Art art = JSONObject.parseObject(jsonStr,Art.class);
        int count = artMapper.updateArt(art);
        if(count == 0){
            log.error("【作品更新】作品更新失败.");
            throw new RuntimeException("【作品更新】作品更新失败.");
        }
        return "更新成功.";
    }

    /**
     * 根据用户获编号取作品列表
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public List<Art> getArtByUserno(String jsonStr) throws Exception {
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userno = jsonObject.getString("userno");//用户编号
        //获取列表
        List<Art> artList = artMapper.getArtByUserno(userno);
        return artList;
    }

    /**
     * 根据作品编号获取作品详情
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @Override
    public Art getArtByNo(String jsonStr) throws Exception {
        //获取参数
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String artno = jsonObject.getString("artno");//用户编号
        //获取详情
        Art art = artMapper.getArtByNo(artno);
        return art;
    }
}
