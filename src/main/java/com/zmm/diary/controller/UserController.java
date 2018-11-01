package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.service.UserService;
import com.zmm.diary.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResultVO register(@RequestParam("username")String username, @RequestParam("password")String password){

        ResultVO resultVO = userService.create(username, password);

        return resultVO;
    }

}
