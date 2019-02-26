package com.zmm.diary.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/2/26
 * Email:65489469@qq.com
 */
@Data
public class CommentReplyDTO {

    private String id;

    private String commentId;

    private String fromName;

    private String toName;

    private String content;

    private Date createTime;

}
