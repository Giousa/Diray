package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResultVO register(@RequestParam("username")String username, @RequestParam("password")String password){

        ResultVO resultVO = userService.register(username, password);

        return resultVO;
    }


    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("username")String username, @RequestParam("password")String password){

        ResultVO resultVO = userService.login(username, password);

        return resultVO;
    }


    @PostMapping(value = "/updateUser")
    public ResultVO updateUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return ResultVO.build(201,bindingResult.getFieldError().getDefaultMessage());
        }

        ResultVO resultVO = userService.update(user);

        return resultVO;
    }

    @GetMapping(value = {"/findUserById/{id}","/findUserById"})
    public ResultVO findUserById(@PathVariable(value = "id",required = false)String id){


        ResultVO resultVO = userService.findUserById(id);

        return resultVO;
    }
}
