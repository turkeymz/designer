package com.chaimao.designer.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.PipedReader;
import java.util.HashMap;

/**
 * @Author: cmxu
 * @Description: 系统配置类
 * @Date： create in 22:48 2018/3/7
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "mypro")
@Data
@NoArgsConstructor
public class SysConfig {
    //短信配置
    private HashMap<String,String> message = new HashMap<>();
    //头像保存路径
    private String headurl;
    //作品保存路径
    private String arturl;
}
