package com.chaimao.designer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: cmxu
 * @Description: 关注粉丝持久层
 * @Date： create in 21:02 2018/3/10
 * @Modified By:
 */
@Mapper
public interface RelationsMapper {
    //查询粉丝
    @Select("select fansno from relations where userno = #{userno}")
    List<String> getFans(@Param("userno") String userno);
    //查询关注
    @Select("select userno from relations where fansno = #{fansno}")
    List<String> getFollow(@Param("fansno") String userno);
    //关注
    @Insert("insert into relations (userno,fansno) values (#{userno},#{fansno})")
    int follow(@Param("userno") String userno,@Param("fansno") String fansno);
    //取消关注
    @Insert("delete from relations where userno=#{userno} and fansno=#{fansno}")
    int cancelFollow(@Param("userno") String userno,@Param("fansno") String fansno);

    String where = "<if test=\"id != null\"> and id=#{id}</if>" +
            "<if test=\"userno != null\"> and userno=#{userno}</if>" +
            "<if test=\"fansno != null\"> and fansno=#{fansno}</if>";

    String set = "<if test=\"id != null\"> id=#{id},</if>" +
            "<if test=\"userno != null\"> userno=#{userno},</if>" +
            "<if test=\"fansno != null\"> fansno=#{fansno},</if>";
}
