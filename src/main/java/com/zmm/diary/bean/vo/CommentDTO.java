package com.zmm.diary.bean.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/2/26
 * Email:65489469@qq.com
 */
@Data
public class CommentDTO {

    private String id;

    private UserDTO fromUser;

    private String hotspotId;

    private String content;

    //点赞数
    private int praiseCount;

    private Date createTime;

    private List<CommentReplyDTO> commentReplyList;
}
