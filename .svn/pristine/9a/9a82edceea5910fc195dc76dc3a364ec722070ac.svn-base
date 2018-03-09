package com.chaimao.designer.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 缓存加载
 * Created by turkeymz on 2017/8/9.
 */
@Component
@Order(value = 1)
@Slf4j
public class CacheLoader implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {

        log.info("Cache begin ......");
        ServiceMethodCache serviceMethodCache = new ServiceMethodCache();
        serviceMethodCache.load();
        log.info("Cache end ......");
    }


}
