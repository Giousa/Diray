package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/2/14
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/newComment")
    public ResultVO newComment(@RequestParam(value = "hotspotId")String hotspotId,
                               @RequestParam(value = "fromUid")String fromUid,
                               @RequestParam(value = "content")String content){

        return commentService.newComment(hotspotId,fromUid,content);
    }

    @GetMapping(value = "/replyComment")
    public ResultVO replyComment(@RequestParam(value = "commentId")String commentId,
                                 @RequestParam(value = "fromUid")String fromUid,
                                 @RequestParam(value = "toUid")String toUid,
                                 @RequestParam(value = "content")String content){


        return commentService.replyComment(commentId,fromUid,toUid,content);
    }

}
