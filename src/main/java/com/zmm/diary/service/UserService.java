package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface UserService {

    ResultVO register(String phone, String password,String verifyCode,HttpServletRequest request);

    ResultVO login(String phone, String password);

    ResultVO update(User user);

    ResultVO findUserById(String id);

    ResultVO modifyPassword(String phone,String newPassword,String verifyCode,HttpServletRequest request);

    ResultVO modifyPhone(String id,String newPhone);

    ResultVO uploadIcon(String id,String icon);

    ResultVO getVerifyCode(String phone, HttpServletRequest request, HttpServletResponse response);

}
