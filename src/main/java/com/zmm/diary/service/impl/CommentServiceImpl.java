package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Comment;
import com.zmm.diary.bean.CommentReply;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.repository.CommentReplyRepository;
import com.zmm.diary.repository.CommentRepository;
import com.zmm.diary.service.CommentService;
import com.zmm.diary.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/2/14
 * Email:65489469@qq.com
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentReplyRepository commentReplyRepository;

    @Override
    public ResultVO newComment(String hotspotId,String fromUid,String content) {

        Comment comment = new Comment();
        comment.setId(KeyUtil.getKeyId());
        comment.setHotspotId(hotspotId);
        comment.setFromUid(fromUid);
        comment.setContent(content);
        comment.setPraiseCount(0);
        commentRepository.save(comment);

        return ResultVO.ok("评论成功");
    }

    @Override
    public ResultVO replyComment(String commentId,String fromUid,String toUid,String content) {

        CommentReply commentReply = new CommentReply();
        commentReply.setId(KeyUtil.getKeyId());
        commentReply.setCommentId(commentId);
        commentReply.setFromUid(fromUid);
        commentReply.setToUid(toUid);
        commentReply.setContent(content);
        commentReplyRepository.save(commentReply);

        return ResultVO.ok("回复成功");
    }
}
