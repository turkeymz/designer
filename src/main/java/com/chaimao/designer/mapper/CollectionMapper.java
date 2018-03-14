package com.chaimao.designer.mapper;

import com.chaimao.designer.entity.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: cmxu
 * @Description: 收藏持久层
 * @Date： create in 19:42 2018/3/11
 * @Modified By:
 */
@Mapper
public interface CollectionMapper {
    //添加收藏
    @Insert("insert into collection (artno,userno) values (#{artno},#{userno})")
    int createCollection(@Param("artno") String artno, @Param("userno") String userno);
    //取消收藏
    @Delete("delete from collection where artno = #{artno}")
    int deleteCollection(@Param("artno") String artno);
    //查看收藏
    @Select("<script> " +
            "select * from collection where " + where +
            "</script> ")
    List<Collection> getCollectionList(@Param("collection") Collection collection);

    String where = "<if test=\"userno != null\"> and userno=#{userno}</if>" +
            "<if test=\"artno != null\"> and artno=#{artno}</if>";

    String set = "<if test=\"userno != null\"> userno=#{userno},</if>" +
            "<if test=\"artno != null\"> artno=#{artno},</if>";
}
