package com.zmm.diary.utils;

public class SmsUtils {

	public static String build(String verifyCode) {
		
		return "您的验证码是："+verifyCode+",有效时间15分钟。请不要把验证码泄露给他人，如非本人操作，可不用理会。";
	}
}
