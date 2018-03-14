package com.chaimao.designer.mapper;


import com.chaimao.designer.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: cmxu
 * @Description: 用户持久层
 * @Date： create in 14:48 2018/3/10
 * @Modified By:
 */
@Mapper
public interface UserInfoMapper {
    //注册(新增用户)
    @Insert("insert into user_info (userno,password,username,phoneno) values (#{userno},#{password},#{username},#{phoneno})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createUserInfo(@Param("userno") String userno, @Param("password") String password, @Param("username") String username, @Param("phoneno") String phoneno);

    //查询用户
    @Select("<script> " +
            "select * from user_info" +
            " <where>" + where + "</where>" +
            "</script> "
    )
    List<UserInfo> selectUser(UserInfo userInfo);

    //更新用户
    @Update("<script> " +
            "update user_info <set> " + set + "</set>" +
            "where userno = #{userno}" +
            "</script> ")
    public int updateUser(UserInfo user);

    //根据用户编号查询用户
    @Select("select * from user_info where userno = #{userno}")
    UserInfo getUserByNo(@Param("userno") String userno);

    //根据用户编号集合查询用户集合
    @Select("<script> " +
            "select * from user_info " +
            "where userno in " +
            "<foreach item='item' index='index' collection='usernoList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script> ")
    List<UserInfo> selectUserByUsernoList(@Param("usernoList") List<String> usernoList);


    String where = "<if test=\"id != null\"> and id=#{id}</if>" +
            "<if test=\"userno != null\"> and userno=#{userno}</if>" +
            "<if test=\"password != null\"> and password=#{password}</if>" +
            "<if test=\"username != null\"> and username=#{username}</if>" +
            "<if test=\"age != null\"> and age=#{age}</if>" +
            "<if test=\"area != null\"> and area=#{area}</if>" +
            "<if test=\"remark != null\"> and remark=#{remark}</if>" +
            "<if test=\"headurl != null\"> and headurl=#{headurl}</if>" +
            "<if test=\"phoneno != null\"> and phoneno=#{phoneno}</if>";

    String set = "<if test=\"id != null\"> id=#{id},</if>" +
            "<if test=\"userno != null\"> userno=#{userno},</if>" +
            "<if test=\"password != null\"> password=#{password},</if>" +
            "<if test=\"username != null\"> username=#{username},</if>" +
            "<if test=\"age != null\"> age=#{age},</if>" +
            "<if test=\"area != null\"> area=#{area},</if>" +
            "<if test=\"remark != null\"> remark=#{remark},</if>" +
            "<if test=\"headurl != null\"> headurl=#{headurl},</if>" +
            "<if test=\"phoneno != null\"> phoneno=#{phoneno},</if>";
}
