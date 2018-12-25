package com.zmm.diary.bean.vo;

import com.zmm.diary.bean.User;
import lombok.Data;
import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/25
 * Email:65489469@qq.com
 */
@Data
public class HtospotVO{

    private String id;

    private User user;

    private String pic;

    private String content;

    private int collect;

    private Date createTime;

    private Date updateTime;
}
