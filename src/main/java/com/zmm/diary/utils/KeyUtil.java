package com.zmm.diary.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/10/9
 * Email:65489469@qq.com
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：当前毫秒数+随机数
     * synchronized:防止多线程下重复
     * @return
     */
    public static synchronized String genUniqueKey(){

        Random random = new Random();

        int i = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(i);
    }

    /**
     * 生成32位不重复主键
     * @return
     */
    public static String getKeyId(){

        String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();

        return id;
    }
}
