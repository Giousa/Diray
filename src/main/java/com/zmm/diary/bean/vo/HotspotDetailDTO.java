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
public class HotspotDetailDTO {

    private String id;

    private UserDTO author;

    private String pic;

    private String content;

    private int collect;

    private int appreciate;

    private boolean hasCollect;

    private boolean hasAppreciate;

    private Date createTime;
}
