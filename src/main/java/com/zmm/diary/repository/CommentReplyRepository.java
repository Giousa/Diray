package com.zmm.diary.repository;

import com.zmm.diary.bean.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface CommentReplyRepository extends JpaRepository<CommentReply,String> {

    List<CommentReply> findAllByCommentId(String commentId);
}
