package com.chaimao.designer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: cmxu
 * @Description: 作品
 * @Date： create in 18:37 2018/3/11
 * @Modified By:
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Art {
    //唯一标示
    private Integer id;
    //作品编号
    private String artno;
    //作品名称
    private String artname;
    //作品简介
    private String recommend;
    //作品存放地址
    private String arturl;
    //作者编号
    private String userno;
    //作者名称
    private String username;

    //创建时间
    private Date createtime;

}
