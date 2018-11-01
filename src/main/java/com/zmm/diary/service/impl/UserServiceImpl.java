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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

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
    public ResultVO register(String username, String password) {

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
    public ResultVO login(String username, String password) {


        User user = userRepository.findUserByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));

        if(user != null){
            return ResultVO.ok(user);
        }

        return ResultVO.error(ResultEnum.USER_NOT_EXIST);
    }

    @Override
    public ResultVO update(User user) {

        String id = user.getId();

        Optional<User> byId = userRepository.findById(id);

        if(!byId.isPresent()){
            return ResultVO.error(ResultEnum.USER_NOT_EXIST);
        }

        User userDB = byId.get();

        userDB.setNickname(user.getNickname());
        userDB.setGender(user.getGender());
        userDB.setAge(user.getAge());
        userDB.setBirthday(user.getBirthday());
        userDB.setHeight(user.getHeight());
        userDB.setWeight(user.getWeight());

        User update = userRepository.save(userDB);
        return ResultVO.ok(update);
    }

    @Override
    public ResultVO findUserById(String id) {

        if(StringUtils.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        Optional<User> byId = userRepository.findById(id);

        if(byId.isPresent()){
            return ResultVO.ok(byId.get());
        }

        return ResultVO.error(ResultEnum.USER_NOT_EXIST);

    }

    @Override
    public ResultVO modifyPassword(String id, String newPassword) {

        Optional<User> byId = userRepository.findById(id);

        if(!byId.isPresent()){
            return ResultVO.error(ResultEnum.USER_NOT_EXIST);
        }

        User user = byId.get();

        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));

        User modify = userRepository.save(user);

        return ResultVO.ok(modify);
    }

    @Override
    public ResultVO modifyUsername(String id, String newUsername) {

        Optional<User> byId = userRepository.findById(id);

        if(!byId.isPresent()){
            return ResultVO.error(ResultEnum.USER_NOT_EXIST);
        }

        User user = byId.get();

        user.setUsername(newUsername);

        User modify = userRepository.save(user);

        return ResultVO.ok(modify);
    }

}
