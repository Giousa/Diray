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

    ResultVO create(String username, String password);

    ResultVO update(User user);

    ResultVO delete(String id);

    ResultVO findUserById(String id);

}
