package com.zmm.diary.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/25
 * Email:65489469@qq.com
 */
@Data
public class HotspotVO {

    private String id;

    private UserVO author;

    private String pic;

    private String content;

    private int collect;

    private int appreciate;

    private Date createTime;
}
