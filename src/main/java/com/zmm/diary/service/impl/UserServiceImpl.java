package com.zmm.diary.service.impl;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.UserRepository;
import com.zmm.diary.service.UserService;
import com.zmm.diary.utils.*;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Value("${DIARY_COOKIE}")
    private String DIARY_COOKIE;

    @Value("${SMS_UID}")
    private String SMS_UID;

    @Value("${SMS_KEY}")
    private String SMS_KEY;


    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultVO register(String phone, String password,String verifyCode,HttpServletRequest request) {

        String cookieValue = CookieUtils.getCookie(request,DIARY_COOKIE);

        if(StringUtils.isEmpty(cookieValue)){
            return ResultVO.error(ResultEnum.VERIFYCODE_HAS_EXPIRE);
        }

        if(!cookieValue.equals(verifyCode)){
            return ResultVO.error(ResultEnum.VERIFYCODE_HAS_ERROR);
        }

        List<User> userList = userRepository.findByUsername(phone);

        if(userList != null && userList.size() >0){
            return ResultVO.error(ResultEnum.USER_EXIST);
        }
        User pre = new User();
        pre.setId(KeyUtil.getKeyId());
        pre.setUsername(phone);
        pre.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        User user = userRepository.save(pre);
        return ResultVO.ok(user);
    }

    @Override
    public ResultVO login(String phone, String password) {


        User user = userRepository.findUserByUsernameAndPassword(phone, DigestUtils.md5DigestAsHex(password.getBytes()));

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
    public ResultVO modifyPassword(String id, String newPassword,String verifyCode,HttpServletRequest request) {

        String cookieValue = CookieUtils.getCookie(request,DIARY_COOKIE);

        if(StringUtils.isEmpty(cookieValue)){
            return ResultVO.error(ResultEnum.VERIFYCODE_HAS_EXPIRE);
        }

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
    public ResultVO modifyPhone(String id, String newPhone) {

        Optional<User> byId = userRepository.findById(id);

        if(!byId.isPresent()){
            return ResultVO.error(ResultEnum.USER_NOT_EXIST);
        }

        User user = byId.get();

        user.setUsername(newPhone);

        User modify = userRepository.save(user);

        return ResultVO.ok(modify);
    }

    @Override
    public ResultVO uploadIcon(String id, String icon) {

        Optional<User> byId = userRepository.findById(id);

        if(!byId.isPresent()){
            return ResultVO.error(ResultEnum.USER_NOT_EXIST);
        }

        User user = byId.get();

        user.setIcon(icon);

        User modify = userRepository.save(user);

        return ResultVO.ok(modify);
    }

    @Override
    public ResultVO getVerifyCode(String phone, HttpServletRequest request, HttpServletResponse response) {

        if(StringUtils.isEmpty(phone)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        String cookieValue = CookieUtils.getCookie(request,DIARY_COOKIE);
        //生成验证码
//		String verifyCode = VerifyCodeUtils.getCode();
//		//存储验证码，用作注册和修改密码时校验
//		CookieUtils.setCookie(request, response, TMS_COOKIE, verifyCode+"", 60);
//
//		//验证码不要展示，而是发送到手机
//		HttpClientUtil clientUtil = HttpClientUtil.getInstance();
//		String content = SmsUtils.build(verifyCode);
//		clientUtil.sendMsgUtf8(SMS_UID, SMS_KEY, content, phone);
//		return SysResult.build(200, "验证码发送成功","验证码发送成功");
//		System.out.println("发送验证码："+cookieValue);
        if(TextUtils.isEmpty(cookieValue)){
            //生成验证码
            String verifyCode = VerifyCodeUtils.getCode();
            //存储验证码，用作注册和修改密码时校验 15分钟
            CookieUtils.writeCookie(response,DIARY_COOKIE, verifyCode);

            //验证码不要展示，而是发送到手机
            HttpClientUtil clientUtil = HttpClientUtil.getInstance();
            String content = SmsUtils.build(verifyCode);
            clientUtil.sendMsgUtf8(SMS_UID, SMS_KEY, content, phone);

            return ResultVO.ok("验证码发送成功");
        }else{
            return ResultVO.error(ResultEnum.VERIFYCODE_HAS_SEND);
        }

    }

}
