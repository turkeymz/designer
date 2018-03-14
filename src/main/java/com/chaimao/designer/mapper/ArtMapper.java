package com.chaimao.designer.mapper;

import com.chaimao.designer.entity.Art;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: cmxu
 * @Description: 作品持久层
 * @Date： create in 19:13 2018/3/11
 * @Modified By:
 */
@Mapper
public interface ArtMapper {

    //创建作品
    @Insert("insert into art (id,artno,artname,recommend,arturl,userno,username,createtime)" +
            " values (#{art.id},#{art.artno},#{art.artname},#{art.recommend},#{art.arturl},#{art.userno},#{art.username},#{art.createtime})")
    @Options(useGeneratedKeys = true, keyProperty = "art.id")
    Art createArt(@Param("art")Art art);
    //更新作品
    @Update("<script> " +
            "update art <set> " + set + "</set>" +
            "where artno = #{artno}" +
            "</script> ")
    int updateArt(@Param("art") Art art);
    //删除作品
    @Delete("delete from art where artno = #{artno}")
    int deleteArt(@Param("artno") String artno);
    //查看用户作品列表
    @Select("select * from art where userno = #{userno}")
    List<Art> getArtByUserno(@Param("userno") String userno);
    //查询单笔作品
    @Select("select * from art where artno = #{artno}")
    Art getArtByNo(@Param("artno") String artno);

    String where = "<if test=\"id != null\"> and id=#{id}</if>" +
            "<if test=\"artno != null\"> and artno=#{artno}</if>" +
            "<if test=\"artname != null\"> and artname=#{artname}</if>"+
            "<if test=\"recommend != null\"> and recommend=#{recommend}</if>" +
            "<if test=\"arturl != null\"> and arturl=#{arturl}</if>" +
            "<if test=\"userno != null\"> and userno=#{userno}</if>" +
            "<if test=\"username != null\"> and username=#{username}</if>" +
            "<if test=\"createtime != null\"> and createtime=#{createtime}</if>";

    String set = "<if test=\"id != null\"> id=#{id},</if>" +
            "<if test=\"artno != null\"> artno=#{artno},</if>" +
            "<if test=\"artname != null\"> artname=#{artname},</if>"+
            "<if test=\"recommend != null\"> recommend=#{recommend},</if>" +
            "<if test=\"arturl != null\"> arturl=#{arturl},</if>" +
            "<if test=\"userno != null\"> userno=#{userno},</if>"+
            "<if test=\"username != null\"> username=#{username},</if>" +
            "<if test=\"createtime != null\"> createtime=#{createtime},</if>";



}
