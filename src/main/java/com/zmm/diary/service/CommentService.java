package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface CommentService {

    ResultVO newComment(String hotspotId,String fromUid,String content);

    ResultVO replyComment(String commentId,String fromUid,String toUid,String content);

}
