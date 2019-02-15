package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
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

    @GetMapping("/findAllCommentsByHotspotId")
    public ResultVO findAllCommentsByHotspotId(@RequestParam("hotspotId") String hotspotId,
                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(hotspotId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return commentService.findAllCommentsByHotspotId(hotspotId, new PageRequest(page, size, Sort.Direction.DESC,"createTime"));
    }

}
