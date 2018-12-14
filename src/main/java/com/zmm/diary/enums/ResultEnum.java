package com.zmm.diary.enums;

import lombok.Getter;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/10/5
 * Email:65489469@qq.com
 */
@Getter
public enum ResultEnum {

    SERVE_EXCEPTION(1,"服务器繁忙，请稍后再试"),

    PARAM_ERROR(201,"参数不正确"),

    USER_EXIST(202,"用户已存在"),

    USER_NOT_EXIST(203,"用户不存在"),

    USERNAME_ERROR(204,"用户名格式不正确"),

    PASSWORD_ERROR(205,"密码格式不正确"),

    LOGIN_FAILURE(206,"用户名或密码错误"),

    PIC_UPLOAD_FAILURE(207,"图片上传失败"),

    VERIFYCODE_HAS_SEND(208,"验证码已发送"),

    VERIFYCODE_HAS_EXPIRE(209,"验证码已过期"),

    VERIFYCODE_HAS_ERROR(210,"验证码错误"),

    NOTE_HAS_EXIST(211,"记录不存在"),

    DELETE_FAILURE(212,"删除失败")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
