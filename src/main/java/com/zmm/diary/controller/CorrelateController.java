package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.service.CorrelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */

@RestController
@RequestMapping("/correlate")
public class CorrelateController {

    @Autowired
    private CorrelateService correlateService;

    @GetMapping(value = {"/findAllFollowers"})
    public ResultVO findAllFollowers(@RequestParam(value = "userId",required = false)String userId,
                                     @RequestParam(value = "page",defaultValue = "0") Integer page,
                                     @RequestParam(value = "size",defaultValue = "10") Integer size){

        return correlateService.findAllFollowers(userId,new PageRequest(page, size, Sort.Direction.DESC,"updateTime"));
    }

    @GetMapping(value = {"/findFollowersByUserId"})
    public ResultVO findFollowersByUserId(@RequestParam(value = "userId",required = false)String userId,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size){

        return correlateService.findFollowersByUserId(userId,new PageRequest(page, size, Sort.Direction.DESC,"updateTime"));
    }

    @GetMapping(value = {"/findFunsByUserId"})
    public ResultVO findFunsByUserId(@RequestParam(value = "userId",required = false)String userId,
                                     @RequestParam(value = "page",defaultValue = "0") Integer page,
                                     @RequestParam(value = "size",defaultValue = "10") Integer size){

        return correlateService.findFunsByUserId(userId,new PageRequest(page, size, Sort.Direction.DESC,"updateTime"));
    }

    @GetMapping(value = {"/correlateAuthor"})
    public ResultVO correlateAuthor(@RequestParam(value = "userId")String userId,
                                    @RequestParam(value = "authorId")String authorId){

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(authorId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return correlateService.correlateAuthor(userId,authorId);
    }

}
