package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.service.UserService;
import com.zmm.diary.utils.UploadOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public ResultVO register(@RequestParam("phone")String phone, @RequestParam("password")String password,@RequestParam("verifyCode")String verifyCode,HttpServletRequest request){

        return userService.register(phone, password,verifyCode,request);
    }


    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("phone")String phone, @RequestParam("password")String password){

        return userService.login(phone, password);
    }

    @GetMapping(value = {"/deleteUserById/{id}","/deleteUserById"})
    public ResultVO deleteUserById(@PathVariable(value = "id",required = false)String id){

        return userService.delete(id);
    }


    @PostMapping(value = "/updateUser")
    public ResultVO updateUser(@RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return ResultVO.build(201,bindingResult.getFieldError().getDefaultMessage());
        }

        return userService.update(user);
    }

    @GetMapping(value = {"/findUserById/{id}","/findUserById"})
    public ResultVO findUserById(@PathVariable(value = "id",required = false)String id){


        return userService.findUserById(id);
    }

    @PostMapping(value = "/resetPassword")
    public ResultVO resetPassword(@RequestParam("phone")String phone, @RequestParam("newPassword")String newPassword,@RequestParam("verifyCode")String verifyCode,HttpServletRequest request){

        return userService.resetPassword(phone,newPassword,verifyCode,request);
    }


    @PostMapping(value = "/modifyPassword")
    public ResultVO modifyPassword(@RequestParam("phone")String phone, @RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword){

        return userService.modifyPassword(phone,oldPassword,newPassword);
    }


    @PostMapping(value = "/modifyUsername")
    public ResultVO modifyPhone(@RequestParam("id")String id, @RequestParam("newPhone")String newPhone){

        return userService.modifyPhone(id,newPhone);
    }

    @PostMapping(value = "/uploadIcon/{id}")
    public ResultVO uploadIcon(@PathVariable String id, @RequestParam(value="uploadFile",required=false) MultipartFile file){

        try {
            String path = UploadOSSUtils.uploadSinglePic(file,".jpg");

            return userService.uploadIcon(id,path);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error(ResultEnum.PIC_UPLOAD_FAILURE);

        }

    }

    @PostMapping(value = "/uploadGif")
    public ResultVO uploadGif(@RequestParam(value="uploadFile",required=false) MultipartFile file){
        try {
            String s = UploadOSSUtils.uploadSinglePic(file, ".gif");

            return ResultVO.ok(s);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error(ResultEnum.PIC_UPLOAD_FAILURE);

        }


    }

//    @GetMapping(value = {"/getVerifyCode/{phone}","/getVerifyCode"})
//    public ResultVO getVerifyCode(@PathVariable(value = "phone",required = false)String phone, HttpServletRequest request, HttpServletResponse response){
//
//        return userService.getVerifyCode(phone,request,response);
//    }

    @PostMapping(value = {"/getVerifyCode"})
    public ResultVO getVerifyCode(@RequestParam(value = "phone",required = false)String phone, HttpServletRequest request, HttpServletResponse response){

        return userService.getVerifyCode(phone,request,response);
    }
}
