package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface UserService {

    ResultVO register(String username, String password);

    ResultVO login(String username, String password);

    ResultVO update(User user);

    ResultVO findUserById(String id);

    ResultVO modifyPassword(String id,String newPassword);

    ResultVO modifyUsername(String id,String newUsername);

    ResultVO uploadIcon(String id,String icon);

}
