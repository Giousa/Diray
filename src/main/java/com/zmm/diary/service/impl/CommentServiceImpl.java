package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Comment;
import com.zmm.diary.bean.CommentReply;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.bean.vo.CommentDTO;
import com.zmm.diary.bean.vo.CommentReplyDTO;
import com.zmm.diary.bean.vo.UserDTO;
import com.zmm.diary.repository.CommentReplyRepository;
import com.zmm.diary.repository.CommentRepository;
import com.zmm.diary.repository.UserRepository;
import com.zmm.diary.service.CommentService;
import com.zmm.diary.utils.KeyUtil;
import org.apache.http.util.TextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public ResultVO findAllCommentsByHotspotId(String hotspotId, Pageable pageable) {

        Page<Comment> commentsByHotspotId = commentRepository.findCommentsByHotspotId(hotspotId, pageable);

        List<Comment> commentList = commentsByHotspotId.getContent();

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : commentList) {

            CommentDTO commentDTO = new CommentDTO();

            BeanUtils.copyProperties(comment,commentDTO);

            String fromUid = comment.getFromUid();
            User user = userRepository.findById(fromUid).get();
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            commentDTO.setFromUser(userDTO);

            String commentId = comment.getId();

            List<CommentReply> commentReplyList = commentReplyRepository.findAllByCommentId(commentId);
            List<CommentReplyDTO> commentReplyDTOList = new ArrayList<>();

            for (CommentReply commentReply:commentReplyList) {
                CommentReplyDTO commentReplyDTO = new CommentReplyDTO();
                BeanUtils.copyProperties(commentReply,commentReplyDTO);

                String fromUidReply = commentReply.getFromUid();
                String toUid = commentReply.getToUid();

                User fromUserReply = userRepository.findById(fromUidReply).get();
                User toUserReply = userRepository.findById(toUid).get();

                commentReplyDTO.setFromName(TextUtils.isEmpty(fromUserReply.getNickname()) ? fromUserReply.getUsername() : fromUserReply.getNickname());
                commentReplyDTO.setToName(TextUtils.isEmpty(toUserReply.getNickname()) ? toUserReply.getUsername() : toUserReply.getNickname());

                commentReplyDTOList.add(commentReplyDTO);

            }


            commentDTO.setCommentReplyList(commentReplyDTOList);
            commentDTOList.add(commentDTO);

        }


        return ResultVO.ok(commentDTOList);

    }
}
