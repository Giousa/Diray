package com.zmm.diary.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * Cookie 工具类
 *
 */
public final class CookieUtils {


    public static String getCookie(HttpServletRequest request,String cookieName){

        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName,String value){
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setPath("/");
        cookie.setMaxAge(15*60);
        response.addCookie(cookie);
    }

}
