package com.chaimao.designer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: cmxu
 * @Description:关注粉丝表
 * @Date： create in 20:38 2018/3/10
 * @Modified By:
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Relations {
    //id自增
    private Integer id;
    //用户编号
    private String userno;
    //粉丝用户编号
    private String fansno;

}
