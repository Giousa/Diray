package com.zmm.diary;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/28
 * Email:65489469@qq.com
 */
@SpringBootApplication
@ServletComponentScan
public class SpringBootStartApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DiaryApplication.class);
    }
}
