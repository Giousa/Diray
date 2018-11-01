package com.zmm.diary.service.impl;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.UserRepository;
import com.zmm.diary.service.UserService;
import com.zmm.diary.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultVO create(String username, String password) {

        List<User> userList = userRepository.findByUsername(username);

        if(userList != null && userList.size() >0){
            return ResultVO.error(ResultEnum.USER_EXIST);
        }
        User pre = new User();
        pre.setId(KeyUtil.getKeyId());
        pre.setUsername(username);
        pre.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        User user = userRepository.save(pre);
        return ResultVO.ok(user);
    }

    @Override
    public ResultVO update(User user) {

        User update = userRepository.save(user);
        return ResultVO.ok(update);
    }

    @Override
    public ResultVO delete(String id) {

        try {
            userRepository.deleteById(id);
            return ResultVO.ok();
        }catch (Exception e){
            return ResultVO.error(ResultEnum.SERVE_EXCEPTION);
        }
    }

    @Override
    public ResultVO findUserById(String id) {

        User user = userRepository.findById(id).get();

        return ResultVO.ok(user);
    }

}
