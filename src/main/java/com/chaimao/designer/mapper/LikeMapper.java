package com.chaimao.designer.mapper;

import com.chaimao.designer.entity.Like;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Author: cmxu
 * @Description: 点赞持久层
 * @Date： create in 19:42 2018/3/11
 * @Modified By:
 */
@Mapper
public interface LikeMapper {
    //添加点赞
    @Insert("insert into like (artno,userno) values (#{artno},#{userno})")
    int createlike(@Param("artno") String artno, @Param("userno") String userno);
    //取消点赞
    @Delete("delete from like where artno = #{artno}")
    int deletelike(@Param("artno") String artno);
    //查看点赞
    @Select("<script> " +
            "select * from like where " + where +
            "</script> ")
    List<Like> getlikeList(@Param("like") Like like);
    
    String where = "<if test=\"userno != null\"> and userno=#{userno}</if>" +
            "<if test=\"artno != null\"> and artno=#{artno}</if>";

    String set = "<if test=\"userno != null\"> userno=#{userno},</if>" +
            "<if test=\"artno != null\"> artno=#{artno},</if>";
}
